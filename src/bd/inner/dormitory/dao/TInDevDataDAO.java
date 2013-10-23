package bd.inner.dormitory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.server.entity.TInDevData;
import bd.inner.dormitory.util.BaseDAO;

public class TInDevDataDAO implements BaseDAO<TInDevData> {
	Connection conn;

	public TInDevDataDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public long add(TInDevData t) throws SQLException {
		long id = 0;
		PreparedStatement prestmt = null;
		if (conn != null) {
			try {
				String sql = "INSERT INTO TInDevData(indevid,dormid,starttime,endtime) " +
						"				values(?,?,?,?)";
				prestmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prestmt.setLong(1, t.getIndevid());
				prestmt.setLong(2, t.getDormid());
				prestmt.setDate(3, t.getStarttime());
				prestmt.setDate(4, t.getEndtime());
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
	public boolean update(TInDevData t) throws SQLException {
		Boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			String sql = "update TInDevData  set indevid = ?,dormid = ?,starttime = ?,endtime = ?"
					+ "where id = ?";
			try {
				prestmt = conn.prepareStatement(sql);
				prestmt.setLong(1, t.getIndevid());
				prestmt.setLong(2, t.getDormid());
				prestmt.setDate(3, t.getStarttime());
				prestmt.setDate(4, t.getEndtime());
				prestmt.setLong(5, t.getId());
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
	public TInDevData get(long condition) throws SQLException {
		PreparedStatement prestmt = null;
		TInDevData u =null;
		if(conn != null){
			String sql = "select * from TInDevData where id = "+condition;
			try {
				prestmt = conn.prepareStatement(sql);
				ResultSet rs = prestmt.executeQuery();
				if (rs.next()) {
					u = new TInDevData();
					u.setId(rs.getLong("id"));
					u.setIndevid(rs.getLong("indevid"));
					u.setDormid(rs.getLong("dormid"));
					u.setStarttime(rs.getDate("starttime"));
					u.setEndtime(rs.getDate("endtime"));
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
				String sql = "delete from TInDevData  where id = ?";
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
