package bd.inner.dormitory.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
	private static ConnectionPool connectionPool = null;
	static {
		try {
			connectionPool = new ConnectionPool(Configuration.JDBC_ORACLE_DRIVER,
					Configuration.URL, Configuration.USER,
					Configuration.PASSWORD);
			connectionPool.setInitialConnections(Configuration.INIT_CONNECTIONS);// 设置初始池大小
			connectionPool.setMaxConnections(Configuration.MAX_CONNECTIONS);// 设置最大池大小
			connectionPool.setIncrementalConnections(Configuration.INCREMENTAL_CONNECTIONS);// 设置每次增加的连接数目
			connectionPool.createPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库连接
	 * @param transaction 是否启用事务
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(boolean transaction) throws SQLException {
		Connection con=connectionPool.getConnection();
		con.setAutoCommit(!transaction);
		return con;
	}
}