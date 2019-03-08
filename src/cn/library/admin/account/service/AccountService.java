package cn.library.admin.account.service;

import cn.library.admin.account.dao.AccountDao;

public class AccountService {

	/**
	 * 依赖对象
	 */
	private AccountDao accountDao = new AccountDao();

	/**
	 * 根据账号查找密码
	 * @param string
	 * @return
	 */
	public String findPassword(String name) {
		return accountDao.findPassword(name);
	}

	/**
	 * 修改账号密码
	 * @param string
	 * @param newpass
	 */
	public void updateAccount(String name, String newpass) {
		accountDao.updateAccount(name,newpass);
	}
}
