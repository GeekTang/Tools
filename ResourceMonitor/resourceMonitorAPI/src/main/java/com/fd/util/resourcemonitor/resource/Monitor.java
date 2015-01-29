package com.fd.util.resourcemonitor.resource;

import java.io.Serializable;

/**
 * Resource monitor, which take the action to monitor the resource, and return
 * the status. And also the monitor could take long time monitor and analyze the
 * data and give result or report.
 * 
 * It will not run unless it be called.
 * 
 * And the description shows the side effect for monitoring resource.
 * 
 * @author "Ares Tang"
 * 
 */
public interface Monitor extends Serializable{

	/**
	 * @return Describe how it monitor the resource and what's the impact, some
	 *         monitor will impact the performance, and not recommended to use
	 *         for normal case.
	 */
	String getDecription();

	/**
	 * @return The resource status.
	 */
	Status getStatus();

	/**
	 * Waits at most <code>millis</code> milliseconds for analyze to return. A
	 * timeout of <code>0</code> means to wait forever.
	 * 
	 * @param millis
	 *            the time to wait in milliseconds.
	 * @return The information of the current status.
	 */
	Information analyze(Long millis);
	
	Information getResourceInfor();
}
