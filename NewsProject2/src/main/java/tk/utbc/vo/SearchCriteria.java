/**
 * 
 */
package tk.utbc.vo;

/**
 * @author KEN
 *	Park Jong-hyun
 */
public class SearchCriteria extends Criteria{
	private String searchTarget;
	private String searchKeyword;
	public String getSearchTarget() {
		return searchTarget;
	}
	public void setSearchTarget(String searchTarget) {
		this.searchTarget = searchTarget;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchTarget=" + searchTarget + ", searchKeyword=" + searchKeyword + "]";
	}
	
	
}
