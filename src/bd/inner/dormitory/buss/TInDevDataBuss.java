package bd.inner.dormitory.buss;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.server.entity.TInDevData;

import bd.inner.dormitory.dao.TInDevDataDAO;
import bd.inner.dormitory.util.BaseBuss;
import bd.inner.dormitory.util.DAOFactory;
import bd.inner.dormitory.util.DatabaseConnection;



public class TInDevDataBuss implements BaseBuss<TInDevData> {

	@Override
	public boolean add(TInDevData t) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(true);
			long i = DAOFactory.getInstance().getDAO(TInDevDataDAO.class, conn).add(t);
			if(i > 0){
				flag = true;
				System.out.println("RESULT " +i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public boolean update(TInDevData t) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(true);
			flag = DAOFactory.getInstance().getDAO(TInDevDataDAO.class, conn).update(t);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public boolean delete(Object id) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(true);
			flag = DAOFactory.getInstance().getDAO(TInDevDataDAO.class, conn).delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public TInDevData load(Object id) {
		Connection conn = null;
		TInDevData u = null;
		try {
			conn = DatabaseConnection.getConnection(true);
//			String condition = " where id = "+id;
			u = DAOFactory.getInstance().getDAO(TInDevDataDAO.class, conn).get((long) id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return u;
	}
	public List<TInDevData> load(String condition) {
		Connection conn = null;
		List<TInDevData> u = null;
		try {
			conn = DatabaseConnection.getConnection(true);
//			u = DAOFactory.getInstance().getDAO(TInDevDataDAO.class, conn).get(condition);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return u;
	}


}
