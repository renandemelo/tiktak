package br.org.tiktak.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
	}
	
}
