package com.cts.fms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fms.jwt.model.AuthRequest;
import com.cts.fms.jwt.model.AuthResponse;
import com.cts.fms.jwt.service.UserService;
import com.cts.fms.jwt.util.JWTUtil;
import com.cts.fms.jwt.util.PBKDF2Encoder;

import reactor.core.publisher.Mono;

@RestController
public class AuthenticationRest {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		return userRepository.findByUsername(ar.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}
