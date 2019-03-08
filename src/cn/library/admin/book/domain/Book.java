package cn.library.admin.book.domain;

import java.io.Serializable;

/**
 * Book-书的领域对象
 * @author zzshang
 * 
 */
public class Book implements Serializable{
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 对应数据库表
	 * 主键
	 */
	private String bid;
	/**
	 * 书名
	 */
	private String bname;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版社
	 */
	private String publish;
	/**
	 * 出版时间
	 */
	private String pubdate;
	/**
	 * 书籍类型
	 */
	private String btype;
	/**
	 * 存放位置索引
	 */
	private String bindex;
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getBindex() {
		return bindex;
	}
	public void setBindex(String bindex) {
		this.bindex = bindex;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((bindex == null) ? 0 : bindex.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((btype == null) ? 0 : btype.hashCode());
		result = prime * result + ((publish == null) ? 0 : publish.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (bindex == null) {
			if (other.bindex != null)
				return false;
		} else if (!bindex.equals(other.bindex))
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (btype == null) {
			if (other.btype != null)
				return false;
		} else if (!btype.equals(other.btype))
			return false;
		if (publish == null) {
			if (other.publish != null)
				return false;
		} else if (!publish.equals(other.publish))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", author=" + author
				+ ", publish=" + publish + ", btype=" + btype + ", bindex="
				+ bindex + "]";
	}
	
	
}
