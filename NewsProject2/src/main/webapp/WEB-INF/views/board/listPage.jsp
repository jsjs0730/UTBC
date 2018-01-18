<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<style>
.info{font-size: 11px;line-height: 11px;}
.itm{padding-right: 0;border: 0;display: inline-block;margin: 5px 0px 0 0;}
.cmt{color :#f35b16;font-weight: bold}

</style>

<div class="content container text-center">    
	  <div class="row">
	    <div class="col-sm-8 text-left">
			<c:forEach items="${list }" var="boardVO">
				<a href="/board/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bnum=${boardVO.bnum}" style="color:#000"><h3>${boardVO.title }</h3>
				<c:choose>
					<c:when test="${fn:length(boardVO.content)>50 }">
						<p>${fn:escapeXml(fn:substring(boardVO.content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "), 0, 30))}...&lt;생략&gt;</p>
					</c:when>
					<c:otherwise>
						<p>${fn:escapeXml(boardVO.content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "))  }</p>
					</c:otherwise>
				</c:choose>   </a>  
				<div class="info">		
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp; <a style="color:#888">${boardVO.usernick }</a> <span style="color:#bbb">&nbsp; |  &nbsp; </span>
					<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; <span class="itm"><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></span> <span style="color:#bbb">&nbsp; |  &nbsp; </span>
					<i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.vlike }</span><span style="color:#bbb">&nbsp; |  &nbsp; </span>
					<i class="fa fa-eye" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.viewcnt }</span><span style="color:#bbb">&nbsp; |  &nbsp; </span>
					<i class="fa fa-barcode" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.bnum }</span><span style="color:#bbb">&nbsp; |  &nbsp; </span>
					<i class="fa fa-commenting-o" aria-hidden="true"></i>&nbsp;	<span class="itm cmt">${boardVO.replycnt }</span>			
				</div>
	     	 <hr>
			</c:forEach>		
  	 		<select name="searchType">
				<option value="n"<c:out value="${cri.searchTarget == null ? 'selected' : '' }"/>><!-- 없음 -->
					----
				</option>
				<option value="t"<c:out value="${cri.searchTarget eq 't' ? 'selected' : '' }"/>><!-- 제목 -->
				제목
				</option>
				
				<option value="c"<c:out value="${cri.searchTarget eq 'c' ? 'selected' : '' }"/>><!-- 내용 -->
				내용
				</option>
				
				<option value="u"<c:out value="${cri.searchTarget eq 'u' ? 'selected' : '' }"/>><!-- 작성자 -->
				닉네임
				</option>
				
				<option value="tc"<c:out value="${cri.searchTarget eq 'tc' ? 'selected' : '' }"/>><!-- 제목이나 내용 -->
				제목 + 내용
				</option>
				
				<option value="cu"<c:out value="${cri.searchTarget eq 'cu' ? 'selected' : '' }"/>><!-- 내용이나 작성자 -->
				내용 + 닉네임
				</option>
				
				<option value="tcu"<c:out value="${cri.searchTarget eq 'tcu' ? 'selected' : '' }"/>><!-- 제목이나 내용 혹은 작성자로 검색 -->
				제목 + 내용 + 닉네임
				</option>			
			</select>
			<input type="text" name="searchKeyword" id="keywordInput" value="${cri.searchKeyword }">
			<button id="searchBtn">Search</button>
			<sec:authorize access="isAuthenticated()">
				<button id="newBtn">New Board</button>
			</sec:authorize>
			<div class="row">
		  	 	<div class="col-sm-12 text-center">
					<ul class="pager pagination">
				      <c:if test="${pageMaker.prev }">
				     	<li><a href="listPage${pageMaker.makeSearch(pageMaker.startPage - 1 )}">&laquo;</a></li>
				      </c:if>
				      <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
						  	<li <c:out value="${pageMaker.cri.page == idx ? 'class = active' : '' }"/>>
						  		<a href="listPage${pageMaker.makeSearch(idx) }">${idx }</a>
						  	</li>
					  </c:forEach>	
					  <c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
						  	<li><a href="listPage${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>	  
					  </c:if>
					</ul>
		  	 	</div>
   	 		</div>	
	   </div>
	   <div class="col-sm-4">
			<div class="well text-center">
				<p class="lead">
					Don't want to miss updates? Please click the below button!
				</p>
				<button class="btn btn-primary btn-lg">Subscribe to my feed</button>
			</div>
			<!-- Latest Posts -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Latest Posts</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="singlepost.html">1. Aries Sun Sign March 21 - April 19</a></li>
					<li class="list-group-item"><a href="singlepost.html">2. Taurus Sun Sign April 20 - May 20</a></li>
					<li class="list-group-item"><a href="singlepost.html">3. Gemini Sun Sign May 21 - June 21</a></li>
					<li class="list-group-item"><a href="singlepost.html">4. Cancer Sun Sign June 22 - July 22</a></li>
					<li class="list-group-item"><a href="singlepost.html">5. Leo Sun Sign July 23 - August 22 </a></li>
				</ul>
			</div>
			<!-- Categories -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Categories</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">Signs</a></li>
					<li class="list-group-item"><a href="#">Elements</a></li>
					<li class="list-group-item"><a href="#">Planets</a></li>
					<li class="list-group-item"><a href="#">Cusps</a></li>
					<li class="list-group-item"><a href="#">Compatibility</a></li>
				</ul>
			</div>
			<!-- Tags -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Tags</h4>
				</div>
				<div class="panel-body">
					<ul class="list-inline">
						<li><a href="#">Aries</a></li>
						<li><a href="#">Fire</a></li>
						<li><a href="#">Mars</a></li>
						<li><a href="#">Taurus</a></li>
						<li><a href="#">Earth</a></li>
						<li><a href="#">Moon</a></li>
						<li><a href="#">Gemini</a></li>
						<li><a href="#">Air</a></li>
						<li><a href="#">Mercury</a></li>
						<li><a href="#">Cancer</a></li>
					</ul>
				</div>
			</div>
			<!-- Recent Comments -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Recent Comments</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">I don't believe in astrology but still your writing style is really great! - <em>john</em></a></li>
					<li class="list-group-item"><a href="#">Wow.. what you said is really true! I'm an aries though - <em>Anto</em></a></li>
					<li class="list-group-item"><a href="#">Does capricorn and aries is compatibile? - <em>Sarah</em></a></li>
					<li class="list-group-item"><a href="#">I'm a cancer woman been in love with Leo. Will this work? - <em>Mary</em></a></li>
				</ul>
			</div>
		</div> 
	 </div>
  	 
</div>

<script>
	var result = '${msg}';
	if (result == 'success') {
		console.log("처리가 완료되었습니다.");
	}
</script>

<script>
	$(document).ready(
			function() {
				$('#searchBtn').on(
						"click",
						function(event) {
							self.location = "listPage"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchTarget="
									+ $("select option:selected").val()
									+ "&searchKeyword=" + $('#keywordInput').val();
						});
				$('#newBtn').on("click", function(evt) {
					self.location = "insert?bname=${cri.bname}";
				});
			});
</script>

<%@ include file="../include/footer.jsp" %>