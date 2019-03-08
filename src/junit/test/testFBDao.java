package junit.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.library.admin.feedback.dao.FeedbackDao;
import cn.library.admin.feedback.domain.Feedback;

public class testFBDao {
	FeedbackDao fdao = null;
	@Before
	public void getServiceObject(){
		fdao = new FeedbackDao();
	}
	
	@Test
	public void testShow(){
		Map<String, Feedback> map = fdao.findFeedback();
		for(Map.Entry<String, Feedback> en : map.entrySet()){
			Feedback value = en.getValue();
			System.out.println(value.getTitle());
		}
	}
	
	@Test
	public void testFind(){
		Feedback feedback = fdao.findById("425120365D1347D4991A662E22E50646");
		System.out.println(feedback);
	}
	
	@Test
	public void testDelete(){
		fdao.deleteFeedback("22");
		testShow();
	}
	
	@Test
	public void testAdd(){
		Feedback feedback = new Feedback();
		feedback.setFid("22");
		feedback.setTitle("反馈标题");
		feedback.setContent("反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容"
				+ "内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈"
				+ "内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容"
				+ "反馈内容内容反馈内容内容反馈内容内容反馈内容内容反馈内容内容");
		feedback.setPhone("123123123");
		feedback.setFtime("2019-1-1");
		fdao.addFeedback(feedback);
		testShow();
	}
}
