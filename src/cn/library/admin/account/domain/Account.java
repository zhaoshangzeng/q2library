package cn.library.admin.account.domain;

import java.io.Serializable;

/**
 * account-管理员账号的领域对象
 * @author zzshang
 *
 */
public class Account implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 对应数据库的表
	 * 主键
	 */
	private String aid;
	/**
	 * 管理员账号名
	 */
	private String aname;
	/**
	 * 密码
	 */
	private String apassword;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result
				+ ((apassword == null) ? 0 : apassword.hashCode());
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
		Account other = (Account) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (apassword == null) {
			if (other.apassword != null)
				return false;
		} else if (!apassword.equals(other.apassword))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", aname=" + aname + ", apassword="
				+ apassword + "]";
	}
}
