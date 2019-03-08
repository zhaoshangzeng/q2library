package cn.library.admin.inform.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.library.admin.inform.domain.Inform;
import cn.library.utils.TxQueryRunner;

/**
 * inform的持久层
 * @author zzshang
 *
 */
public class InformDao {
	
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据标题和日期判断是否重复
	 * @param title
	 * @param itime
	 * @return
	 */
	public Boolean findBy2info(String title, String itime) {
		try{
			//sql语句
			String sql = "SELECT * FROM inform WHERE title=? AND itime=?";
			//参数
			Object[] params = {title,itime};
			//执行
			Inform inform = qr.query(sql, new BeanHandler<Inform>(Inform.class), params);
			//返回
			return inform != null;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加通知
	 * @param inform
	 */
	public void addInform(Inform inform) {
		try{
			//sql语句
			String sql = "INSERT INTO inform VALUES(?,?,?,?)";
			//参数
			Object[] params = {inform.getIid(),inform.getTitle(),
					inform.getContent(),inform.getItime()};
			//执行
			qr.update(sql, params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有通知
	 * @return
	 */
	public Map<String, Inform> findInform() {
		try{
			//sql语句
			String sql = "SELECT * FROM inform ORDER BY itime DESC";
			//执行
			List<Inform> list = qr.query(sql, new BeanListHandler<Inform>(Inform.class));
			//返回
			Map<String, Inform> map = new LinkedHashMap<String, Inform>();
			for(Inform inform : list){
				map.put(inform.getIid(), inform);
			}
			return map;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据id找通知
	 * @param iid
	 * @return
	 */
	public Inform findById(String iid) {
		try{
			//sql语句
			String sql = "SELECT * FROM inform WHERE iid=?";
			//参数
			Object param = iid;
			//执行
			Inform inform = qr.query(sql, new BeanHandler<Inform>(Inform.class), param);
			//返回
			return inform;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除通知
	 * @param delid
	 */
	public void deleteInform(String delid) {
		try{
			//sql语句
			String sql = "DELETE FROM inform WHERE iid=?";
			//参数
			Object param = delid;
			//执行
			qr.update(sql, param);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
