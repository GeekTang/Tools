package com.fd.util.resourcemonitor.resource;

/**
 * Represent physical resource, eg, disk, app server.
 * 
 * @author "Ares Tang"
 * 
 */
public interface Resource {

	/**
	 * @return The name of the resource.
	 */
	String getName();

	/**
	 * @return More detail of the resource to describe what the resource is, and
	 *         what it provided, and why need to monitor it.
	 */
	String getDescription();

	/**
	 * @return ResourceType indicate different resources, some could be hardware
	 *         resource, and easy to get the status, and some could be complex
	 *         resources, such as app server, it need more time to get the
	 *         status, and the monitor may impact the original functionality.
	 */
	ResourceType getType();

	/**
	 * @return The resource monitor, which actually take the monitor action.
	 */
	Monitor getMonitor();

}
