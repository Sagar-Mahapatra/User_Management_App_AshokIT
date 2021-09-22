package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.UserService;

@RestController
public class ForgotPswRestController {

	private UserService service;

	public ForgotPswRestController(UserService service) {
		this.service = service;
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) {
		return service.forgotPwd(email);
	}
}
