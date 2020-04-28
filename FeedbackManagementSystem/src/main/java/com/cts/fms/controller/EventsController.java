package com.cts.fms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fms.model.Dashboard;
import com.cts.fms.model.Event;
import com.cts.fms.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/events")
public class EventsController {

	@Autowired
	private EventService eventService;

	@GetMapping(value = "/getDashboard")
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN')")
	public Flux<Dashboard> dashboardData() {
		Dashboard dashBoard = new Dashboard();

		Mono<Long> countMono = eventService.getAllEventList().count();
		countMono.subscribe(subs -> dashBoard.setCount(Integer.parseInt(subs.toString())));

		Mono<Integer> totalVolunteersCount = eventService.getAllEventList().map(x -> x.getTotalVolunteers()).reduce(0,
				(p, q) -> p + q);
		totalVolunteersCount.subscribe(subs -> dashBoard.setTotalVolunteers(Integer.parseInt(subs.toString())));

		Mono<Integer> livesImpactedCount = eventService.getAllEventList().map(x -> x.getLives_impacted()).reduce(0,
				(p, q) -> p + q);
		livesImpactedCount.subscribe(subs -> dashBoard.setLivesImpacted(Integer.parseInt(subs.toString())));

		Mono<Integer> totalParticipantsCount = eventService.getAllEventList().map(x -> x.getTotalParticipants()).reduce(0,
				(p, q) -> p + q);
		totalParticipantsCount.subscribe(subs -> dashBoard.setTotalParticipants(Integer.parseInt(subs.toString())));

		List<Dashboard> list = new ArrayList<>();
		list.add(dashBoard);

		return Flux.fromIterable(list);

	}

	@GetMapping("/getEvents/{eventId}")
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN') or hasRole('POC')")
	public Flux<Event> getEventsById(@PathVariable String eventId) {
		return eventService.searchEventsById(eventId);
	}

	@GetMapping("/getEventsById/{id}")
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN') or hasRole('POC')")
	public Mono<Event> getEventsById(@PathVariable int id) {
		return eventService.findEventById(id);
	}

	@GetMapping("/getEventsByVh/{vh}")
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN') or hasRole('POC')")
	public Flux<Event> getEventsByVolunteerHours(@PathVariable int vh) {
		return eventService.searchEventsByVH(vh);
	}

	@GetMapping("/sendEmail")
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN') ")
	public Mono<String> sendEmail() {
		return eventService.SendMail();
	}
}
