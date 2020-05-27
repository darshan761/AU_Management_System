/**
 * 
 */
package com.accolite.aums.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accolite.aums.dao.UserDao;
import com.accolite.aums.models.User;
import com.accolite.aums.rowmapper.UserRowMapper;

/**
 * @author darshan
 *
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * from user";
		RowMapper<User> rowMapper = new UserRowMapper();

		return jdbcTemplate.query(query, rowMapper);
	}

	@Override
	public User findUserById(int id) {
		String query = "SELECT * FROM user WHERE user_id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

		return jdbcTemplate.queryForObject(query, rowMapper, id);
	}

	@Override
	public User findUserByEmail(String email) {
		String query = "SELECT * FROM user WHERE user_email = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

		return jdbcTemplate.queryForObject(query, rowMapper, email);
	}
	
	@Override
	public void addUser(User user) {
//		String query = "INSERT INTO employees(employee_id, first_name, last_name, email, phone, job_title) VALUES(?, ?, ?, ?, ?, ?)";
//		  jdbcTemplate.update(query, user.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getJobTitle());
	}

	@Override
	public void updateUser(User user) {
//		String query = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, job_title=? WHERE employee_id=?";
//		  jdbcTemplate.update(query, employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getJobTitle(), employee.getEmployeeId());
//		  

	}

	@Override
	public void deleteUser(int id) {
		String query = "DELETE FROM user WHERE user_id=?";
		jdbcTemplate.update(query, id);
	}

}
