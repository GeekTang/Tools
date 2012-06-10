package com.fd.util.resourcemonitor.agent;

import com.fd.util.resourcemonitor.resource.Resource;

/**
 * @author "Ares Tang"
 * 
 */
public interface Agent {

	/**
	 * @return Logical resource, which present a physical resource, eg disk, app server.
	 */
	Resource getResource();

	/**
	 * This method should be synchronized start, which means after return, the
	 * agent should start working.
	 * 
	 * @return <code>true</code> if start successfully, <code>false</code> if
	 *         failed to start.
	 */
	boolean start();

	/**
	 * This method should be synchronized stop, which means after return, the
	 * agent should stop and release all resources.
	 * 
	 * @return <code>true</code> if stop successfully, <code>false</code> if
	 *         failed to stop.
	 */
	boolean stop();

	/**
	 * @return The name of the agent.
	 */
	String getName();

}
