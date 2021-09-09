package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/loadRegForm")
	public String loadRegistrationForm(Model model) {

		User user = new User();
		Map<Integer, String> countries = service.getCountries();
		Map<Integer, String> states = service.getStates(1);
		model.addAttribute("states", states);
		model.addAttribute("user", user);
		model.addAttribute("countries", countries);

		return "userReg";
	}

	@PostMapping("/register")
	public String UserRegistration(@ModelAttribute User user, Model model) {
		boolean registerUser = service.registerUser(user);
		if (registerUser) {

			model.addAttribute("msg", "Successfully registered, Please check your registered email to unlock account");
		} else {
			model.addAttribute("msg", "something went wrong please try again !!!");
		}

		return "userReg";
	}

	@GetMapping("/loadUnlockAccountForm")
	public String loadUnlockAccountForm() {

		return "unlockAccount";
	}

	@PostMapping("/unlockAccount")
	public String unlockAccount(@RequestParam String email, @RequestParam String tempPsw, @RequestParam String newPsw,
			Model model) {

		boolean unlockAccount = service.unlockAccount(email, tempPsw, newPsw);
		if (unlockAccount) {
			model.addAttribute("msg", "Account Unlocked, please proceed with login");

		} else {
			model.addAttribute("msg",
					"Please Enter the Registered email id & Password provided in the registered mail id");
		}

		return "unlockAccount";
	}

	@GetMapping("/loadLoginForm")
	public String loadLoginForm() {

		return "userLogin";
	}

	@PostMapping("/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String password, Model model) {
		String msg = service.loginUser(email, password);
		model.addAttribute("msg", msg);
		return "userLogin";

	}

	@GetMapping("/loadForgotPasswordForm")
	public String loadForgotPasswordForm() {

		return "forgotPsw";
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email, Model model) {
		String msg = service.forgotPsw(email);

		model.addAttribute("msg", msg);

		return "forgotPsw";

	}

}
