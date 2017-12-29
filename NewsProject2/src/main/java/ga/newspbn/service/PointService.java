/**
 * 
 */
package ga.newspbn.service;

import ga.newspbn.vo.PointCycleLogVO;

/**
 * @author KEN
 * Park Jong-hyun
 */
public interface PointService {
	//추천시 로그기록
	public void voteBoardPoint(PointCycleLogVO pclvo) throws Exception;
	//비추천시 로그기록
	public void voteBoardPointDis(PointCycleLogVO pclvo) throws Exception;
	//작성에 의한 포인트 계산
	public int calculatingPoint(String chk) throws Exception;
	
	//포인트 업데이트
	public void updatePoint(String uid, int ipoint) throws Exception;
	
	//삭제시 로그삭제(글)
	public void deleteBoardPointLog(PointCycleLogVO pclvo) throws Exception;
	
	
	//포인트 관련 uname, usernick을 받아 uid로 변환
	public String chkUid(String usernick) throws Exception;
	//글번호로  usernick 찾기
	public String chkUsernick(int bnum) throws Exception;
			
}