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
//	parent int, ##부모가 없으면 null(지가 부모), 댓글, 대댓글은 모두 최초댓글(부모)의 번호,
//	depth int default 1, ##부모글은 1부터 시작, 자식이 되는 순간 부모의 depth+1
//	seq int default 1, ##같은 주제를 갖는 게시물의 고유번호 부모글, 부모글로부터 파생된 모든 자식글은 같은번호
//	replytext varchar(1000) not null,
//	replyer varchar(50) not null,
//	regdate timestamp not null default now(),
//	updatedate timestamp not null default now(),
	
	private Integer rnum;
	private Integer bnum;
	private int parent;
	private int depth;
	private int seq;
	
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


	public int getParent() {
		return parent;
	}


	public void setParent(int parent) {
		this.parent = parent;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
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
		return "ReplyVO [rnum=" + rnum + ", bnum=" + bnum + ", parent=" + parent + ", depth=" + depth + ", seq=" + seq
				+ ", replytext=" + replytext + ", replyer=" + replyer + ", regdate=" + regdate + ", updatedate="
				+ updatedate + "]";
	}
	
	
}
