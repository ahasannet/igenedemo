package com.igene.demo.model;

import java.util.Date;
import java.util.List;

public class Droplet {
	
	private int id;
	private long userId;
	private int distribution;
	private String version;
	private List<Integer> applications;
	private int configuration;
	private int serverType;
	private int dataCenter;
	private int slot;
	private List<String> additionalOptions;
	private List<String> sshKeys;
	private int numberOfDroplets;
	private String hostname;
	private Date createdOn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getDistribution() {
		return distribution;
	}
	public void setDistribution(int distribution) {
		this.distribution = distribution;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<Integer> getApplications() {
		return applications;
	}
	public void setApplications(List<Integer> applications) {
		this.applications = applications;
	}
	public int getConfiguration() {
		return configuration;
	}
	public void setConfiguration(int configuration) {
		this.configuration = configuration;
	}
	public int getServerType() {
		return serverType;
	}
	public void setServerType(int serverType) {
		this.serverType = serverType;
	}
	public int getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(int dataCenter) {
		this.dataCenter = dataCenter;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public List<String> getAdditionalOptions() {
		return additionalOptions;
	}
	public void setAdditionalOptions(List<String> additionalOptions) {
		this.additionalOptions = additionalOptions;
	}
	public List<String> getSshKeys() {
		return sshKeys;
	}
	public void setSshKeys(List<String> sshKeys) {
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
}
