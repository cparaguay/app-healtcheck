package com.dalozz.app.healtchek.service.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dalozz.app.healtchek.service.HealtService;
import com.dalozz.app.healtchek.util.log4j2.Log4jUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class HealtServiceImpl implements HealtService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void check(String schema, String table) {
		jdbcTemplate.queryForObject(MessageFormat.format("select count(*) from {0}.{1}", schema, table), Integer.class);
	}

	@Override
	public Boolean conditionSimple(String schema, String table, String column, String value, String id) {
		log.info(Log4jUtil.inicioLog("conditionSimple() | id: {0}", id));
		String sql = MessageFormat.format("select count(*) from {0}.{1} where {2}={3}", schema, table, column, value);
		log.info("conditionSimple() | id: {}, sql: {}", id, sql);
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		log.info("conditionSimple() | id: {}, count: {}", id, count);
		boolean result = (count>0)?true:false;
		log.info(Log4jUtil.finLog("conditionSimple() | id: {0}", id));
		return result;
	}

}
