package cn.library.admin.feedback.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.library.admin.feedback.domain.Feedback;
import cn.library.utils.TxQueryRunner;

/**
 * feedback的持久层
 * @author zzshang
 *
 */
public class FeedbackDao {

	/**
	 * dbutils工具类
	 */
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查找反馈
	 * @return
	 */
	public Map<String, Feedback> findFeedback() {
		try{
			//sql语句
			String sql = "SELECT * FROM feedback ORDER BY ftime DESC";
			//执行
			List<Feedback> list = qr.query(sql, new BeanListHandler<Feedback>(Feedback.class));
			//返回
			Map<String, Feedback> map = new LinkedHashMap<String, Feedback>();
			for(Feedback feedback : list){
				map.put(feedback.getFid(), feedback);
			}
			return map;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据id查找反馈
	 * @param fid
	 * @return
	 */
	public Feedback findById(String fid) {
		try{
			//sql语句
			String sql = "SELECT * FROM feedback WHERE fid=?";
			//参数
			Object param = fid;
			//执行
			Feedback feedback = qr.query(sql, new BeanHandler<Feedback>(Feedback.class), param);
			//返回
			return feedback;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除反馈
	 * @param delid
	 */
	public void deleteFeedback(String delid) {
		try{
			//sql语句
			String sql = "DELETE FROM feedback WHERE fid=?";
			//参数
			Object param = delid;
			//执行
			qr.update(sql, param);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加反馈
	 * @param feedback
	 */
	public void addFeedback(Feedback feedback) {
		try{
			//sql语句
			String sql = "INSERT INTO feedback VALUES(?,?,?,?,?)";
			//参数
			Object[] params = {feedback.getFid(),feedback.getTitle(),
					feedback.getContent(),feedback.getPhone(),feedback.getFtime()};
			//执行
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
