package bd.inner.dormitory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.server.entity.TUser;
import bd.inner.dormitory.util.BaseDAO;

public class TUserDAO implements BaseDAO<TUser> {
	Connection conn;

	public TUserDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public long add(TUser t) throws SQLException {
		long id = 0;
		PreparedStatement prestmt = null;
		if (conn != null) {
			try {
				String sql = "INSERT INTO TUser(name,password,jobnumber,idcardid,idcardnum,iccard,points,remainpoints,type,mobile,qq,unitid,rfid) " +
						"				values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				prestmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				prestmt.setString(1, t.getName());
				prestmt.setString(2, t.getPassword());
				prestmt.setString(3, t.getJobnumber());
				prestmt.setLong(4, t.getIdcardid());
				prestmt.setString(5, t.getIdcardnum());
				prestmt.setString(6, t.getIccard());
				prestmt.setLong(7, t.getPoints());
				prestmt.setLong(8, t.getRemainpoints());
				prestmt.setString(9, t.getType());
				prestmt.setString(10, t.getMobile());
				prestmt.setString(11, t.getQq());
				prestmt.setLong(12, t.getUnitid());
				prestmt.setString(13, t.getRfid());
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
	public boolean update(TUser t) throws SQLException {
		Boolean flag = false;
		PreparedStatement prestmt = null;
		if(conn != null){
			String sql = "update TUser  set name = ?,password = ?,jobnumber = ?,idcardid = ?,idcardnum = ?,iccard = ?,points = ?,remainpoints = ?,type = ?,mobile = ?,qq = ?,unitid = ?,rfid = ?"
					+ "where id = ?";
			try {
				prestmt = conn.prepareStatement(sql);
				prestmt.setString(1, t.getName());
				prestmt.setString(2, t.getPassword());
				prestmt.setString(3, t.getJobnumber());
				prestmt.setLong(4, t.getIdcardid());
				prestmt.setString(5, t.getIdcardnum());
				prestmt.setString(6, t.getIccard());
				prestmt.setLong(7, t.getPoints());
				prestmt.setLong(8, t.getRemainpoints());
				prestmt.setString(9, t.getType());
				prestmt.setString(10, t.getMobile());
				prestmt.setString(11, t.getQq());
				prestmt.setLong(12, t.getUnitid());
				prestmt.setString(13, t.getRfid());
				prestmt.setLong(14, t.getId());
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
	public TUser get(long condition) throws SQLException {
		PreparedStatement prestmt = null;
		TUser u =null;
		if(conn != null){
			String sql = "select * from TUser where id = "+condition;
			try {
				prestmt = conn.prepareStatement(sql);
				ResultSet rs = prestmt.executeQuery();
				if (rs.next()) {
					u = new TUser();
					u.setId(rs.getLong("id"));
					u.setName(rs.getString("name"));
					u.setPassword(rs.getString("password"));
					u.setJobnumber(rs.getString("jobnumber"));
					u.setIdcardid(rs.getLong("idcardid"));
					u.setIdcardnum(rs.getString("idcardnum"));
					u.setIccard(rs.getString("iccard"));
					u.setPoints(rs.getLong("points"));
					u.setRemainpoints(rs.getLong("remainpoints"));
					u.setType(rs.getString("type"));
					u.setMobile("mobile");
					u.setQq(rs.getString("qq"));
					u.setUnitid(rs.getLong("unitid"));
					u.setRfid(rs.getString("rfid"));
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
				String sql = "delete from TUser  where id = ?";
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
