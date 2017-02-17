package com.ai.opt.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ai.opt.sdk.util.DateUtil;

public class UCDateUtils {

	public static long getSystime(){

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeStart;
		try {
			timeStart = sdf.parse(DateUtil.getCurrentTime()).getTime();
			return timeStart/1000;
		} catch (ParseException e) {

		}
		return 0;
	
	}
	//1478549191-1478549224
	public static void main(String[] args) {
		System.out.println(getSystime());
		
		long c = 1478549191-1478549224;
		System.out.println(c);
	}
}
