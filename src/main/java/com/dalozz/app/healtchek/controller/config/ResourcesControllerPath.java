package com.dalozz.app.healtchek.controller.config;

public class ResourcesControllerPath {

	private ResourcesControllerPath () {
	}
	
	public class HealtControllerPath{
		
		private HealtControllerPath () {
		}
		
		public static final String RESOURCE = "/healt";
		public static final String CHECK = "/check/{schema}/{table}";
		public static final String HOME = "/home";
	}
}
