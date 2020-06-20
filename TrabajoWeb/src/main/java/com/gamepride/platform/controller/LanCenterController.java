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
import com.gamepride.platform.model.LanCenter;
import com.gamepride.platform.service.IEventService;
import com.gamepride.platform.service.IGamerService;
import com.gamepride.platform.service.ILanCenterService;

@Controller
@SessionAttributes("lancenter")
@RequestMapping("/lancenters")
public class LanCenterController {

	@Autowired
	private ILanCenterService lancenterService;

	@Autowired
	private IGamerService gamerService;

	@Autowired
	private IEventService eventService;

	@GetMapping("/register")
	public String newLanCenter(Model model) throws Exception {
		model.addAttribute("lancenter", new LanCenter());
		model.addAttribute("gamers", gamerService.getGamers());
		model.addAttribute("events", eventService.getEvents());
		return "/lancenter/lancenter";
	}

	@GetMapping("/form/{gamerId}")
	public String formLanCenter(@PathVariable(value = "gamerId") Long gamerId, Model model) throws Exception {
		try {
			Optional<Gamer> gamer = gamerService.findById(gamerId);
			if (!gamer.isPresent()) {
				model.addAttribute("info", "Usuario no existe");
				return "redirect:/gamers/profile/" + gamer.get().getId();
			} else {
				LanCenter lancenter = new LanCenter();
				lancenter.setGamerId(gamer.get());
				model.addAttribute("lancenter", lancenter);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/lancenter/lancenter";
	}

	@PostMapping("/save")
	public String saveLanCenter(@Valid LanCenter lancenter, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("events", eventService.getEvents());
			return "/lancenter/lancenter";
		} else {
			if (lancenterService.create(lancenter) > 0) {
				model.addAttribute("info", "Usted ya cuenta con un Lan Center");
				model.addAttribute("gamers", gamerService.getGamers());
				model.addAttribute("events", eventService.getEvents());
				return "/lancenter/lancenter";
			} else {
				model.addAttribute("info", "Lan Center registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("lancenters", lancenterService.getLanCenters());
		return "/gamers/gamerList";
	}

	@GetMapping("/list")
	public String listLanCenters(Model model) {
		try {
			model.addAttribute("lancenter", new LanCenter());
			model.addAttribute("lancenters", lancenterService.getLanCenters());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/lancenter/lancenterList";
	}

	@GetMapping("/search")
	public String searchLanCenter(@RequestParam("name") String name, Model model) {
		try {
			if (!name.isEmpty()) {
				List<LanCenter> lancenters = lancenterService.findByName(name);
				if (!lancenters.isEmpty()) {
					model.addAttribute("lancenters", lancenters);
				} else {
					model.addAttribute("info", "Lan Center no existe");
					model.addAttribute("lancenters", lancenterService.getLanCenters());
				}
			} else {
				model.addAttribute("info", "Debe ingresar un nombre");
				model.addAttribute("lancenters", lancenterService.getLanCenters());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/lancenter/lancenterList";
	}
}
