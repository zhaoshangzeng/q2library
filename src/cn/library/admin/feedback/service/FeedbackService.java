package cn.library.admin.feedback.service;

import java.util.Map;

import cn.library.admin.feedback.dao.FeedbackDao;
import cn.library.admin.feedback.domain.Feedback;

/**
 * feedback的业务层
 * @author zzshang
 *
 */
public class FeedbackService {
	/**
	 * 依赖对象
	 */
	private FeedbackDao feedbackDao = new FeedbackDao();

	/**
	 * 查找所有反馈
	 * @return
	 */
	public Map<String, Feedback> showFeedback() {
		return feedbackDao.findFeedback();
	}

	/**
	 * 根据id查找对象
	 * @param fid
	 * @return
	 */
	public Feedback findById(String fid) {
		return feedbackDao.findById(fid);
	}

	/**
	 * 删除反馈
	 * @param delid
	 */
	public void deleteFeedback(String delid) {
		feedbackDao.deleteFeedback(delid);
	}

}
