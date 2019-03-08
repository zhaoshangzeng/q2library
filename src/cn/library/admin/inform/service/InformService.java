package cn.library.admin.inform.service;

import java.util.Map;

import cn.library.admin.inform.dao.InformDao;
import cn.library.admin.inform.domain.Inform;

/**
 * inform的业务层
 * @author zzshang
 *
 */
public class InformService {
	/**
	 * 依赖对象
	 */
	private InformDao informDao = new InformDao();

	/**
	 * 添加通知
	 * @param inform
	 */
	public boolean addInform(Inform inform) {
		Boolean b = informDao.findBy2info(inform.getTitle(), inform.getItime());
		
		//判断有则返回false，无则添加，再返回true
		if(b){
			return false;
		}
		informDao.addInform(inform);
		return true;
	}

	/**
	 * 查找所有通知
	 * @return
	 */
	public Map<String, Inform> showInform() {
		return informDao.findInform();
	}

	/**
	 * 根据iid查到inform对象
	 * @param iid
	 * @return
	 */
	public Inform findById(String iid) {
		return informDao.findById(iid);
	}

	/**
	 * 删除通知
	 * @param delid
	 */
	public void deleteInform(String delid) {
		informDao.deleteInform(delid);
	}

	/**
	 * 修改通知
	 * @param inform
	 */
	public void updateBook(Inform inform) {
		//先根据id查到数据库的通知，进行删除
		informDao.deleteInform(inform.getIid());
		
		//再添加通知到数据库
		informDao.addInform(inform);
	}
}
