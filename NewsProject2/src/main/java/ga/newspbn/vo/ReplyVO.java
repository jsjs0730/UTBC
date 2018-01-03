/**
 * 
 */
package ga.newspbn.vo;

import java.util.Date;

/**
 * @author KEN
 *	Park Jong-hyun
 */
/**
 * @author KEN
 * Park Jong-hyun
 * 2018. 01. 02 
 */
public class ReplyVO {
//	rnum int NOT null auto_increment,
//	bnum int not null default 0,
//	depth int default 0, ##부모글은 1부터 시작, 자식이 되는 순간 부모의 depth+1
//	seq varchar(20000), ##같은 주제를 갖는 게시물의 고유번호 부모글, 부모글로부터 파생된 모든 자식글은 같은번호
//	replytext varchar(1000) not null,
//	replyer varchar(50) not null,
//	regdate timestamp not null default now(),
//	updatedate timestamp not null default now(),
//	primary key(rnum)
	
	private Integer rnum;
	private Integer bnum;
	private int idx;//1,2,3,4,5,6,7
	private String depth;// 1/1/1/1 10단계
	private int depthCnt;// '@' 개수 카운팅
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
	private String uid;
	
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
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	
	public int getDepthCnt() {
		return depthCnt;
	}
	public void setDepthCnt(int depthCnt) {
		this.depthCnt = depthCnt;
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
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [rnum=" + rnum + ", bnum=" + bnum + ", idx=" + idx + ", depth=" + depth + ", depthCnt="
				+ depthCnt + ", replytext=" + replytext + ", replyer=" + replyer + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + ", uid=" + uid + "]";
	}
	
	
	
		
}
