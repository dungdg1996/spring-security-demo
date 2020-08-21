package com.myclass.demo.controller;

import com.myclass.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("users", userService.findAll());
		return "user/index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		return "user/add";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model) {
		model.addAttribute("users", userService.findAll());
		return "user/edit";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam Integer id) {
		userService.delete(id);
		return "redirect:/user";
	}
}
