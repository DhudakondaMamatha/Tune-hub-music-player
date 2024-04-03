package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;


@CrossOrigin("*")
@RestController
public class UsersController
{
	@Autowired
	UsersService service;
	
	@Autowired
	SongService songService;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.addUser(user);
			System.out.println("user added");
		}
		else
		{
			System.out.println("user already exists");
		}
		
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session)
	{
		
		
		if(service.validateUser(email,password)==true)
		{
			String role=service.getRole(email);
			session.setAttribute("email",email);
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else
			{
			return "customerHome";
			}
		}
		else
		{
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	
	
}

