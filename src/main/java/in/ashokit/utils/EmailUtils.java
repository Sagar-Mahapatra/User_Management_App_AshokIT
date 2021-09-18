package in.ashokit.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

@Controller
public class EmailUtils {

	@Autowired
	private JavaMailSender sender;

	public boolean sendEmail(String to, String subject, String body) {
		try {
			MimeMessage MimeMessage = sender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(MimeMessage);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body, true);
			sender.send(MimeMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
