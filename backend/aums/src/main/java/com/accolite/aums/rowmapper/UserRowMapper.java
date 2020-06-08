/**
 * 
 */
package com.accolite.aums.rowmapper;



import org.springframework.jdbc.core.RowMapper;

import com.accolite.aums.constants.ColumnNames;
import com.accolite.aums.models.User;

/**
 * @author darshan
 *
 */
public class UserRowMapper {

	private UserRowMapper() {}
	
	public static final RowMapper<User> UserRowMapperLambda = (rs, rowNum) -> {
		User user = new User();
		user.setUserId(rs.getInt(ColumnNames.USER_ID));
		user.setUserEmail(rs.getString(ColumnNames.USER_EMAIL));
		user.setUserName(rs.getString(ColumnNames.USER_NAME));
		user.setUserDesignation(rs.getString(ColumnNames.USER_DESIGNATION));
		user.setUserLocation(rs.getString(ColumnNames.USER_LOCATION));
		user.setUserImage(rs.getBlob(ColumnNames.USER_IMAGE));
		return user;
	};

}
