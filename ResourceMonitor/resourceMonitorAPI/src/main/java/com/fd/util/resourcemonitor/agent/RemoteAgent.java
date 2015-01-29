package com.fd.util.resourcemonitor.agent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.fd.util.resourcemonitor.resource.Resource;

public interface RemoteAgent extends Remote {

	/**
	 * @return Logical resource, which present a physical resource, eg disk, app server.
	 */
	List<Resource> getResources() throws RemoteException;

	/**
	 * This method should be synchronized start, which means after return, the
	 * agent should start working.
	 * 
	 * @return <code>true</code> if start successfully, <code>false</code> if
	 *         failed to start.
	 */
	boolean start() throws RemoteException;

	/**
	 * This method should be synchronized stop, which means after return, the
	 * agent should stop and release all resources.
	 * 
	 * @return <code>true</code> if stop successfully, <code>false</code> if
	 *         failed to stop.
	 */
	boolean stop() throws RemoteException;

	/**
	 * @return The name of the agent.
	 */
	String getName() throws RemoteException;
	
	/**
	 * @return The Description of the agent.
	 */
	String getDescription() throws RemoteException;
	
	String getData(String type) throws RemoteException;

}
