package com.myclass.demo.controller;

import com.myclass.demo.entity.Role;
import com.myclass.demo.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model) {
        // LẤY DANH SÁCH ROLE TỪ DATABASE
        List<Role> list = roleService.findAll();
        // CHUYỂN TIẾP DS ROLE QUA CHO THYMELEAF (index.html)
        model.addAttribute("roles", list);
        return "role/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String add(@RequestParam("keyword") String keyword, ModelMap model) {
        if (keyword.isEmpty())
            model.addAttribute("roles", roleService.findAll());
        else
            model.addAttribute("roles", roleService.searchByName(keyword));
        return "role/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("role", new Role());
        return "role/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String post(@ModelAttribute("role") Role role, ModelMap model) {
        roleService.save(role);
        return "redirect:/role";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, ModelMap model) {

        model.addAttribute("role", roleService.findById(id));
        return "role/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("role") Role role, ModelMap model) {
        roleService.save(role);
        return "role/index";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        roleService.delete(id);
        return "redirect:/role";
    }
}
