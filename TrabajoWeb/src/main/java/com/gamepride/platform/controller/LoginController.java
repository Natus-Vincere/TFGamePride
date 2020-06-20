package com.gamepride.platform.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gamepride.platform.model.Gamer;

@Controller
@RequestMapping
public class LoginController {

	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,Gamer gamer,
			RedirectAttributes flash) {

		if (principal != null) {
			return "redirect:/home";
		}

		if (error != null) {
			model.addAttribute("error",
					"Nombre de usuario o contraseña incorrectos, ingrese credenciales correctas por favor.");
		}
		
		if(logout!=null) {
			model.addAttribute("success","Ha cerrado su sesión con éxito");
		}
		
		return "login";
	}

}
