package bd.inner.dormitory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import db.server.entity.TInDev;
import bd.inner.dormitory.util.BaseDAO;

public class TInDevDAO implements BaseDAO<TInDev> {
	Connection conn;

	public TInDevDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public long add(TInDev t) throws SQLException {
		long id = 0;
		PreparedStatement prestmt = null;
		if (conn != null) {
			try {
				String sql = "INSERT INTO TInDev(systemid,devtype,devid,status,factorytime,registertime,statusmemo,memo) " +
						"				values(?,?,?,?,?,?,?,?)";
				prestmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prestmt.setLong(1, t.getSystemid());
				prestmt.setString(2, t.getDevtype());
				prestmt.setString(3, t.getDevid());
				prestmt.setString(4, t.getStatus());
				prestmt.setDate(5, t.getFactorytime());
				prestmt.setDate(6, t.getRegistertime());
				prestmt.setString(7, t.getStatusmemo());
				prestmt.setString(8, t.getMemo());
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
	public boolean update(TInDev t) throws SQLException {
		Boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			String sql = "update TInDev  set systemid = ?,devtype = ?,devid = ?,status = ?,factorytime = ?,registertime = ?,statusmemo = ?,memo = ?"
					+ "where id = ?";
			try {
				prestmt = conn.prepareStatement(sql);
				prestmt.setLong(1, t.getSystemid());
				prestmt.setString(2, t.getDevtype());
				prestmt.setString(3, t.getDevid());
				prestmt.setString(4, t.getStatus());
				prestmt.setDate(5, t.getFactorytime());
				prestmt.setDate(6, t.getRegistertime());
				prestmt.setString(7, t.getStatusmemo());
				prestmt.setString(8, t.getMemo());
				prestmt.setLong(9, t.getId());
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
	public TInDev get(long condition) throws SQLException {
		PreparedStatement prestmt = null;
		TInDev u =null;
		if(conn != null){
			String sql = "select * from TInDev where id = "+condition;
			try {
				prestmt = conn.prepareStatement(sql);
				ResultSet rs = prestmt.executeQuery();
				if (rs.next()) {
					u = new TInDev();
					u.setId(rs.getLong("id"));
					u.setSystemid(rs.getLong("systemid"));
					u.setDevtype(rs.getString("devtype"));
					u.setDevid(rs.getString("devid"));
					u.setStatus(rs.getString("status"));
					u.setFactorytime(rs.getDate("factorytime"));
					u.setRegistertime(rs.getDate("registertime"));
					u.setStatusmemo(rs.getString("statusmemo"));
					u.setMemo(rs.getString("memo"));
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
				String sql = "delete from TInDev  where id = ?";
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
