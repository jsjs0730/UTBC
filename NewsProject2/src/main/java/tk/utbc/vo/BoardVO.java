/**
 * 
 */
package tk.utbc.vo;

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
	private int vlike;
	private int dislike;
	private String del; //기본 : N, 삭제한 글은 Y
	private String[] files;
	
	private String uid;	//포인트에 사용될 변수 - 보안을 핑계(?)로 조인문으로 바로 부르진 않는다.
	private String filesrc;	//join문에서 사용될 변수 - 불러도 된다 이건
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getFilesrc() {
		return filesrc;
	}
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
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

	public int getVlike() {
		return vlike;
	}
	public void setVlike(int vlike) {
		this.vlike = vlike;
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
	
	
	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", bname=" + bname + ", usernick=" + usernick + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", viewcnt=" + viewcnt + ", replycnt=" + replycnt
				+ ", vlike=" + vlike + ", dislike=" + dislike + ", del=" + del + ", files=" + Arrays.toString(files)
				+ ", uid=" + uid + ", filesrc=" + filesrc + "]";
	}

		
}
