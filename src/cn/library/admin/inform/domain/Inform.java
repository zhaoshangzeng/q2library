package cn.library.admin.inform.domain;

import java.io.Serializable;

/**
 * inform-馆内通知的领域对象
 * @author zzshang
 *
 */
public class Inform implements Serializable{
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 对应数据库表
	 * 主键
	 */
	private String iid;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 时间
	 */
	private String itime;
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getItime() {
		return itime;
	}
	public void setItime(String itime) {
		this.itime = itime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((iid == null) ? 0 : iid.hashCode());
		result = prime * result + ((itime == null) ? 0 : itime.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Inform other = (Inform) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (iid == null) {
			if (other.iid != null)
				return false;
		} else if (!iid.equals(other.iid))
			return false;
		if (itime == null) {
			if (other.itime != null)
				return false;
		} else if (!itime.equals(other.itime))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Inform [iid=" + iid + ", title=" + title + ", content="
				+ content + ", itime=" + itime + "]";
	}
	
}
