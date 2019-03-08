package junit.test;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Map;

import org.junit.Test;

import cn.library.admin.book.dao.BookDao;
import cn.library.admin.book.domain.Book;
import cn.library.paging.domain.PageBean;

public class testBookDao {
	
	@Test
	public void addBook(){
		//添加图书
		BookDao bookDao = new BookDao();
		Book book = new Book();
		book.setAuthor("万一");
		book.setBid("222");
		book.setBindex("eae2");
		book.setBname("自然学");
		book.setBtype("科学");
		book.setPubdate("2018.11");
		book.setPublish("无名氏出版社");
		bookDao.addBook(book);
	}
	
	@Test
	public void findBook(){
		BookDao bookDao = new BookDao();
		Boolean b = bookDao.findBy3info("自然学", "万一", "2018.11");
		System.out.println(b);
	}
	
	@Test
	public void showAllBook(){
		BookDao bookDao = new BookDao();
		PageBean<Book> showAllBook = bookDao.showAllBook(2,10);
		for(Map.Entry<String, Book> en : showAllBook.getBeanMap().entrySet()){
			Book value = en.getValue();
			System.out.println(value.getBname() + value.getAuthor());
		}
	}
	
	@Test
	public void showBook(){
		BookDao bookDao = new BookDao();
		PageBean<Book> books = bookDao.showBook("中国",1,15);
		for(Map.Entry<String, Book> en : books.getBeanMap().entrySet()){
			Book value = en.getValue();
			System.out.println(value.getBname() + "\t" + value.getAuthor());
		}
//		System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(new Date()));
//		DateFormat df3 = new SimpleDateFormat("yyy-MM-dd HH-mm-ss")
//		DateFormat df = new SimpleDateFormat("yyy-MM-dd");
//		String format = df.format(new Date());
//		System.out.println(format);
	}
	
	@Test
	public void deleteBook(){
		BookDao bookDao = new BookDao();
		bookDao.deleteBook("222");
	}
	
	@Test
	public void findByPubdate(){
		BookDao bookDao = new BookDao();
		PageBean<Book> books = bookDao.findByPubdate("2019", "2018",1,15);
		for(Map.Entry<String, Book> en : books.getBeanMap().entrySet()){
			Book value = en.getValue();
			System.out.println(value.getBname() + value.getAuthor());
		}
		System.out.println(3/15);
	}
}
