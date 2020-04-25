package com.dalozz.app.healtchek.service.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dalozz.app.healtchek.service.HealtService;

@Service
public class HealtServiceImpl implements HealtService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void check(String schema, String table) {
		jdbcTemplate.queryForObject(MessageFormat.format("select count(*) from {0}.{1}", schema, table), Integer.class);
	}

}
