package junit.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.library.admin.feedback.domain.Feedback;
import cn.library.admin.feedback.service.FeedbackService;

public class testFBService {

	FeedbackService fs = null;
	@Before
	public void getServiceObject(){
		fs = new FeedbackService();
	}
	
	@Test
	public void testFind(){
		Feedback i = fs.findById("425120365D1347D4991A662E22E50646");
		System.out.println(i);
	}
	
	@Test
	public void testShow(){
		Map<String, Feedback> map = fs.showFeedback();
		for(Map.Entry<String, Feedback> en : map.entrySet()){
			Feedback value = en.getValue();
			System.out.println(value.getTitle());
		}
	}
}
