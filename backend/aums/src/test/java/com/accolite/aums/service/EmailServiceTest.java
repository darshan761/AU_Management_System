/**
 * 
 */
package com.accolite.aums.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Email;
import com.accolite.aums.service.impl.EmailServiceImpl;

/**
 * @author darshan
 *
 */
@SpringBootTest(classes = com.accolite.aums.config.AumsApplication.class)
public class EmailServiceTest {

	@Mock
	private CourseDaoImpl courseDao;
	
	@Mock
	private MimeMessageHelper helper;
	
	@Mock
	private MimeMessage msg;

	@Mock
	private JavaMailSender javaMailSender;

	@InjectMocks
	private EmailServiceImpl emailService;

	ResponseDto response = new ResponseDto();
	Course course = new Course();
	Email email = new Email();

	@BeforeEach
	public void init() {
		course.setCourseId(1);
		course.setCourseName("React");
		course.setCourseDesc("Frontend Library");
		email.setSubject("subject");
		email.setMessage("message");
		email.setCourseId(1);
	}

	@Test
	public void sendEmail() throws MailException, MessagingException {
		
		MimeMessage msg = javaMailSender.createMimeMessage();		
		
		doNothing().when(helper).setTo(email.getRecepient());
		doNothing().when(helper).setSubject(email.getSubject());
		doNothing().when(helper).setText(email.getMessage());
		doNothing().when(javaMailSender).send(msg);
    	when(courseDao.findCourseById(1)).thenReturn(response);
    	
    	helper.setTo(email.getRecepient());
		helper.setSubject(email.getSubject());
		helper.setText(email.getMessage());
		javaMailSender.send(msg);
		response.setData(course);
//		
		ResponseDto responseFound = emailService.sendEmail(email);
		verify(javaMailSender).send(msg);
		verify(helper).setTo(email.getRecepient());
		verify(helper).setSubject(email.getSubject());
		verify(helper).setText(email.getMessage());

		
//		 assertEquals(1, (int)response.getAdditionalData());
	}

}
