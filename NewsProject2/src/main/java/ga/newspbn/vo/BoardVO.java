/**
 * 
 */
package ga.newspbn.vo;

import java.util.Arrays;
import java.util.Date;

/**
 * @author KEN
 *	Park Jong-hyun
 */

//bno int not null auto_increment,
//title varchar(200) not null,
//content text null,
//writer varchar(50) not null,
//regdate timestamp not null default now(),
//viewcnt int default 0,
public class BoardVO {

	private Integer bnum;
	private String bname; //정치 : politics, 사회 : society, 경제 : economy, 속보 : newsflash, 커뮤니티 : community
	private String usernick;
	private String title;
	private String content;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	private int like;
	private int dislike;
	private String del; //기본 : N, 삭제한 글은 Y
	private String[] files;
	private String uid;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getBnum() {
		return bnum;
	}
	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getUsernick() {
		return usernick;
	}
	public void setUsernick(String usernick) {
		this.usernick = usernick;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", bname=" + bname + ", usernick=" + usernick + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", viewcnt=" + viewcnt + ", replycnt=" + replycnt
				+ ", like=" + like + ", dislike=" + dislike + ", del=" + del + ", files=" + Arrays.toString(files) + "]";
	}

		
}
