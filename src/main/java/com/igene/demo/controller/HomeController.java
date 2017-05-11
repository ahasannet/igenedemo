package com.igene.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.igene.demo.utils.AppConstants;

@Controller
@RequestMapping("/")
public class HomeController {

	  @RequestMapping(method = RequestMethod.GET)
	  public String getHomePage(HttpSession session) {
		  if(session.getAttribute(AppConstants.USER) == null)
		  {
			  long userId = System.currentTimeMillis();
			  session.setAttribute(AppConstants.USER, userId);
		  }
		  return AppConstants.HOME_PAGE;
	  }

}