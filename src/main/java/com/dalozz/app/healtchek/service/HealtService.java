package com.dalozz.app.healtchek.service;

public interface HealtService {

	void check(String schema, String table);

	Boolean conditionSimple(String schema, String table, String column, String value, String id);
}
