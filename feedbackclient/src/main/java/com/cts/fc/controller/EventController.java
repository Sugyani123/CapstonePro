package com.cts.fc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cts.fc.domain.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EventController {
	
	WebClient webClient = WebClient.create("http://localhost:8089");

	@GetMapping("/client/retrieveEvents")
	public Flux<Event> getEventsByUsingRetrieve() {
		return webClient.get().uri("/getEvents").retrieve().bodyToFlux(Event.class)
				.log("get all events in client project");
	}

	@GetMapping("/client/retrieve/eventById")
	public Flux<Event> getSpecificEventByUsingRetrieve() {
		String eventId = "EVNT00047261";
		return webClient.get().uri("/getEvents/{eventId}", eventId).retrieve().bodyToFlux(Event.class)
				.log("get specific event in client project");
	}

	@GetMapping("/client/sendMail")
	public Mono<String> sendMail() {
		return webClient.get().uri("/sendEmail").retrieve().bodyToMono(String.class);
	}


}
