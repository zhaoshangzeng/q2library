package cn.library.admin.feedback.domain;

import java.io.Serializable;

/**
 * feedback-读者反馈的领域对象
 * @author zzshang
 *
 */
public class Feedback implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 对应数据库的表
	 * 主键
	 */
	private String fid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 时间
	 */
	private String ftime;
	/**
	 * 
	 * @return
	 */
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fid == null) ? 0 : fid.hashCode());
		result = prime * result + ((ftime == null) ? 0 : ftime.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Feedback other = (Feedback) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (fid == null) {
			if (other.fid != null)
				return false;
		} else if (!fid.equals(other.fid))
			return false;
		if (ftime == null) {
			if (other.ftime != null)
				return false;
		} else if (!ftime.equals(other.ftime))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		return "Feedback [fid=" + fid + ", title=" + title + ", content="
				+ content + ", phone=" + phone + ", ftime=" + ftime + "]";
	}
	
}
