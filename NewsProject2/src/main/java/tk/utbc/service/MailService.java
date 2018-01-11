/**
 * 
 */
package tk.utbc.service;

/**
 * @author KEN
 * Park Jong-hyun
 * 2018. 01. 10 
 */
public interface MailService {

	 /** 메일 전송
     *  @param title 제목
     *  @param content 내용
     *  @param from 보내는 메일 주소
     *  @param to 받는 메일 주소
     *  @param filePath 첨부 파일 경로: 첨부파일 없을시 null **/

	public boolean send(String title, String content, String from, String to, String filePath);
}
