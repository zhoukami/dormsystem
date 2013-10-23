package bd.inner.dormitory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.server.entity.TDormAssign;
import bd.inner.dormitory.util.BaseDAO;

public class TDormAssignDAO implements BaseDAO<TDormAssign> {
	Connection conn;

	public TDormAssignDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public long add(TDormAssign t) throws SQLException {
		long id = 0;
		PreparedStatement prestmt = null;
		if (conn != null) {
			try {
				String sql = "INSERT INTO TDormAssign(dormid,userid,starttime,endtime) " +
						"				values(?,?,?,?)";
				prestmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prestmt.setLong(1, t.getDormid());
				prestmt.setLong(2, t.getUserid());
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
	public boolean update(TDormAssign t) throws SQLException {
		Boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			String sql = "update TDormAssign  set dormid = ?,userid = ?,starttime = ?,endtime = ?"
					+ "where id = ?";
			try {
				prestmt = conn.prepareStatement(sql);
				prestmt.setLong(1, t.getDormid());
				prestmt.setLong(2, t.getUserid());
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
	public TDormAssign get(long condition) throws SQLException {
		PreparedStatement prestmt = null;
		TDormAssign u =null;
		if(conn != null){
			String sql = "select * from TDormAssign where id = "+condition;
			try {
				prestmt = conn.prepareStatement(sql);
				ResultSet rs = prestmt.executeQuery();
				if (rs.next()) {
					u = new TDormAssign();
					u.setId(rs.getLong("id"));
					u.setDormid(rs.getLong("dormid"));
					u.setUserid(rs.getLong("userid"));
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
				String sql = "delete from TDormAssign  where id = ?";
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
