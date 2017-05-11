package com.igene.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igene.demo.model.DataCenterRegion;
import com.igene.demo.model.Distribution;
import com.igene.demo.model.Droplet;
import com.igene.demo.model.OneClickApplication;
import com.igene.demo.model.ServerConfiguration;
import com.igene.demo.model.ViewDroplet;
import com.igene.demo.repository.DropletRepository;
import com.igene.demo.utils.AppConstants;


@Service("userService")
public class DropletService {

	private static final Logger logger = LoggerFactory.getLogger(DropletService.class);

	@Autowired
	private ServletContext servletContext;

	public Map<String, Object> getReferenceData()
	{
		String classPath = servletContext.getRealPath("/WEB-INF/classes/");

		AppConstants.XML_PATH = classPath;

		return DropletRepository.getInstance().getReferenceData();
	}

	public void saveDroplet(Droplet droplet)
	{
		DropletRepository.getInstance().saveDroplet(droplet);
	}
	
	public Droplet getDroplet(long userId, int id)
	{
		List<Droplet> list = DropletRepository.getInstance().getDropletList(userId);
		for(Droplet droplet : list)
		{
			if(droplet.getId() == id)
			{
				return droplet;
			}
		}
		return null;
	}

	public List<ViewDroplet> getDropletList(long userId)
	{
		List<ViewDroplet> dropletList =  new ArrayList<ViewDroplet>();
		List<Droplet> list = DropletRepository.getInstance().getDropletList(userId); 

		ViewDroplet viewDroplet = null;
		for(Droplet droplet : list)
		{
			viewDroplet = new ViewDroplet();
			viewDroplet.setId(droplet.getId());

			for(Distribution distribution : DropletRepository.getInstance().getDistributionList())
			{
				if(distribution.getId() == droplet.getDistribution())
				{
					viewDroplet.setDistribution(distribution.getName());
				}
			}
			viewDroplet.setVersion(droplet.getVersion());

			StringBuilder applications = new StringBuilder();
			for(Integer application : droplet.getApplications())
			{
				for(OneClickApplication oneClickApplication : DropletRepository.getInstance().getOneClickApplicationList())
				{
					if(oneClickApplication.getId() == application)
					{
						if(applications.toString().length() > 0)
						{
							applications.append(", ");
						}
						applications.append(oneClickApplication.getName());
						break;
					}
				}
			}
			viewDroplet.setApplications(applications.toString());

			if(droplet.getServerType() == 1)
			{
				viewDroplet.setServerType("Standard");
				for(ServerConfiguration configuration : DropletRepository.getInstance().getServerConfigurationList())
				{
					if(configuration.getId() == droplet.getConfiguration())
					{
						viewDroplet.setConfiguration(configuration);
						break;
					}
				}
			}
			else
			{
				viewDroplet.setServerType("High Memory");
				for(ServerConfiguration configuration : DropletRepository.getInstance().getHighMemoryServerList())
				{
					if(configuration.getId() == droplet.getConfiguration())
					{
						viewDroplet.setConfiguration(configuration);
						break;
					}
				}
			}

			for(DataCenterRegion dataCenterRegion : DropletRepository.getInstance().getDataCenterRegionList())
			{
				if(dataCenterRegion.getId() == droplet.getDataCenter())
				{
					viewDroplet.setDataCenter(dataCenterRegion.getName());
					break;
				}
			}
			viewDroplet.setSlot(droplet.getSlot());

			StringBuilder additionalOptions = new StringBuilder();

			for(String additionalOption : droplet.getAdditionalOptions())
			{
				if(additionalOptions.toString().length() > 0)
				{
					additionalOptions.append(", ");
				}
				additionalOptions.append(additionalOption);
			}
			viewDroplet.setAdditionalOptions(additionalOptions.toString());

			StringBuilder sshKeys = new StringBuilder();

			for(String sshKey : droplet.getSshKeys())
			{
				if(sshKeys.toString().length() > 0)
				{
					sshKeys.append(", ");
				}
				sshKeys.append(sshKey);
			}
			viewDroplet.setSshKeys(sshKeys.toString());
			viewDroplet.setNumberOfDroplets(droplet.getNumberOfDroplets());
			viewDroplet.setHostname(droplet.getHostname());
			viewDroplet.setCreatedOn(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(droplet.getCreatedOn()));
			
			int ONE_HOUR = 3600000;
			
			float usage = ((System.currentTimeMillis() - droplet.getCreatedOn().getTime())/ONE_HOUR)*viewDroplet.getConfiguration().getHourlyRate();
			
			viewDroplet.setUsage(usage);
			
			dropletList.add(viewDroplet);
		}

		return dropletList;
	}


}
