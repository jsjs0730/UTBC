/**
 * 
 */
package tk.utbc.service;

import tk.utbc.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public interface PointService {
	
	//추천 여부 확인
	public int chkVoteCount(PointCycleLogVO pclvo) throws Exception;
	
	//추천시 로그기록
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception;

	//추천시 업데이트
	public void updateVote(int bnum, int vlike, int dislike) throws Exception;
	
	/*//작성에 의한 포인트 계산
	public int calculatingPoint(String chk) throws Exception;*/
	
	//포인트 업데이트
	public void updatePoint(String uid, int ipoint) throws Exception;
	
	//삭제시 로그삭제(글) - 내가 쓴 글 삭제
	public void deleteBoardPointLog(PointCycleLogVO pclvo) throws Exception;
	
	//삭제시 로그삭제(댓글) - 내가 쓴 글 삭제
	public void deleteReplyPointLog(PointCycleLogVO pclvo) throws Exception;
	
	
	//포인트 관련 uname, usernick을 받아 uid로 변환
	public String chkUid(String usernick) throws Exception;
	//글번호로  usernick 찾기
	public String chkUsernick(int bnum) throws Exception;
	//댓글번호로  usernick 찾기
	public String chkUsernickForReply(int rnum) throws Exception;
			
}
