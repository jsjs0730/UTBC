/**
 * 
 */
package ga.newspbn.vo;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public class Criteria {
	
	private String bname; //게시판 위치
	private int page;	//페이지 번호
	private int perPageNum; //한페이지에 표시될 데이터의 수
	/**
	 * @param page
	 * @param perPageNum
	 * 
	 * Criteria
	 * 1 (판단,비판의) 표준, 규범, 척도, 기준 ((of, for)) 
	 * 2 특징
	 */
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page<=0){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	/// MyBatis SQL Mapper를 위한 메소드
	public int getPageStart(){//limit 시작위치를 정하기 위함
		return (this.page-1) * perPageNum;
	}
	
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
	

}
