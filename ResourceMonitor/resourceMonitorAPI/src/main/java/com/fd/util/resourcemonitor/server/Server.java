/**
 * 
 */
package com.fd.util.resourcemonitor.server;

import java.util.List;
import java.util.Properties;

import com.fd.util.resourcemonitor.agent.Agent;

/**
 * @author Ares Tang
 *
 */
public interface Server {

	boolean start();
	
	boolean stop();
	
	boolean config(Properties properties);
	
	boolean register(Object agent);
	
	String getName();
	
	List<Agent> getAgentList();
}
