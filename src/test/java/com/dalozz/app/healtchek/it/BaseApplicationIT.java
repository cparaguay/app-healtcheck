package com.dalozz.app.healtchek.it;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class BaseApplicationIT {


	protected <T> T fromJSON(ResultActions result, Type typeOfT) throws JsonSyntaxException, UnsupportedEncodingException {
		return new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), typeOfT);
	}
	
}
