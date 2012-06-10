package com.fd.util.resourcemonitor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandRunner {
	public static String run(String command) {
		String result = "";
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			InputStream standardInput = process.getInputStream();
			InputStream errorOutput = process.getErrorStream();
			result += readInputStream(standardInput);
			result += readInputStream(errorOutput);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return result;
	}
	
	public static String readInputStream(InputStream in)
	{
		String content = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				in));
		String line;
		try {
			line = reader.readLine();
			while (line != null) {
				content += line;
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
}
