/**
 * 
 */
package ga.newspbn.vo;

import java.util.Date;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class PointCycleLogVO {
	/*			uid varchar(50) not null, #로그인아이디
	bname varchar(30) not null,#게시판이름
	bnum int,
	rnum int,
	chk varchar(3) not null, #bck:글쓰기, rck:댓글작성, vck:추천
	timeclock timestamp not null default now()*/

	private String uid;
	private String bname;
	private int bnum;
	private int rnum;
	private String chk;
	private Date timeclock;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public Date getTimeclock() {
		return timeclock;
	}
	public void setTimeclock(Date timeclock) {
		this.timeclock = timeclock;
	}
	@Override
	public String toString() {
		return "PointCycleLogVO [uid=" + uid + ", bname=" + bname + ", bnum=" + bnum + ", rnum=" + rnum + ", chk=" + chk
				+ ", timeclock=" + timeclock + "]";
	}


}
