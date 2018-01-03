/**
 * 
 */
package ga.newspbn.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public class PageMaker {
	private int totalDataCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;	//화면에 표시되는 페이지 번호 개수
	
	private Criteria cri;
	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
		
		calcData();
	}
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	private void calcData() {
									//페이지번호 / 화면에 표시될 페이지번호수
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage - displayPageNum)+1;
		
		int tempEndPage = (int)(Math.ceil(totalDataCount/(double)cri.getPerPageNum()));
		
		if(endPage>tempEndPage){
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalDataCount ? false : true;
			
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalDataCount() {
		return totalDataCount;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalDataCount=" + totalDataCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	public String makeQuery(int page){
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("bname", cri.getBname())
				.build();
		
		return uriComponents.toString(); 
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("bname", cri.getBname())
				.queryParam("searchTarget", ((SearchCriteria)cri).getSearchTarget())
				.queryParam("searchKeyword", ((SearchCriteria)cri).getSearchKeyword())
				.build();
		return uriComponents.toString();
	}
	
	private String encoding(String searchKeyword) {
		if(searchKeyword == null || searchKeyword.trim().length() == 0 ) {
			return "";
		}
		try {
			return URLEncoder.encode(searchKeyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
}
