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

	@PostMapping("/unlockAccount")
	public String unlockAccount(@RequestParam String email, @RequestParam String tempPsw, @RequestParam String newPsw) {

		boolean unlockAccount = service.unlockAccount(email, tempPsw, newPsw);
		if (unlockAccount) {
			return "Account Unlocked, please proceed with login";
		}
		return "Please Enter the Correct Password provided in the registered mail id";
	}

	@GetMapping("/loadLoginForm")
	public String loadLoginForm(Model model) {

		return "userLogin";
	}

	@PostMapping("/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String password, Model model) {
		String msg = service.loginUser(email, password);
		model.addAttribute("msg", msg);
		return "userLogin";

	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email) {

		return service.forgotPsw(email);

	}

}
