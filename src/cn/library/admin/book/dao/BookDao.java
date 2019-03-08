package cn.library.admin.book.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.library.admin.book.domain.Book;
import cn.library.paging.domain.PageBean;
import cn.library.utils.TxQueryRunner;

/**
 * Book的持久层
 * @author zzshang
 *
 */
public class BookDao {
	//1.获得dbutil的工具类
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据书名、作者、出版日期三信息判断是否是重复图书
	 * @param bname
	 * @param author
	 * @param pubdate
	 * @return
	 */
	public Boolean findBy3info(String bname, String author, String pubdate) {
		try{
			//sql语句
			String sql = "SELECT * FROM book WHERE bname=? AND author=? AND pubdate=?";
			//参数
			Object[] params = {bname,author,pubdate};
			//执行
			Book book = qr.query(sql, new BeanHandler<Book>(Book.class), params);
			//返回
			return book != null;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加图书
	 * @param book
	 */
	public void addBook(Book book) {
		try{
			//sql语句
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
			//参数
			Object[] params = {book.getBid(),book.getBname(),book.getAuthor(),
					book.getPublish(),book.getPubdate(),book.getBtype(),
					book.getBindex()};
			//执行
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有书，返回Map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<Book> showAllBook(int pc, int ps) {
		//流程
		//1创建PageBean<Book>对象pb
		//2设置pc和ps
		//3得到tr，设置给pb
		//4返回pb
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPc(pc);
		pb.setPs(ps);
		try{
			//查询tr总记录数
			//sql语句
			String sql = "SELECT COUNT(*) FROM book";
			//执行
			Number number = qr.query(sql, new ScalarHandler());
			//获得tr
			int tr = number.intValue();
			pb.setTr(tr);
			
			//限制行数来查询所有图书
			//sql语句
			String sql2 = "SELECT * FROM book ORDER BY pubdate DESC LIMIT ?,?";
			//参数
			Object[] params = {(pc-1)*ps, ps};
			//执行
			List<Book> list = qr.query(sql2, new BeanListHandler<Book>(Book.class), params);
			//得到每一页的书记录，存到map方便用作其他页面进行删除操作
			Map<String, Book> map = new LinkedHashMap<String, Book>();
			for(Book book : list){
				map.put(book.getBid(), book);
			}
			
			pb.setBeanMap(map);
			return pb;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据书名进行模糊查询
	 * @param bookName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<Book> showBook(String bookName, int pc, int ps) {
		//流程
		//1创建PageBean<Book>对象pb
		//2设置pc和ps
		//3得到tr，设置给pb
		//4返回pb
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPc(pc);
		pb.setPs(ps);
		try{
			//查询tr总记录数
			//sql语句
			String sql = "SELECT COUNT(*) FROM book WHERE bname LIKE ?";
			//参数
			Object param = bookName;
			//执行
			Number number = qr.query(sql, new ScalarHandler(), ("%"+param+"%"));
			//获得tr
			int tr = number.intValue();
			pb.setTr(tr);
			
			//sql语句
			String sql2 = "SELECT * FROM book WHERE bname LIKE ? ORDER BY pubdate DESC LIMIT ?,?";
			//参数
			Object[] params = {bookName, (pc-1)*ps, ps};
			//执行
			List<Book> list = qr.query(sql2, new BeanListHandler<Book>(Book.class), ("%"+params[0]+"%"),params[1],params[2]);
			//返回
			Map<String, Book> map = new LinkedHashMap<String, Book>();
			for(Book book : list){
				map.put(book.getBid(), book);
			}
			
			pb.setBeanMap(map);
			return pb;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据id删除图书
	 * @param delid
	 */
	public void deleteBook(String delid) {
		try{
			//sql语句
			String sql = "DELETE FROM book WHERE bid=?";
			//参数
			Object[] params = {delid};
			//执行
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据出版年份进行模糊查找
	 * @param year
	 * @param realYear
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean<Book> findByPubdate(String year, String lastYear, int pc, int ps) {
		//流程
		//1创建PageBean<Book>对象pb
		//2设置pc和ps
		//3得到tr，设置给pb
		//4返回pb
		PageBean<Book> pb = new PageBean<Book>();
		pb.setPc(pc);
		pb.setPs(ps);
		try{
			//查询tr总记录数
			//sql语句
			String sql = "SELECT COUNT(*) FROM book WHERE pubdate LIKE ? OR pubdate LIKE ?";
			//参数
			Object[] param = {year, lastYear};
			//执行
			Number number = qr.query(sql, new ScalarHandler(), (param[0]+"%"),(param[1]+"%"));
			//获得tr
			int tr = number.intValue();
			pb.setTr(tr);
			
			//sql语句
			String sql2 = "SELECT * FROM book WHERE pubdate LIKE ? OR pubdate LIKE ? ORDER BY pubdate DESC LIMIT ?,?";
			//参数
			Object[] param2 = {year, lastYear, (pc-1)*ps, ps};
			//执行
			List<Book> list = qr.query(sql2, new BeanListHandler<Book>(Book.class), (param2[0]+"%"),(param2[1]+"%"),param2[2],param2[3]);
			//返回
			Map<String, Book> map = new LinkedHashMap<String, Book>();
			for(Book book : list){
				map.put(book.getBid(), book);
			}
			
			pb.setBeanMap(map);
			return pb;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
