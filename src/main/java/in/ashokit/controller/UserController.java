package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import in.ashokit.entity.Country;
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

		List<Country> countries = service.getCountriesList();

		model.addAttribute("user", user);
		model.addAttribute("countries", countries);

		return "userReg";
	}

	@ResponseBody
	@GetMapping("/getStatesByCountry/{countryId}")
	public String getStatesByCountry(@PathVariable Integer countryId) {
		Gson gson = new Gson();
		return gson.toJson(service.getStatesListByCountryId(countryId));
	}

	@ResponseBody
	@GetMapping("/getCitiesByState/{stateId}")
	public String getCitiesByState(@PathVariable Integer stateId) {
		Gson gson = new Gson();
		System.out.println(stateId);
		return gson.toJson(service.getCitiesByStateId(stateId));
	}

	@ResponseBody
	@GetMapping("/getCountries")
	public String getCountries() {
		Gson gson = new Gson();
		return gson.toJson(service.getCountriesList());
	}

	@ResponseBody
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		try {
			return new ResponseEntity<User>(service.getUserById(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/register")
	public String UserRegistration(@ModelAttribute User user, Model model) {
		boolean registerUser = service.registerUser(user);
		System.out.println(user);
		if (registerUser) {

			model.addAttribute("msg", "Successfully registered, Please check your registered email to unlock account");
			model.addAttribute("user", new User());
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

	@GetMapping("/viewUsers")
	public String viewUsers(Model model) {
		model.addAttribute("users", service.getAllUsers());
		model.addAttribute("user", new User());
		return "viewUsers";
	}

	@PostMapping("/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String password, Model model) {
		String[] msg = service.loginUser(email, password);
		if (msg[1].equalsIgnoreCase("failure")) {
			model.addAttribute("msg", msg[0]);
			return "userLogin";
		}

		model.addAttribute("msg", msg[0]);

		return "redirect:viewUsers";

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

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam Integer userId, Model model) {
		boolean deleteUser = service.deleteUser(userId);
		if (deleteUser) {
			model.addAttribute("deleteMsg", "User Deleted Successfully...");
		} else {
			model.addAttribute("deleteMsg", "Something Went Wrong !!!");
		}
		return "redirect:viewUsers";
	}

}
