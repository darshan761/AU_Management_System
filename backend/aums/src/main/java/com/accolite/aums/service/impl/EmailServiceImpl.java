/**
 * 
 */
package com.accolite.aums.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.accolite.aums.dao.impl.CourseDaoImpl;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Email;

/**
 * @author darshan
 * @param <Email>
 *
 */
@Service
public class EmailServiceImpl {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private CourseDaoImpl courseDao;

   
    public void sendEmail(Email email) throws MailException, MessagingException {
    	
    	
    	MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        

        Course course = courseDao.findCourseById(email.getCourseId());
        helper.setTo(email.getRecepient());
        helper.setSubject(email.getSubject());
        helper.setText("Please check the portal for training material.\n course: "+course.getCourseName()+"\n"+" by "+ email.getInstructor());
      

        javaMailSender.send(msg);
    }
}

