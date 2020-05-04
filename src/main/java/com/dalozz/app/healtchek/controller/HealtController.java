package com.dalozz.app.healtchek.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalozz.app.healtchek.controller.config.ResourcesControllerPath.HealtControllerPath;
import com.dalozz.app.healtchek.service.HealtService;
import com.dalozz.app.healtchek.util.log4j2.Log4jUtil;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = HealtControllerPath.RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class HealtController {

	@Autowired
	private HealtService healtService;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Value("${driver}")
	private String driver;

	@Value("${url}")
	private String url;
	
	@ApiResponse(description = "Obtener las variables asignadas")
	@GetMapping(value = HealtControllerPath.HOME, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String home() {
		
		String id = String.valueOf(System.nanoTime());
		log.info(Log4jUtil.inicioLog("home() | id: {0}", id));
		String message = StringUtils.EMPTY;
		
		try {
			message = MessageFormat.format("home() | id: {0} - driver: [{1}] | url: [{2}] | username: [{3}] | password: [{4}]", id, driver, url, username, password);
			log.info(message);
		} catch (Exception e) {
			message = e.getMessage();
		}
		log.info(Log4jUtil.finLog("home() | id: {0}", id));
		
	    return message;
	}
	
	@ApiResponse(description = "Consulta a una tabla mediante su esquema, hace un select count.")
	@GetMapping(value = HealtControllerPath.CHECK, produces = MediaType.TEXT_PLAIN_VALUE)
	public String check(@PathVariable("schema")String schema, @PathVariable("table")String table, HttpServletResponse response) {
		
		String id = String.valueOf(System.nanoTime());
		log.info(Log4jUtil.inicioLog("check() | id: {0}", id));
		String message = StringUtils.EMPTY;
		
		try {
			log.info("check() | id: {} Schema: {}, Table: {}", id, schema, table);
			healtService.check(schema, table);
			log.info("check() | id: {} - Query Status OK", id);
		} catch (Exception e) {
			message = e.getMessage();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		log.info(Log4jUtil.finLog("check() | id: {0}", id));
		
	    return message;
	}
	
	@ApiResponse(description = "Consulta a una tabla mediante un where simple")
	@GetMapping(value = HealtControllerPath.CONDITION_SIMPLE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String checkConditionSimple(@PathVariable("schema")String schema, @PathVariable("table")String table, @PathVariable("column")String column, @PathVariable("value")String value, HttpServletResponse response) {
		
		String id = String.valueOf(System.nanoTime());
		log.info(Log4jUtil.inicioLog("checkConditionSimple() | id: {0}", id));
		String message = StringUtils.EMPTY;
		
		try {
			log.info("checkConditionSimple() | id: {} Schema: {}, Table: {}", id, schema, table);
			Boolean result = healtService.conditionSimple(schema, table, column, value, id);
			if(BooleanUtils.isFalse(result)) {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			}else {
				log.info("checkConditionSimple() | id: {} - Query Status OK", id);
			}
		} catch (Exception e) {
			message = e.getMessage();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		log.info(Log4jUtil.finLog("checkConditionSimple() | id: {0}", id));
		
	    return message;
	}
}
