package com.dalozz.app.healtchek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dalozz.app.healtchek.service.ShutdownService;


@RestController
public class ShutdownController {

	@Autowired
	private ShutdownService shutdownService;

	@GetMapping("/shutdown")
	public void shutdownContext() {
		shutdownService.shutdown();
	}

}