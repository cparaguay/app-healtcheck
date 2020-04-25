package com.dalozz.app.healtchek.it;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dalozz.app.healtchek.HealtchekApplicationTest;



@SpringBootTest(classes = HealtchekApplicationTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("default")
public class HealtControllerIT extends BaseApplicationIT{

	@Autowired
	private MockMvc mockMvc;
	
	@DisplayName("Test Exitoso")
	@Test
	public void success() throws Exception {
		
		// Endpoint
		String path = "/healt/check/{0}/{1}";
		
		// Given
		String schema = "LMBASE";
		String table = "PAT_SOLICITUD";
		
		path = MessageFormat.format(path, schema, table);
		
		// When
		ResultActions result = mockMvc.perform(get(path)).andExpect(status().isOk());
		
		// Then
		assertTrue(StringUtils.isEmpty(result.andReturn().getResponse().getContentAsString()), "Debe estar vacio");

	}
	
	@DisplayName("Test Erroneo")
	@Test
	public void error() throws Exception {
		
		// Endpoint
		String path = "/healt/check/{0}/{1}";
		
		// Given
		String schema = "LMBASE";
		String table = "TABLA";
		
		path = MessageFormat.format(path, schema, table);
		
		// When
		ResultActions result = mockMvc.perform(get(path)).andExpect(status().isOk());
		 
		// Then
		assertFalse(StringUtils.isEmpty(result.andReturn().getResponse().getContentAsString()), "No debe estar vacio el mensaje");
	}
	
	
}
