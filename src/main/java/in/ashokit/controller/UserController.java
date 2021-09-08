package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public String UserRegistration(@RequestBody User user) {
		boolean registerUser = service.registerUser(user);
		if (registerUser) {
			return "Please check your email to unlock account";
		}
		return "something went wrong please try again !!!";
	}

	@PostMapping("/unlockAccount")
	public String unlockAccount(@RequestParam String email, @RequestParam String tempPsw, @RequestParam String newPsw) {

		boolean unlockAccount = service.unlockAccount(email, tempPsw, newPsw);
		if (unlockAccount) {
			return "Account Unlocked, please proceed with login";
		}
		return "Please Enter the Correct Password provided in the registered mail id";
	}

	@PostMapping("/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String psw) {

		return service.loginUser(email, psw);

	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) {

		return service.forgotPsw(email);

	}

}
