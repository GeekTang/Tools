/**
 * 
 */
package com.fd.util.resourcemonitor.resource.cassandra;

import com.fd.util.resourcemonitor.resource.Resource;
import com.fd.util.resourcemonitor.resource.ResourceType;
import com.fd.util.resourcemonitor.resource.Status;
import com.fd.util.resourcemonitor.util.CommandRunner;

/**
 * @author Ares Tang
 * 
 */
public class EMAResource implements Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5788411212335496173L;

	private static final String NAME = "EMA Resource";

	private static final String DESCRIPTION = "Display EMA status";

	private ResourceType resourceType = ResourceType.Application;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.Resource.Resource#getName()
	 */
	public String getName() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.Resource.Resource#getDescription()
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.Resource.Resource#getType()
	 */
	public ResourceType getType() {
		return resourceType;
	}

	public Status getStatus() {
		CommandRunner.run("");
		return Status.offline;
	}

	public String getDetail() {
		return "";
	}

}
