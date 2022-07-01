package com.example.softcon.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


import com.example.softcon.model.User;
import com.example.softcon.model.Tracker;
import com.example.softcon.model.LogIn;

@Component
public class ServiceFile {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static List<User> user = new ArrayList<>();


	
	
	//for user and task
	private static final class UserMapper implements RowMapper<User> {
	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	User emp = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
	    	if(emp!=null) {
	    		System.out.print("Nice");
	    	}
	    	else {
	    		System.out.print("Not");
	    	}
//	    	emp.setId(rs.getInt("id"));
//	    	emp.setEmpName(rs.getString("name"));
//	    	emp.setAge(rs.getInt("age"));
	    	return emp;
	    }
	  } 

	private static final class TrackerMapper implements RowMapper<Tracker> {
	    public Tracker mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Tracker emp = new Tracker(rs.getInt("id"),rs.getDouble("bmi"),rs.getDouble("weight"),rs.getDouble("height"),rs.getInt("userId"));
	    	if(emp!=null) {
	    		System.out.print("Nice");
	    	}
	    	else {
	    		System.out.print("Not");
	    	}
//	    	emp.setId(rs.getInt("id"));
//	    	emp.setEmpName(rs.getString("name"));
//	    	emp.setAge(rs.getInt("age"));
	    	return emp;
	    }
	  } 
	
	public List<User> retrieveAllUser() {
		String sql="SELECT * FROM `user`";
		return this.jdbcTemplate.query(sql, new UserMapper());
		
//		return user;
	}
	
	public List<Tracker> retrieveTracker(int userId) {
		
		String sql="SELECT * FROM `tracker` WHERE userId=?";
		return this.jdbcTemplate.query(sql,new Object[] { userId}, new TrackerMapper());
//		User user = retrieveUser(userId);
//
//		if (user == null) {
//			return null;
//		}
//
//		return user.getTask();
	}
	
	public List<User> retrieveUser(int userId) {
		String sql="SELECT * FROM `user` WHERE id=?";
		return this.jdbcTemplate.query(sql,new Object[] {userId}, new UserMapper());
	}
	
	public User retrieveUserforLogIn(LogIn login) {
		for (User user : user) {
			if (user.getEmail().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> funclogIn(LogIn newlogin) {
		String sql="SELECT * FROM `user` WHERE email=? AND password=? LIMIT 1";
		return this.jdbcTemplate.query(sql,new Object[] { newlogin.getEmail(), newlogin.getPassword() }, new UserMapper());
	}
	
	public List<Tracker> retrieveSpecificTask(int userId, int taskId) {
		String sql="SELECT * FROM `tracker` WHERE id=? AND userId=?";
		return this.jdbcTemplate.query(sql,new Object[] { taskId, userId }, new TrackerMapper());
		
	}
	
	public List<User> checkforRegister(String email) {
		String sql="SELECT * FROM `user` WHERE email=? LIMIT 1";
		return this.jdbcTemplate.query(sql,new Object[] { email }, new UserMapper());
	}
	
	public int funcRegister(User newUser) {
		List<User> listuser=checkforRegister(newUser.getEmail());
		if(!listuser.isEmpty()) {
			return 0;
		}
		String sql = "INSERT INTO `user`(`name`, `email`, `password`) " + "VALUES (?, ?, ?)"; 
		return this.jdbcTemplate.update(sql, new Object[] { newUser.getName(), newUser.getEmail(), newUser.getPassword()});
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Tracker funcTaskInsert(Tracker newTask) {
//		String sql = "INSERT INTO `task`(`taskName`, `taskDescription`, `taskCondition`, `userId`) VALUES (?,?,?,?)"; 
//		return this.jdbcTemplate.update(sql, new Object[] { newTask.getTaskName(), newTask.getTaskDescription(), newTask.getTaskCondition(),newTask.getuserId()});
		String sql = "INSERT INTO `tracker`(`bmi`, `weight`, `height`, `userId`) VALUES (:bmi, :weight, :height, :userId)"; 
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("bmi", newTask.getBmi())
				.addValue("weight", newTask.getWeight())
				.addValue("height", newTask.getHeight())
				.addValue("userId", newTask.getuserId());
		namedParameterJdbcTemplate.update(sql, parameters, holder);
		newTask.setId(holder.getKey().intValue());
		return newTask;
	}
	
	public int deleteTask(int id) {
		String sql = "DELETE FROM `tracker` WHERE id=?"; 
		return this.jdbcTemplate.update(sql, new Object[] {id});
	}
	
	public int updateTask(Tracker tracker) {
		String sql = "UPDATE `tracker` SET bmi=?,weight=?,height=?,userId=? WHERE id=?"; 
		return this.jdbcTemplate.update(sql, new Object[] {tracker.getBmi(),tracker.getWeight(),tracker.getHeight(),tracker.getuserId(),tracker.getId()});
	}
	
	
	
	
	
	
	
	
	
	
	
	
}