package cn.library.admin.book.service;

import cn.library.admin.book.dao.BookDao;
import cn.library.admin.book.domain.Book;
import cn.library.paging.domain.PageBean;

/**
 * Book的业务层
 * @author zzshang
 *
 */
public class BookService {
	/**
	 * 依赖对象
	 */
	private BookDao bookDao = new BookDao();

	/**
	 * 添加图书
	 * @param book
	 * @return
	 */
	public Boolean addBook(Book book) {
		//先判断该书是否存在与数据库
		Boolean b = bookDao.findBy3info(book.getBname(), book.getAuthor(), book.getPubdate());
		
		//判断有则返回false，无则添加，再返回true
		if(b){
			return false;
		}
		bookDao.addBook(book);
		return true;
	}

	/**
	 * 查找所有图书
	 * @return
	 */
	public PageBean<Book> showAllBook(int pc, int ps) {
		return bookDao.showAllBook(pc, ps);
	}

	/**
	 * 根据书名模糊查找
	 * @param bookName
	 * @return
	 */
	public PageBean<Book> showBook(String bookName, int pc, int ps) {
		return bookDao.showBook(bookName, pc, ps);
	}

	/**
	 * 删除相应的书
	 * @param delid
	 */
	public void deleteBook(String delid) {
		bookDao.deleteBook(delid);
	}

	/**
	 * 修改相应的书
	 * @param book
	 */
	public void updateBook(Book book) {
		//先根据bid查到数据库的书，进行删除
		bookDao.deleteBook(book.getBid());
		
		//再添加book到数据库
		bookDao.addBook(book);
	}
	
}
