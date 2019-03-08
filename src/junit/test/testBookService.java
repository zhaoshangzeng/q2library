package junit.test;


import org.junit.Before;
import org.junit.Test;

import cn.library.admin.book.domain.Book;
import cn.library.admin.book.service.BookService;

public class testBookService {

	BookService bs = null;
	@Before
	public void getServiceObject(){
		bs = new BookService();
	}
	
	@Test
	public void addBook(){
		Book book = new Book();
		book.setAuthor("一万");
		book.setBid("333");
		book.setBindex("eae2");
		book.setBname("自然学");
		book.setBtype("科学");
		book.setPubdate("2018.11");
		book.setPublish("无名氏出版社");
		Boolean addBook = bs.addBook(book);
		System.out.println(addBook);
	}
	
//	@Test
//	public void showAllBook(){
//		Map<String, Book> books = bs.showAllBook();
//		for(Map.Entry<String, Book> en : books.entrySet()){
//			Book value = en.getValue();
//			System.out.println(value.getBname() + ": " + value.getAuthor());
//		}
//	}
	
//	@Test
//	public void showBook(){
//		Map<String, Book> books = bs.showBook("改革");
//		for(Map.Entry<String, Book> en : books.entrySet()){
//			Book value = en.getValue();
//			System.out.println(value.getBname() + ": " + value.getAuthor());
//		}
//	}
	
	@Test
	public void updateBook(){
		Book book = new Book();
		book.setAuthor("不是一万");
		book.setBid("333");
		book.setBindex("wwww");
		book.setBname("电脑学");
		book.setBtype("科学");
		book.setPubdate("2018.11");
		book.setPublish("无名氏出版社");
		bs.updateBook(book);
//		showAllBook();
	}

	@Test
	public void deleteBook(){
		bs.deleteBook("333");
//		showAllBook();
	}
	
//	@Test
//	public void findByDate(){
//		Map<String, Book> books = new UserPageService().showNewBooks();
//		for(Map.Entry<String, Book> en : books.entrySet()){
//			Book value = en.getValue();
//			System.out.println(value.getBname() + value.getAuthor());
//		}
//	}
}
