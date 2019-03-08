package cn.library.paging.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 用于分页的javabean
 * @author zzshang
 *
 * 分工流程：
 * 页面：给出相关的链接们
 * servlet：1.获取当前页面，即pc
 * 		   2.指定ps，即指定每页记录数
 * 		   3.传递给service的参数是pc，和ps，service返回PageBean对象
 * 		   4.把PageBean保存到request域，转发给页面
 * service：即Dao方法的包装
 * Dao：
 * 		1.得到pc和ps，创建出PageBean
 * 		2.要查询数据库得到tr，和beanMap
 * 		3.返回PageBean
 * 		操作：
 *		tr：select count(*) t_book
 * 	   beanList：select * from t_book limit x,y;
 * 理解：即在原查询beanMap的基础上，再查询tr，而且还有修改原查询，修改成有limit的sql语句。
 */
public class PageBean<T> implements Serializable{
	
	private static final long serialVersionUID = 1L; //序列化id
	
	private int pc; //当前页码page code （jsp请求时会给）
//	private int tp; //总页数total page //因为是固定值，所以没必要设置变量
	private int tr; //总记录数total record （到数据库查询COUNT(*)，获得Number类型，intValue转）
	private int ps; //每页记录数page size （根据页面大小，自己指定）
	private Map<String,T> beanMap; //当前页的记录	  （用pc和ps，到数据库查询limit (pc-1)*ps,ps）
	
	private String url; //当使用条件查询时，它是url后面的条件
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	/**
	 * 返回总页数
	 * @return
	 */
	public int getTp() {
		int tp = tr / ps;
		tp = (tr%ps == 0 ? tp : tp + 1);  //默认是相除结果再加1，但遇到刚刚好满一页的情况，如10/10，则不加
		return tp;
	}
//	public void setTp(int tp) {	//避免外界设置它，因为它是固定的
//		this.tp = tp;
//	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public Map<String, T> getBeanMap() {
		return beanMap;
	}
	public void setBeanMap(Map<String, T> beanMap) {
		this.beanMap = beanMap;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanMap == null) ? 0 : beanMap.hashCode());
		result = prime * result + pc;
		result = prime * result + ps;
		result = prime * result + tr;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageBean<?> other = (PageBean<?>) obj;
		if (beanMap == null) {
			if (other.beanMap != null)
				return false;
		} else if (!beanMap.equals(other.beanMap))
			return false;
		if (pc != other.pc)
			return false;
		if (ps != other.ps)
			return false;
		if (tr != other.tr)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps
				+ ", beanMap=" + beanMap + ", url=" + url + "]";
	}
	
	
	
}
