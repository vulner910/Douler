/**
 * 数据库操作基础信息
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import douler.Public_Var;
import douler.ReadConfig;

public class BaseDao {
	private static BasicDataSource ds;

	static {
		try {
			if (Public_Var.PROP)
				new ReadConfig().doReadConfig();
			String driver = Public_Var.driver;
			String url = Public_Var.url;
			String username = Public_Var.username;
			String password = Public_Var.password;

			ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);

			ds.setTestOnBorrow(true);
			ds.setTestOnReturn(true);
			ds.setTestWhileIdle(true);
			ds.setValidationQuery("select 1 from dual");

			ds.setInitialSize(5);
			ds.setMaxActive(100);// maxActive：最大连接数据库连接数，设置为0时，表示没有限制；
			ds.setMaxIdle(5);// maxIdle：最大等待连接中的数量，设置为0时，表示没有限制；

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static Connection openConnection() throws SQLException {
		return ds.getConnection();
	}

	protected static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
