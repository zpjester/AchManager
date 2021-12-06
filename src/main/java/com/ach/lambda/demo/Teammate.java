package com.ach.lambda.demo;
import org.json.simple.JSONObject;
public class Teammate {
    String name;
    Teammate(String name){
    	this.name = name;
    }
    JSONObject toJSON() {
    	JSONObject response = new JSONObject();
    	response.put("name", name);
    	return response;
    }
}