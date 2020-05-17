package com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.entity.MovieEntity;
import com.dao.query.SQLQuery;
import com.utils.SQLConnectionUtils;

public class MovieDaoImpl implements MovieDao {

	@Override
	public void deleteByName(String name) {
		try {
			Connection connection = SQLConnectionUtils.getConn();
			// Compiling query and assigning into PreparedStatement object
			PreparedStatement pstmt = connection.prepareStatement(SQLQuery.DELETE_MOVIE_BY_NAME);
			// setting the values inside PreparedStatement object
			pstmt.setString(1, name);
			// Fire the query
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String update(MovieEntity entity) throws Exception {
		Connection connection = SQLConnectionUtils.getConn();
		//Compiling query and assigning into PreparedStatement object
		PreparedStatement pstmt=connection.prepareStatement(SQLQuery.UPDATE_MOVIE);
		//setting the values inside PreparedStatement object
		pstmt.setInt(1,entity.getYear());
		pstmt.setBigDecimal(2,new BigDecimal(entity.getBudget()));
		pstmt.setString(3,entity.getName());
		//Fire the query
	    pstmt.execute();
	    return "success";
	}

	
	
	@Override
	public String save(MovieEntity entity) throws Exception {
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		Connection connection = SQLConnectionUtils.getConn();
		//Compiling query and assigning into PreparedStatement object
		PreparedStatement pstmt=connection.prepareStatement(SQLQuery.INSERT_MOVIE);
		//setting the values inside PreparedStatement object
		pstmt.setString(1,entity.getName());
		pstmt.setInt(2,entity.getYear());
		pstmt.setInt(3,entity.getRating());
		pstmt.setBigDecimal(4,new BigDecimal(entity.getBudget()));
		pstmt.setInt(5,entity.getTeams());
		pstmt.setTimestamp(6,timestamp);
		//Fire the query
	    pstmt.execute();
	    return "success";
	}


	@Override
	public List<MovieEntity> findAll() throws Exception {
		// Compiling query and assigning into PreparedStatement object
		Connection connection = SQLConnectionUtils.getConn();
		PreparedStatement pstmt = connection.prepareStatement(SQLQuery.SELECT_ALL_MOVIE);
		ResultSet rs = pstmt.executeQuery();
		List<MovieEntity> movies = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt(1);
			String name = rs.getString(2);
			int year = rs.getInt(3);
			int rating = rs.getInt(4);
			BigDecimal budget = rs.getBigDecimal(5);
			int teams = rs.getInt(5);
			Timestamp createDate = rs.getTimestamp(6);
			MovieEntity entity = new MovieEntity(mid, name, year, rating, budget.longValue(), teams, createDate);
			movies.add(entity);
		}
		return movies;
	}
}
