package com.igene.demo.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.igene.demo.model.AdditionalOption;
import com.igene.demo.model.DataCenterRegion;
import com.igene.demo.model.DataCenterRegions;
import com.igene.demo.model.Distribution;
import com.igene.demo.model.Distributions;
import com.igene.demo.model.Droplet;
import com.igene.demo.model.OneClickApplication;
import com.igene.demo.model.OneClickApplications;
import com.igene.demo.model.SSHKey;
import com.igene.demo.model.ServerConfiguration;
import com.igene.demo.model.ServerConfigurations;
import com.igene.demo.utils.AppConstants;

public class DropletRepository {

	private static DropletRepository userRepository = null;
	private static Map<Long, List<Droplet>> dropletMap = null;
	private static Map<String, Object> referenceDataMap = null;
	private static List<Distribution> distributionList = null;
	private static List<OneClickApplication> oneClickApplicationList = null;
	private static List<ServerConfiguration> serverConfigurationList = null;
	private static List<ServerConfiguration> highMemoryServerList = null;
	private static List<DataCenterRegion> dataCenterRegionList = null;
	private static List<String> additionalOptionList = null;
	private static List<String> sshKeyList = null;
	private static int maxDropletId = 1;


	public static DropletRepository getInstance()
	{
		if(userRepository == null)
		{
			reload();
		}
		return userRepository;
	}

	private static void reload()
	{
		userRepository = new DropletRepository();

		dropletMap = new HashMap<Long, List<Droplet>>();

		referenceDataMap = new HashMap<String, Object>();

		reloadDistributionList();
		
		reloadOneClickApplicationList();

		reloadServerConfigurationList();

		reloadHighMemoryServerList();

		reloadDataCenterRegionList();

		reloadAdditionalOptionList();

		reloadSSHKeyList();
	}

	public List<Droplet> getDropletList(Long userId)
	{
		if(dropletMap.containsKey(userId))
		{
			return dropletMap.get(userId);
		}
		return null;
	}

	public void saveDroplet(Droplet droplet)
	{
		List<Droplet> dropletList = null;
		boolean isNewData = true;

		if(dropletMap.containsKey(droplet.getUserId()))
		{
			dropletList = dropletMap.get(droplet.getUserId());
			for(int i=0; i<dropletList.size(); i++)
			{
				Droplet oldDroplet = dropletList.get(i);

				if(oldDroplet.getId() == droplet.getId())
				{
					dropletList.remove(i);
					isNewData = false;
					break;
				}
			}
		}
		else
		{
			dropletList = new ArrayList<Droplet>();
		}
		if(isNewData)
		{
			droplet.setId(maxDropletId);
			maxDropletId += 1;
		}
		dropletList.add(droplet);
		dropletMap.put(droplet.getUserId(), dropletList);
	}

	public Map<String, Object> getReferenceData()
	{
		return referenceDataMap;
	}

	private static void reloadDistributionList()
	{
		distributionList = new ArrayList<Distribution>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "distributions.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(Distributions.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			Distributions distributions = (Distributions) jaxbUnmarshaller.unmarshal(file); 
			distributionList = distributions.getDistributions();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("distributionList", distributionList);
	}
	
	private static void reloadOneClickApplicationList()
	{
		oneClickApplicationList = new ArrayList<OneClickApplication>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "one-click-application.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(OneClickApplications.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			OneClickApplications oneClickApplications = (OneClickApplications) jaxbUnmarshaller.unmarshal(file); 
			oneClickApplicationList = oneClickApplications.getApplications();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("oneClickApplicationList", oneClickApplicationList);
	}

	private static void reloadServerConfigurationList()
	{
		serverConfigurationList = new ArrayList<ServerConfiguration>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "standard-server.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfigurations.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			ServerConfigurations serverConfigurations = (ServerConfigurations) jaxbUnmarshaller.unmarshal(file); 
			serverConfigurationList = serverConfigurations.getServerConfigurations();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("serverConfigurationList", serverConfigurationList);
	}

	private static void reloadHighMemoryServerList()
	{
		highMemoryServerList = new ArrayList<ServerConfiguration>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "high-memory-server.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfigurations.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			ServerConfigurations serverConfigurations = (ServerConfigurations) jaxbUnmarshaller.unmarshal(file); 
			highMemoryServerList = serverConfigurations.getServerConfigurations();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("highMemoryServerList", highMemoryServerList);
	}

	private static void reloadDataCenterRegionList()
	{
		dataCenterRegionList = new ArrayList<DataCenterRegion>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "regions.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(DataCenterRegions.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			DataCenterRegions dataCenterRegions = (DataCenterRegions) jaxbUnmarshaller.unmarshal(file); 
			dataCenterRegionList = dataCenterRegions.getDataCenterRegions();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("dataCenterRegionList", dataCenterRegionList);
	}

	private static void reloadAdditionalOptionList()
	{
		additionalOptionList = new ArrayList<String>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "additional-option.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(AdditionalOption.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			AdditionalOption additionalOption = (AdditionalOption) jaxbUnmarshaller.unmarshal(file); 
			additionalOptionList = additionalOption.getOption();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("additionalOptionList", additionalOptionList);
	}

	private static void reloadSSHKeyList()
	{
		sshKeyList = new ArrayList<String>();
		try 
		{  
			File file = new File(AppConstants.XML_PATH + "ssh-key.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(SSHKey.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			SSHKey sshKey = (SSHKey) jaxbUnmarshaller.unmarshal(file); 
			sshKeyList = sshKey.getSshkey();
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		referenceDataMap.put("sshKeyList", sshKeyList);
	}
	
	public List<Distribution> getDistributionList()
	{
		return distributionList;
	}
	
	public List<OneClickApplication> getOneClickApplicationList()
	{
		return oneClickApplicationList;
	}
	
	public List<ServerConfiguration> getServerConfigurationList()
	{
		return serverConfigurationList;
	}
	
	public List<ServerConfiguration> getHighMemoryServerList()
	{
		return highMemoryServerList;
	}
	
	public List<DataCenterRegion> getDataCenterRegionList()
	{
		return dataCenterRegionList;
	}
	
	

}
