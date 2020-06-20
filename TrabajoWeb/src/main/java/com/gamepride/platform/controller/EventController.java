package com.gamepride.platform.controller;

import java.util.List;

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

import com.gamepride.platform.model.Event;
import com.gamepride.platform.service.IEventService;
import com.gamepride.platform.service.IGamerService;
import com.gamepride.platform.service.ILanCenterService;

@Controller
@SessionAttributes("event")
@RequestMapping("/events")
public class EventController {

	@Autowired
	private IEventService eventService;

	@Autowired
	private IGamerService gamerService;

	@Autowired
	private ILanCenterService lancenterService;

	@GetMapping("/register")
	public String newEvent(Model model) throws Exception {
		model.addAttribute("event", new Event());
		model.addAttribute("gamers", gamerService.getGamers());
		model.addAttribute("lancenters",lancenterService.getLanCenters());
		return "event/eventForm";
	}

	@PostMapping("/save")
	public String saveEvent(@Valid Event event, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("lancenters", lancenterService.getLanCenters());
			model.addAttribute("gamers", gamerService.getGamers());
			return "event/eventForm";
		} else {
			if (eventService.create(event) > 0) {
				model.addAttribute("info", "El evento ya existe");
				model.addAttribute("lancenters", lancenterService.getLanCenters());
				model.addAttribute("gamers", gamerService.getGamers());
				return "event/eventForm";
			} else {
				model.addAttribute("info", "Evento creado correctamente");
				status.setComplete();
			}

			model.addAttribute("events", eventService.getEvents());
			return "/event/eventList";
		}
	}

	@GetMapping("/list")
	public String listEvents(Model model) {
		try {
			model.addAttribute("event", new Event());
			model.addAttribute("events", eventService.getEvents());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/event/eventList";
	}

	@GetMapping("/search")
	public String searchEvent(@RequestParam("game") String game, Model model) {
		try {
			if (!game.isEmpty()) {
				List<Event> events = eventService.findByGame(game);
				if (!events.isEmpty()) {
					model.addAttribute("events", events);
				} else {
					model.addAttribute("info", "No existe evento");
					model.addAttribute("events", eventService.getEvents());
				}
			} else {
				model.addAttribute("info", "Debe ingresar un nombre");
				model.addAttribute("events", eventService.getEvents());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/event/eventList";
	}
	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable(value = "id") Long id, Model model) throws Exception {

		eventService.deleteById(id);
		return "redirect:/events/";

	}

}
