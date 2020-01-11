package com.websystem.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WebsystemRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class UserEntityRowMapper implements RowMapper<UserEntity> {
        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        	UserEntity user = new UserEntity();
        	user.setAge(rs.getInt("age"));
        	user.setBrith(rs.getString("birth"));
        	user.setPassword(rs.getString("password"));
        	user.setUserId(rs.getString("userId"));
        	user.setUserName(rs.getString("userName"));
            return user;
        }
    }
	
	public UserEntity findById(String id) {
		String sql = "select * from websystem where userId = '" + id + "'";
		System.out.println(sql);
		UserEntity user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new UserEntityRowMapper());
		} catch (EmptyResultDataAccessException e) {
			
		}
		return user;
	}
}
