/**
 * 
 */
package com.fd.util.resourcemonitor.resource.cassandra;

import com.fd.util.resourcemonitor.resource.Monitor;
import com.fd.util.resourcemonitor.resource.Resource;
import com.fd.util.resourcemonitor.resource.ResourceType;
import com.fd.util.resourcemonitor.resource.common.SimpleResourceType;

/**
 * @author Ares Tang
 * 
 */
public class CassandraResource implements Resource {

	private static final String NAME = "Cassandra Resource";

	private static final String DESCRIPTION = "Cassandra Resource is a type of database resource, "
			+ "this resource provide data access service, and as the general resource, "
			+ "it not related to any app locagical, "
			+ "the ifformation of this resource most related to the server's performance and status."
			+ " Not the data structure.";

	private ResourceType resourceType = new SimpleResourceType();
	
	private Monitor monitor = new CassandraMonitor();

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.Resource.Resource#getMonitor()
	 */
	public Monitor getMonitor() {
		return monitor;
	}

}
