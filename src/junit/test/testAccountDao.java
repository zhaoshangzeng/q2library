package junit.test;


import org.junit.Test;

import cn.library.admin.account.dao.AccountDao;

public class testAccountDao {

	@Test
	public void testFind(){
		AccountDao ad = new AccountDao();
		String pass = ad.findPassword("q2library");
		System.out.println(pass);
	}
	
	@Test
	public void testUpdate(){
		AccountDao ad = new AccountDao();
		ad.updateAccount("q2library", "q2library");
		System.out.println(ad.findPassword("q2library"));
	}
}
