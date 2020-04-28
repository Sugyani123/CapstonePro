package com.cts.fms.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.fms.jwt.model.Message;

import reactor.core.publisher.Mono;

public class ResourceRest {
	@RequestMapping(value = "/resource/user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public Mono<ResponseEntity<?>> user() {
		return Mono.just(ResponseEntity.ok(new Message("Content for user")));
	}
	
	@RequestMapping(value = "/resource/pmo", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PMO')")
	public Mono<ResponseEntity<?>> pmo() {
		return Mono.just(ResponseEntity.ok(new Message("Content for pmo")));
	}
	
	@RequestMapping(value = "/resource/poc", method = RequestMethod.GET)
	@PreAuthorize("hasRole('POC')")
	public Mono<ResponseEntity<?>> poc() {
		return Mono.just(ResponseEntity.ok(new Message("Content for poc")));
	}
	
	@RequestMapping(value = "/resource/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> admin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
	}
	
	@RequestMapping(value = "/resource/pmo-or-admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> pmoOrAdmin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for pmo or admin")));
	}
	
	@RequestMapping(value = "/resource/pmo-or-admin-or-poc", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PMO') or hasRole('ADMIN') or hasRole('POC')")
	public Mono<ResponseEntity<?>> pmoOrAdminOrPoc() {
		return Mono.just(ResponseEntity.ok(new Message("Content for pmo or admin or poc")));
	}


}
