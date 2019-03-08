package junit.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.library.admin.inform.dao.InformDao;
import cn.library.admin.inform.domain.Inform;

public class testInformDao {
	
	InformDao idao = null;
	@Before
	public void getServiceObject(){
		idao = new InformDao();
	}
	
	@Test
	public void testAdd(){
		Inform inform = new Inform();
		inform.setIid("3333");
		inform.setTitle("标题");
		inform.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"
				+ "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内"
				+ "容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
		inform.setItime("2018-9-1");
		idao.addInform(inform);
	}
	
	@Test
	public void testFind(){
		Boolean b = idao.findBy2info("标题", "2018-9-1");
		System.out.println(b);
	}
	
	@Test
	public void testFind2(){
		Inform inform = idao.findById("3333");
		System.out.println(inform);
	}
	
	@Test
	public void testFind3(){
		Map<String, Inform> findInform = idao.findInform();
		for(Map.Entry<String, Inform> en : findInform.entrySet()){
			Inform value = en.getValue();
			System.out.println(value.getTitle());
		}
	}
	
	@Test
	public void testDelete(){
		idao.deleteInform("3333");
		testFind3();
	}
}
