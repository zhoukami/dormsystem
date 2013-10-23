package bd.inner.dormitory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.server.entity.TDorm;
import bd.inner.dormitory.util.BaseDAO;

public class TDormDAO implements BaseDAO<TDorm> {
	Connection conn;

	public TDormDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public long add(TDorm t) throws SQLException {
		long id = 0;
		PreparedStatement prestmt = null;
		if (conn != null) {
			try {
				String sql = "INSERT INTO TDorm(room,floor,building,type,zone,unitid) " +
						"				values(?,?,?,?,?,?)";
				prestmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prestmt.setString(1, t.getRoom());
				prestmt.setString(2, t.getFloor());
				prestmt.setString(3, t.getBuilding());
				prestmt.setString(4, t.getType());
				prestmt.setString(5, t.getZone());
				prestmt.setLong(6, t.getUnitid());
				int count = prestmt.executeUpdate();
				if(count > 0){
					ResultSet rs = prestmt.getGeneratedKeys();
					if(rs.next()) {
						id = rs.getInt(1);
					}
				}
			} catch (SQLException e) {
				System.out.println("ERROR" + e.getMessage());
			}finally{
				if(prestmt != null){
					try {
						prestmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR"+e.getMessage());
					}
				}
			}
		}
		return id;
	}

	@Override
	public boolean update(TDorm t) throws SQLException {
		Boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			String sql = "update TDorm  set room = ?,floor = ?,building = ?,type = ?,zone = ?,unitid = ?"
					+ "where id = ?";
			try {
				prestmt = conn.prepareStatement(sql);
				prestmt.setString(1, t.getRoom());
				prestmt.setString(2, t.getFloor());
				prestmt.setString(3, t.getBuilding());
				prestmt.setString(4, t.getType());
				prestmt.setString(5, t.getZone());
				prestmt.setLong(6, t.getUnitid());
				prestmt.setLong(7, t.getId());
				  if(prestmt.executeUpdate() > 0)
				    	flag = true;
			} catch (SQLException e) {
				System.out.println("ERROR "+" UPDATE "+e.getMessage());
			}finally{
				try {
					prestmt.close();
				} catch (SQLException e) {
					System.out.println("ERROR "+" UPDATE "+e.getMessage());
				}
			}
		}
		return flag;
	}

	@Override
	public TDorm get(long condition) throws SQLException {
		PreparedStatement prestmt = null;
		TDorm u =null;
		if(conn != null){
			String sql = "select * from TDorm where id = "+condition;
			try {
				prestmt = conn.prepareStatement(sql);
				ResultSet rs = prestmt.executeQuery();
				if (rs.next()) {
					u = new TDorm();
					u.setId(rs.getLong("id"));
					u.setRoom(rs.getString("room"));
					u.setFloor(rs.getString("floor"));
					u.setBuilding(rs.getString("building"));
					u.setType(rs.getString("type"));
					u.setZone(rs.getString("zone"));
					u.setUnitid(rs.getLong("unitid"));
					//listUser.add(u);
					}
			} catch (SQLException e) {
				System.err.println("ERROR "+" FIND BY CONDITION"+e.getMessage());
			}finally{
				try {
					prestmt.close();
				} catch (SQLException e) {
					System.out.println("ERROR "+" FIND BY CONDITION"+e.getMessage());
				}
			}
		}
		return u;
	}

	@Override
	public boolean delete(Object id) throws SQLException {
		boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			try {
				String sql = "delete from TDorm  where id = ?";
				prestmt = conn.prepareStatement(sql);
				prestmt.setObject(1, id);
			    if(prestmt.executeUpdate() > 0)
			    	flag = true;
			} catch (SQLException e) {
				System.out.println("ERROR  "+"DELETE BY ID "+e.getMessage());
			}finally{
				try {
					prestmt.close();
				} catch (SQLException e) {
					System.out.println("ERROR  "+"DELETE BY ID "+e.getMessage());
				}
			}
		}
		return flag;
	}

}
