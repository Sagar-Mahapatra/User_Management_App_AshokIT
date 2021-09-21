package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.UserService;

@RestController
public class ForgotPswRestController {
	@Autowired
	private UserService service;

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) {
		return service.forgotPwd(email);
	}
}
