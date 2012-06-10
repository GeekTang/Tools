/**
 * 
 */
package com.fd.util.resourcemonitor.resource.cassandra;

import com.fd.util.resourcemonitor.resource.Information;
import com.fd.util.resourcemonitor.resource.Monitor;
import com.fd.util.resourcemonitor.resource.Status;
import com.fd.util.resourcemonitor.resource.common.GeneralInformation;
import com.fd.util.resourcemonitor.util.CommandRunner;

/**
 * @author "Ares Tang"
 * 
 */
public class CassandraMonitor implements Monitor {

	private Status status = Status.unknown;
	
	private Information information = null;

	private static final String DESCRIPTION = "Cassandra Monitor is the simple resource monitor, "
			+ "it will analyze the log and use server tool to get different dynamical data, "
			+ "and compare with standard data, "
			+ "and judge the status and analyze result.";

	private static final String NODETOOL_COMMAND = "./bin/nodetool -h 127.0.0.1 -p 6080 ring";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.resource.Monitor#getDecription()
	 */
	public String getDecription() {
		return DESCRIPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.resource.Monitor#getStatus()
	 */
	public Status getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.resource.Monitor#analyze(java.lang.Long)
	 */
	public Information analyze(Long millis) {
		status = Status.online;
		String nodeStatus = CommandRunner.run(System.getenv("CASSANDRA_HOME")+NODETOOL_COMMAND);
		if(nodeStatus.contains("Down"))
		{
			status = Status.error;
		}
		information = new GeneralInformation("Cassandra status: " + status.toString());
		return information;
	}

	public Information getResourceInfor() {
		return information;
	}

}
