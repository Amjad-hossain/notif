package com.codehaven.controller;



import com.codehaven.domain.User;

import com.codehaven.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.*;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping( value = "/admin/users.do", method = RequestMethod.GET)
	public String readUsers(ModelMap model) {

		logger.debug(">>> User list reading.");
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("timeZone", TimeZone.getAvailableIDs());
		return "user-list";
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