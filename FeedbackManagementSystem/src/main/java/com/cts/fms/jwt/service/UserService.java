package com.cts.fms.jwt.service;

import java.util.Arrays;

import com.cts.fms.jwt.model.Role;
import com.cts.fms.jwt.model.User;

import reactor.core.publisher.Mono;

public class UserService {
	private final String userUsername = "user";// password: admin
	private final User user = new User(userUsername, "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG5770dhGtY=", true,
			Arrays.asList(Role.ROLE_USER));

	private final String userUsername1 = "naicy";// password: naicy
	private final User user1 = new User(userUsername1, "dbaDIej3XCnjvjyg8rK46eG8y4HNik+lJo8IY3B5Tkk=", true,
			Arrays.asList(Role.ROLE_PMO));

	// username:passwowrd -> pmo:pmo
	private final String pomUsername = "pmo";// password: pmo
	private final User pmo = new User(pomUsername, "S3ReJy0odJ/k1Kh7JYY/cPplbH+Gls/Y5BbO5fwGDE0=", true,
			Arrays.asList(Role.ROLE_PMO));
    //username:passwowrd -> poc:poc
	private final String pocUsername = "poc";// password: poc
	private final User poc = new User(pocUsername, "kXfZwSmUmW1Hh5Nf8vksyzkh/0J6UJoqWPUg/8ugg7Q=", true, Arrays.asList(Role.ROLE_POC));
	
	
	//username:passwowrd -> admin:admin
	private final String adminUsername = "admin";// password: admin
	private final User admin = new User(adminUsername, "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN));
	
	

	public Mono<User> findByUsername(String username) {
		if (username.equals(pomUsername)) {
			return Mono.just(pmo);
		} else if (username.equals(userUsername1)) {
			return Mono.just(user1);
		} else if (username.equals(userUsername)) {
			return Mono.just(user);
		}else if (username.equals(pocUsername)) {
			return Mono.just(poc);
		} 
		else if (username.equals(adminUsername)) {
			return Mono.just(admin);
		} else {
			return Mono.empty();
		}
	}
}
