//
////rest api
//
//package com.example.demo.controllers;
//
//import com.example.demo.mdoels.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.example.demo.services.UserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable int id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping
//    public void createUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable int id, @RequestBody User user) {
//        user.setId(id);
//        userService.updateUser(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//    }
//}


//thymeleaf

package com.example.demo.controllers;

import com.example.demo.mdoels.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show all users
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("listUsers", list);
        return "index";
    }

    // Show add user form
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    // Save user (POST)
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User existingUser = userService.getUserById(id);
        model.addAttribute("user", existingUser);
        return "edit";
    }

    // Update user
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

