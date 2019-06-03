package com.daichao.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class HttpHelper {

	public static String textGet(String url){
		try {    
		    StringBuffer sb;
	        URLConnection con = (new URL(url)).openConnection();
	        Reader reader = new InputStreamReader(con.getInputStream(), "utf-8");
	        BufferedReader br = new BufferedReader(reader);
	        sb = new StringBuffer();
	        for(String line = ""; (line = br.readLine()) != null;)
	            sb.append(line);
				br.close();
				return sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
	    }
}
