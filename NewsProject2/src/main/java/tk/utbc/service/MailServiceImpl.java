/**
 * 
 */
package tk.utbc.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author KEN
 * Park Jong-hyun
 * 2018. 01. 10 
 */
@Service
public class MailServiceImpl implements MailService {
	
	private JavaMailSender javaMailSender;

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public boolean send(String title, String content, String from, String to, String filePath){
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(title);
			helper.setText(content);
			helper.setFrom(from);
			helper.setTo(to);
			if(filePath != null) {
				File file = new File(filePath);
				if(file.exists()) {
					helper.addAttachment(file.getName(), new File(filePath));
				}
			}
			javaMailSender.send(message);
			
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}
