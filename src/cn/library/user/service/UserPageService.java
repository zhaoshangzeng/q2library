package cn.library.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.library.admin.account.dao.AccountDao;
import cn.library.admin.book.dao.BookDao;
import cn.library.admin.feedback.dao.FeedbackDao;
import cn.library.admin.feedback.domain.Feedback;
import cn.library.admin.inform.dao.InformDao;
import cn.library.admin.inform.domain.Inform;
import cn.library.admin.book.domain.Book;
import cn.library.paging.domain.PageBean;

/**
 * 负责非管理员界面的业务
 * @author zzshang
 *
 */
public class UserPageService {
	/**
	 * 依赖对象
	 */
	private BookDao bookDao = new BookDao();
	private InformDao informDao = new InformDao();
	private FeedbackDao feedbackDao = new FeedbackDao();
	private AccountDao accountDao = new AccountDao();
	
	/**
	 * 添加反馈
	 * @param feedback
	 */
	public void addFeedback(Feedback feedback) {
		feedbackDao.addFeedback(feedback);
	}

	/**
	 * 查找新书
	 * @return
	 */
	public PageBean<Book> showNewBooks(int pc, int ps) {
		//获取今年年份的字符串形式
		String year = new SimpleDateFormat("yyy").format(new Date());
		int realYear = Integer.parseInt(year);
		realYear = realYear - 1;
		//获取去一年年份的字符串形式
		String lastYear = Integer.toString(realYear);
		//进行匹配查找
		return bookDao.findByPubdate(year, lastYear, pc, ps);
	}

	/**
	 * 查看所有通知
	 * @return
	 */
	public Map<String, Inform> showInform() {
		return informDao.findInform();
	}

	/**
	 * 根据id获得通知内容（通知对象）
	 * @param iid
	 * @return
	 */
	public Inform findById(String iid) {
		return informDao.findById(iid);
	}

	/**
	 * 根据账号找密码
	 * @param aname
	 * @return
	 */
	public String findPassword(String aname) {
		return accountDao.findPassword(aname);
	}

	/**
	 * 模糊查询图书
	 * @param bookName
	 * @return
	 */
	public PageBean<Book> showBook(String bookName, int pc, int ps) {
		return bookDao.showBook(bookName, pc, ps);
	}
}
