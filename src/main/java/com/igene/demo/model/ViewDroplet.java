package com.igene.demo.model;

public class ViewDroplet {
	
	private int id;
	private String distribution;
	private String version;
	private String applications;
	private ServerConfiguration configuration;
	private float usage;
	private String serverType;
	private String dataCenter;
	private int slot;
	private String additionalOptions;
	private String sshKeys;
	private int numberOfDroplets;
	private String hostname;
	private String createdOn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistribution() {
		return distribution;
	}
	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApplications() {
		return applications;
	}
	public void setApplications(String applications) {
		this.applications = applications;
	}
	public ServerConfiguration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(ServerConfiguration configuration) {
		this.configuration = configuration;
	}
	public float getUsage() {
		return usage;
	}
	public void setUsage(float usage) {
		this.usage = usage;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public String getAdditionalOptions() {
		return additionalOptions;
	}
	public void setAdditionalOptions(String additionalOptions) {
		this.additionalOptions = additionalOptions;
	}
	public String getSshKeys() {
		return sshKeys;
	}
	public void setSshKeys(String sshKeys) {
		this.sshKeys = sshKeys;
	}
	public int getNumberOfDroplets() {
		return numberOfDroplets;
	}
	public void setNumberOfDroplets(int numberOfDroplets) {
		this.numberOfDroplets = numberOfDroplets;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
