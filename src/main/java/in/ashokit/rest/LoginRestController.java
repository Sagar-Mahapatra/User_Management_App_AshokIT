package in.ashokit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.LoginForm;
import in.ashokit.service.UserService;

@RestController
public class LoginRestController {

	private UserService service;

	public LoginRestController(UserService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.loginCheck(loginForm);

	}
}
