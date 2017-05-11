package com.igene.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.igene.demo.model.Droplet;
import com.igene.demo.service.DropletService;
import com.igene.demo.utils.AppConstants;

@RestController
@RequestMapping("/droplets")
public class DropletController {

	@Autowired
	DropletService userService;

	@RequestMapping(method = RequestMethod.GET, value="data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getReferenceData(HttpSession session) {
		try {
			if(session.getAttribute(AppConstants.USER) == null)
			{
				long userId = System.currentTimeMillis();
				session.setAttribute(AppConstants.USER, userId);
			}
			return new ResponseEntity(userService.getReferenceData(), HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getDroplet(HttpSession session, @PathVariable("id") int id) {
		try {
			return new ResponseEntity(userService.getDroplet((long)session.getAttribute(AppConstants.USER), id), HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getDropletList(HttpSession session) {
		try {
			if(session.getAttribute(AppConstants.USER) == null)
			{
				long userId = System.currentTimeMillis();
				session.setAttribute(AppConstants.USER, userId);
			}
			return new ResponseEntity(userService.getDropletList((long)session.getAttribute(AppConstants.USER)), HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDropletList(HttpSession session, @RequestBody Droplet droplet) {
		try {
			if(session.getAttribute(AppConstants.USER) == null)
			{
				long userId = System.currentTimeMillis();
				session.setAttribute(AppConstants.USER, userId);
			}
			droplet.setUserId((Long)session.getAttribute(AppConstants.USER));
			droplet.setCreatedOn(new Date(System.currentTimeMillis()));
			userService.saveDroplet(droplet);
			return new ResponseEntity(userService.getDropletList(droplet.getUserId()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDropletList(HttpSession session, @RequestBody Droplet droplet) {
		try {
			if(session.getAttribute(AppConstants.USER) == null)
			{
				long userId = System.currentTimeMillis();
				session.setAttribute(AppConstants.USER, userId);
			}
			userService.saveDroplet(droplet);
			return new ResponseEntity(userService.getDropletList(droplet.getUserId()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}