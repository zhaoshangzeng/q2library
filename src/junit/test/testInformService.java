package junit.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.library.admin.inform.domain.Inform;
import cn.library.admin.inform.service.InformService;

public class testInformService {

	InformService is = null;
	@Before
	public void getServiceObject(){
		is = new InformService();
	}
	
	@Test
	public void testAdd(){
		Inform inform = new Inform();
		inform.setIid("555");
		inform.setTitle("标题");
		inform.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"
				+ "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内"
				+ "容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
		inform.setItime("2018-9-1");
		is.addInform(inform);
	}
	
	@Test
	public void testFind(){
		Inform i = is.findById("555");
		System.out.println(i);
	}
	
	@Test
	public void testFindAll(){
		Map<String, Inform> map = is.showInform();
		for(Map.Entry<String, Inform> en : map.entrySet()){
			Inform value = en.getValue();
			System.out.println(value.getTitle());
		}
	}
	
	@Test
	public void testUpdate(){
		Inform inform = new Inform();
		inform.setIid("555");
		inform.setTitle("标题2222");
		inform.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"
				+ "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内"
				+ "容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
		inform.setItime("2018-11-1");
		is.updateBook(inform);
		testFind();
	}
	
	@Test
	public void testDelete(){
		is.deleteInform("555");
		testFindAll();
	}
}
