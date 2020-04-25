package com.dalozz.app.healtchek.controller;

import java.text.MessageFormat;

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
	@GetMapping(value = HealtControllerPath.HOME, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String home() {
		
		String id = String.valueOf(System.nanoTime());
		log.info(Log4jUtil.inicioLog("home() | id: {0}", id));
		String message = StringUtils.EMPTY;
		
		try {
			message = MessageFormat.format("driver: [{0}] | url: [{1}] | username: [{2}] | password: [{3}]", driver, url, username, password);
		} catch (Exception e) {
			message = e.getMessage();
			log.error(e.getMessage(), e);
		}
		log.info(Log4jUtil.finLog("home() | id: {0}", id));
		
	    return message;
	}
	
	@ApiResponse(description = "Consulta a una tabla mediante su esquema, hace un select count.")
	@GetMapping(value = HealtControllerPath.CHECK, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String check(@PathVariable("schema")String schema, @PathVariable("table")String table) {
		
		String id = String.valueOf(System.nanoTime());
		log.info(Log4jUtil.inicioLog("check() | id: {0}", id));
		String message = StringUtils.EMPTY;
		
		try {
			log.info("check() | id: {} Schema: {}, Table: {}", id, schema, table);
			healtService.check(schema, table);
		} catch (Exception e) {
			message = e.getMessage();
			log.error(e.getMessage(), e);
		}
		
		log.info(Log4jUtil.finLog("check() | id: {0}", id));
		
	    return message;
	}
}
