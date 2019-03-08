package cn.library.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 改良版DBUtils的QueryRunner类
 * 具备原功能 和 资源的自动获取和自动释放 的功能
 * QueryRunner的子类，目的是将所有方法融入连接的获取和释放，从而不用在构造器中获取连接池，
 * 从而实现与事务的连接的一致性
 * 而且，当调用无连接参数的方法时（即以下这些方法），自动会获取连接，执行相应内容，释放连接，
 * 则不需再写获取连接和释放连接的重复性高的语句。
 * @author zzshang
 *
 */
public class TxQueryRunner extends QueryRunner {
	
	//从JDBCUtils工程类中获得

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		int[] batch = super.batch(connection, sql, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return batch;
	}

	@Override
	public int execute(String sql, Object... params) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		int execute = super.execute(connection, sql, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return execute;
}

	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		List<T> execute = super.execute(connection, sql, rsh, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return execute;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.insert(connection, sql, rsh, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.insert(connection, sql, rsh);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh,
			Object[][] params) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.insertBatch(connection, sql, rsh, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.query(connection, sql, param, rsh);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.query(connection, sql, params, rsh);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.query(connection, sql, rsh, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		T insert = super.query(connection, sql, rsh);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return insert;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		int update = super.update(connection, sql, params);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return update;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		int update = super.update(connection, sql, param);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return update;
	}

	@Override
	public int update(String sql) throws SQLException {
		//1.得到连接
		Connection connection = JdbcUtils.getConnection();
		
		//2.执行父类带连接的同名方法，融入连接
		int update = super.update(connection, sql);
		
		//3.释放连接
		JdbcUtils.releaseConnection(connection);
		
		//4.返回值
		return update;
	}
	
}