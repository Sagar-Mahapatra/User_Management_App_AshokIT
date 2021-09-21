package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.LoginForm;
import in.ashokit.service.UserService;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserService service;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.loginCheck(loginForm);

	}
}
