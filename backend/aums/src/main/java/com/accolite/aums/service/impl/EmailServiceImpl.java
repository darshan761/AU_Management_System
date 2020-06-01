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
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.enums.ResponseType;
import com.accolite.aums.models.Course;
import com.accolite.aums.models.Email;
import com.accolite.aums.queries.Queries;
import com.accolite.aums.rowmapper.TrainingMaterialRowMapper;

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

	public ResponseDto sendEmail(Email email) throws MailException, MessagingException {

		ResponseDto response = new ResponseDto();
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			ResponseDto courseResponse = courseDao.findCourseById(email.getCourseId());
			helper.setTo(email.getRecepient());
			helper.setSubject(email.getSubject());
			helper.setText("Please check the portal for training material.\n course: "
					+ ((Course) courseResponse.getData()).getCourseName() + "\n" + " by " + email.getInstructor());
			javaMailSender.send(msg);
			response.setResponseType(ResponseType.SUCCESS);
		} catch (Exception e) {
			response.setResponseType(ResponseType.FAILURE);
			response.setMsg(e.toString());
		}
		return response;

	}
}
