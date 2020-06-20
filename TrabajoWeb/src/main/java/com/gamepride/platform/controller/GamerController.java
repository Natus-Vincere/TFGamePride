package com.gamepride.platform.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gamepride.platform.model.Gamer;
import com.gamepride.platform.service.IGamerService;

@Controller
@SessionAttributes("gamer")
@RequestMapping("/gamers")
public class GamerController {

	@Autowired
	private IGamerService gamerService;

	@GetMapping("/register")
	public String newGamer(Model model) throws Exception {
		model.addAttribute("gamer", new Gamer());
		return "/gamer/gamerForm";
	}

	@PostMapping("/save")
	public String saveGamer(@Valid Gamer gamer, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/gamer/gamerForm";
		} else {
			if (gamerService.create(gamer)>0) {
				model.addAttribute("error", "Este usuario ya existe");
				return "/gamer/gamerForm";
			} else {
				model.addAttribute("info", "Usuario registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("gamers",gamerService.getGamers());
		return "redirect:/login";
	}

	@GetMapping("/list")
	public String listGamers(Model model) {
		try {
			model.addAttribute("gamer", new Gamer());
			model.addAttribute("gamers", gamerService.getGamers());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/gamer/gamerList";
	}

	@GetMapping("/search")
	public String searchGamer(@RequestParam("name") String name, Model model) {
		try {
			if (!name.isEmpty()) {
				List<Gamer> gamers = gamerService.findByUsername(name);
				if (!gamers.isEmpty()) {
					model.addAttribute("gamers", gamers);
				} else {
					model.addAttribute("info", "Competidor no existe");
					model.addAttribute("gamers", gamerService.getGamers());
				}
			} else {
				model.addAttribute("info", "Debe ingresar un nombre");
				model.addAttribute("gamers", gamerService.getGamers());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/gamer/gamerList";
	}
	
	@GetMapping("/profile/{id}")
	public String profile(@PathVariable(value="id") Long id, Model model) {
		try {
			Optional<Gamer> gamer=gamerService.findById(id);
			
			if(!gamer.isPresent()) {
				model.addAttribute("info","Competidor no existe");
				return "redirect:/gamers/list";
			}else {
				model.addAttribute("gamer",gamer.get());
			}
			
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		
		return "/gamer/profile";
	}
}