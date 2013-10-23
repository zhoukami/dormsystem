package bd.inner.dormitory.util;

public class Configuration {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER = "";
	public static final String PASSWORD = "123456";
	public static final String JDBC_ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	public static final int INIT_CONNECTIONS = 10;//初始大小
	public static final int MAX_CONNECTIONS = 50;// 设置最大池大小
	public static final int INCREMENTAL_CONNECTIONS = 5;// 设置每次增加的连接数目
	
}
