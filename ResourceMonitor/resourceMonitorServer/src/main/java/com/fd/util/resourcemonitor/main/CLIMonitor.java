package com.fd.util.resourcemonitor.main;

import java.util.Properties;

import com.fd.util.resourcemonitor.general.CLIServer;
import com.fd.util.resourcemonitor.general.Constant;

public class CLIMonitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CLIServer cliServer = new CLIServer();
		Properties properties =  new Properties();
		properties.put(Constant.FILE_NAME, args[0]);
		cliServer.config(properties);
		cliServer.start();
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
