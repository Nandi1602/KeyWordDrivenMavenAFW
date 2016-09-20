package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteThis {

	public static void main(String[] args) {
		String str1="rgba(110, 168, 0, 1)";
		List<Integer> rgb=new ArrayList<Integer>();
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(str1);
		 while(m.find())
		 {
			 int v=Integer.parseInt(m.group());
			 rgb.add(v);
		 }
		 System.out.println(rgb.size());
		
		 int red=rgb.get(0);
		 int green=rgb.get(1);
		 int blue=rgb.get(2);
		 String hex = String.format("#%02x%02x%02x",red, green,blue);
		 System.out.println(hex);
	}
}