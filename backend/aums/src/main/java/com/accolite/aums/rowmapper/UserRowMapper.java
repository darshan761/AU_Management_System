/**
 * 
 */
package com.accolite.aums.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt(ColumnNames.USER_ID));
		user.setUserEmail(rs.getString(ColumnNames.USER_EMAIL));
		user.setUserDesignation(rs.getString(ColumnNames.USER_DESIGNATION));
		user.setUserLocation(rs.getString(ColumnNames.USER_LOCATION));
		user.setUserImage(rs.getBlob(ColumnNames.USER_IMAGE));
		return user;
	}

}
