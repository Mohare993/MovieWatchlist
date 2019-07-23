package com.qa.util;

import javax.enterprise.inject.Default;

import com.google.gson.Gson;

@Default
public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {

		return gson.fromJson(jsonString, clazz);
	}

}