/**
 * 
 */
package ga.newspbn.vo;

import java.util.Date;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public class ReplyVO {
//	rnum int NOT null auto_increment,
//	bnum int not null default 0,
//	grp int default 1, ##같은 주제를 갖는 게시물의 고유번호 부모글, 부모글로부터 파생된 모든 자식글은 같은번호
//	seq int default 1, ## 같은 그룹내 게시물의 순서
//	lvl int default 0, ##같은 그룹내 계층

//	replytext varchar(1000) not null,
//	replyer varchar(50) not null,
//	regdate timestamp not null default now(),
//	updatedate timestamp not null default now(),
	private Integer rnum;
	private Integer bnum;
	private int grp;
	private int lvl;
	
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
	
	
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public Integer getBnum() {
		return bnum;
	}
	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}
	
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "ReplyVO [rnum=" + rnum + ", bnum=" + bnum + ", grp=" + grp + ", lvl=" + lvl + ", replytext=" + replytext
				+ ", replyer=" + replyer + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
	
	
	
}
