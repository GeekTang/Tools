/**
 * 
 */
package com.fd.util.resourcemonitor.resource.common;

import com.fd.util.resourcemonitor.resource.ResourceType;

/**
 * @author "Ares Tang"
 *
 */
public class SimpleResourceType implements ResourceType {
	
	private static final String RESOURCE_TYPE = "Simple Resource Type";

	/* (non-Javadoc)
	 * @see com.fd.util.resourcemonitor.Resource.ResourceType#getType()
	 */
	public String getType() {
		return RESOURCE_TYPE;
	}

}
