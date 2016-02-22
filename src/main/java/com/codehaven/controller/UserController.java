package com.codehaven.controller;



import com.codehaven.domain.User;

import com.codehaven.service.UserService;
import com.codehaven.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.*;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping( value = "/admin/users.do", method = RequestMethod.GET)
	public String readUsers(ModelMap model) {

		logger.info(">>> User list reading.");
		model.addAttribute("timeZone", TimeZone.getAvailableIDs());
		return "user-list";
	}

	@RequestMapping( value = "/admin/userList.do", method = RequestMethod.GET)
	public @ResponseBody Map getUserList(HttpServletRequest request) {

		logger.info(">>> getUserList:: by ajax");
		int start = request.getParameter(Constants.START) != null ? Integer.parseInt(request.getParameter(Constants.START)) : 0;
		int length = request.getParameter(Constants.LENGTH) != null ? Integer.parseInt(request.getParameter(Constants.LENGTH)) : 5;
		int echo = request.getParameter(Constants.ECHO) != null ? Integer.parseInt(request.getParameter(Constants.ECHO)) : 0;
		String searchKey = request.getParameter(Constants.SEARCH) != null ? request.getParameter(Constants.SEARCH) : "";
		String sortType = request.getParameter(Constants.ORDER) != null ? request.getParameter(Constants.ORDER) : "asc";

		try {
			logger.debug("--- Query string:: " + URLDecoder.decode(request.getQueryString(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.debug("AMLOG: length: " + length + " echo :" + echo +" start:" + start + " sortType:" + sortType + " searchKey:" + searchKey);
		List<User> users = userService.findAll();
		if(users != null && !users.isEmpty()) {
			logger.info("--- getUserList:: size[{}]", users.size());
		} else {
			logger.info("--- getUserList:: Empty list");
		}
		Map<String, List> response = new HashMap<String, List>();
		response.put("data", users);
		return response;
	}

	@RequestMapping( value = "/admin/user.do", method = RequestMethod.GET)
	public String getPersonForm(ModelMap model) {

		logger.debug(">>> Add Person form");
		model.addAttribute("user", new User());
		model.addAttribute("timeZoneList", TimeZone.getAvailableIDs() );
		return "user";
	}

	@RequestMapping( value = "/admin/user.do", method = RequestMethod.POST)
	public String savePerson(@ModelAttribute("user") User user) {

		logger.debug(">>> Add Person form");
		userService.addUser(user);
		return "redirect:./users.do";
	}

	@RequestMapping(value = "/*/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}." , locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );
		return "home";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		logger.info("About page !");

		return "about";
	}



}