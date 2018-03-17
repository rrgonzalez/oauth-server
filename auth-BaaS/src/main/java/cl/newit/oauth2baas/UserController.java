package cl.newit.oauth2baas;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/user_info")
	public Principal user(Principal user) {
		return user;
	}

}
