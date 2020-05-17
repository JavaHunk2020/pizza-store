package com.dao;

import java.util.List;

import com.dao.entity.MovieEntity;

public interface MovieDao {

	void deleteByName(String name);

	List<MovieEntity> findAll() throws Exception;

	String save(MovieEntity entity) throws Exception;

	String update(MovieEntity entity) throws Exception;

}
