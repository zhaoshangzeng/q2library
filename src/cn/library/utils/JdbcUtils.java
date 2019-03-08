package cn.library.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.log.ModifyLogLevel;

/**
 * JDBC工具类
 * 具备获得数据库连接给到DAO层  和 处理service层事务 的功能
 * C3P0技术，连接池
 * 连接池的相关参数配置在c3p0-config.xml
 * 补充了service层的事务方法
 * 强化成可适应多线程，使用ThreadLocal（专门修改事务的部分，因为事务都是多操作多线程）
 * @author zzshang
 * @version v5.0
 */
public class JdbcUtils {

	/**
	 * 连接池变量
	 */
	private static DataSource ds = null;
	
	/**
	 * 事务专用连接器
	 * 把连接器装到线程域中
	 */
	private static ThreadLocal<Connection> tc = new ThreadLocal<Connection>();
//	private static Connection con = null;
	
	static{  //（只加载一次）
		ModifyLogLevel.modifyInfoLevel(Level.ALL);//去掉日志输出到控制台
		ds = new ComboPooledDataSource();  //创建C3P0连接池对象（带参构造器可以指定配置信息名）
	}	
	
	/**
	 * 提供连接器。
	 * 因为DAO层改为dbutil工具后，该方法在DAO层成了废弃方法，现改为Service层专用方法，即有了新作用。
	 * 补充：为了保证connection一致，即事务的连接和dao层使用的连接要一致，所以dao层也用该方法。
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		//获得线程自己的con
		Connection con = tc.get();
		if(con != null){  //不为空，则说明有事务开启了，要沿用事务的连接。
			return con;
		}
		
		return ds.getConnection(); //说明无事务，创建新的连接，并直接返回，不赋值给con
	}
		
	/**
	 * 开始事务
	 * 给service层开始事务功能
	 * 保证service层使用的Connection对象和开始事务的Connection对象是一致的
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		Connection con = tc.get();
		//防止重复开启事务而导致浪费资源，给予警告
		if(con != null){
			throw new SQLException("已经开启了事务，不要重复开启！");
		}
		con = ds.getConnection();  //当con有值，说明事务开启
		con.setAutoCommit(false);
		
		//将自己创建的con存到自己的线程域中
		tc.set(con);
	}
	
	/**
	 * 正常结束事务
	 * 给service层结束事务功能
	 * 保证service层使用的Connection对象和开始事务的Connection对象是一致的
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		Connection con = tc.get();
		//防止重复结束事务而导致的空指针异常
		if(con == null){
			throw new SQLException("还没开启事务，不能提交！");
		}
		con.commit();
		con.close();
		con = null; //关闭的con不能继续使用，应该置空con，让第二次调用时创建新的con
					//同时，con里面无值，说明事务结束
		
		//移除自己线程的con
		tc.remove();
	}
	
	/**
	 * 事务发生异常后回滚事务（重置事务内容）
	 * 给service层回滚事务功能
	 * 保证service层使用的Connection对象和开始事务的Connection对象是一致的
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException{
		Connection con = tc.get();
		//防止重复结束事务而导致的空指针异常
		if(con == null){
			throw new SQLException("还没开启事务，不能回滚！");
		}
		con.rollback();
		con.close();
		con = null; //同上
		
		//移除自己线程的con
		tc.remove();
	}
	
	/**
	 * 释放连接，用于无事务的普通业务的释放资源
	 * @param con2
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection con = tc.get();
		//先判断是否是事务的连接，如果专用连接为空，则无事务开启，则该连接是自己创的，自己释放
		if(con == null)	connection.close();
		//如果专用连接不为空，判断连接是否相同，不相同则是自己创的，自己释放
		if(con != connection) connection.close();
	}
}
