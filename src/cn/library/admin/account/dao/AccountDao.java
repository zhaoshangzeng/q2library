package cn.library.admin.account.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.library.admin.account.domain.Account;
import cn.library.utils.TxQueryRunner;

/**
 * account的持久层
 * @author zzshang
 *
 */
public class AccountDao {

	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据账号查找密码
	 * @param name
	 * @return
	 */
	public String findPassword(String name) {
		try{
			//sql语句
			String sql = "SELECT * FROM account WHERE aname=?";
			//参数
			Object param = name;
			//执行
			Account account = qr.query(sql, new BeanHandler<Account>(Account.class), param);
			//返回
			return account.getApassword();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改密码
	 * @param name
	 * @param newpass
	 */
	public void updateAccount(String name, String newpass) {
		try{
			//sql语句
			String sql = "UPDATE account SET apassword=? WHERE aname=?";
			//参数
			Object[] param = {newpass, name};
			//执行
			qr.update(sql, param);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
