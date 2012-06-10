package com.fd.util.resourcemonitor.resource.common;

import com.fd.util.resourcemonitor.resource.Information;

public class GeneralInformation implements Information{
	
	private String information = null;

	public GeneralInformation(String information)
	{
		this.information = information;
	}

	public String getDetail() {
		return information;
	}
}
