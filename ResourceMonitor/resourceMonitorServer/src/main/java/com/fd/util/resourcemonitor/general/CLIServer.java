/**
 * 
 */
package com.fd.util.resourcemonitor.general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fd.util.resourcemonitor.agent.Agent;
import com.fd.util.resourcemonitor.server.Server;
import com.fd.util.resourcemonitor.util.AgentPrinter;

/**
 * @author "Ares Tang"
 * 
 */
public class CLIServer implements Server {

	private static final String NAME = "Command Line Resource Monitor Server.";

	private List<Agent> agentList = new ArrayList<Agent>();

	private Properties properties;

	CLIRunner runner = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.server.Server#start()
	 */
	public boolean start() {
		String fileName = (String) properties.get(Constant.FILE_NAME);
		File file = new File(fileName);
		OutputStream out;
		try {
			out = new FileOutputStream(file);
			runner = new CLIRunner(out);
			runner.startRunner();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.server.Server#stop()
	 */
	public boolean stop() {
		runner.stopRunner();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fd.util.resourcemonitor.server.Server#config(java.util.Properties)
	 */
	public boolean config(Properties properties) {
		this.properties = properties;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.server.Server#register(com.fd.util.
	 * resourcemonitor.agent.Agent)
	 */
	public boolean register(Agent agent) {
		agentList.add(agent);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.server.Server#getName()
	 */
	public String getName() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fd.util.resourcemonitor.server.Server#getAgentList()
	 */
	public List<Agent> getAgentList() {
		return agentList;
	}

	private class CLIRunner extends Thread {
		private boolean isStarted = false;
		private OutputStreamWriter writer = null;

		public CLIRunner(OutputStream output) {
			writer = new OutputStreamWriter(output);
		}

		public void stopRunner() {
			isStarted = false;
			this.interrupt();
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void startRunner() {
			isStarted = true;
			this.start();
		}

		public void run() {
			while (isStarted) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));
				String line;
				try {
					line = reader.readLine();
					if (line.startsWith("register")) {
						loadAgent(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					line = reader.readLine();
					if (line.startsWith("print")) {
						printAgentInfor();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		private void printAgentInfor() {
			for (Agent agent : agentList) {
				agent.getResource().getMonitor().analyze(0L);
			}

			for (Agent agent : agentList) {
				String agentInfor = AgentPrinter.print(agent);
				try {
					writer.write(agentInfor);
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		private void loadAgent(String line) {
			String agentParameter = line.replaceFirst("register", "").trim();
			String[] agentInfor = agentParameter.split(" ");
			URL classUrl;
			try {
				classUrl = new URL(agentInfor[0]);
				URL[] classUrls = { classUrl };
				URLClassLoader ucl = new URLClassLoader(classUrls);
				Agent agent = (Agent) ucl.loadClass(agentInfor[1])
						.newInstance();
				register(agent);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
