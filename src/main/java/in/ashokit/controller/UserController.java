package in.ashokit.controller;

import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/loadRegForm")
	public String loadRegistrationForm(Model model) {

		UserForm userForm = new UserForm();

		Map<Integer, String> countries = service.getCountries();
		System.out.println(countries);

		model.addAttribute("userForm", userForm);
		model.addAttribute("countries", countries);

		return "userReg";
	}

	@ResponseBody

	@GetMapping("/getStatesByCountry/{countryId}")
	public String getStatesByCountry(@PathVariable Integer countryId) {
		Gson gson = new Gson();
		return gson.toJson(service.getStates(countryId));
	}

	@ResponseBody

	@GetMapping("/getCitiesByState/{stateId}")
	public String getCitiesByState(@PathVariable Integer stateId) {
		Gson gson = new Gson();
		System.out.println(stateId);
		return gson.toJson(service.getCities(stateId));
	}

	@ResponseBody

	@GetMapping("/getCountries")
	public String getCountries() {
		Gson gson = new Gson();
		return gson.toJson(service.getCountries());
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

	@GetMapping("/emailUnique")
	@ResponseBody
	public String emailUniqueCheck(@RequestParam String email) {

		return (service.emailUnique(email)) ? "" : "Email-Id already exists";

	}

	@GetMapping("/phNoUnique")

	@ResponseBody
	public String phNoUniqueCheck(@RequestParam String phNo) {
		boolean isphNoUnique = service.isphNoUnique(phNo);
		return (isphNoUnique) ? "" : "Phone Number already exist";

	}

	@PostMapping("/register")
	public String UserRegistration(@ModelAttribute UserForm userForm, Model model) {
		try {
			boolean registerUser = service.saveUser(userForm);
			if (registerUser) {

				model.addAttribute("msg",
						"Successfully registered, Please check your registered email to unlock account");
				model.addAttribute("userForm", new UserForm());
			} else {
				model.addAttribute("msg", "something went wrong please try again !!!");
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
		}

		return "userReg";
	}

	@GetMapping("/loadUnlockAccountForm")
	public String loadUnlockAccountForm(@RequestParam(required = false) String email, Model model) {
		model.addAttribute("email", email);
		return "unlockAccount";
	}

	@PostMapping("/unlockAccount")
	public String unlockAccount(@ModelAttribute UnlockAccForm unlockAccForm, Model model) {

		boolean unlockAccount = service.unlockAccount(unlockAccForm);
		if (unlockAccount) {
			model.addAttribute("msg", "Account Unlocked, please proceed with login");

		} else {
			model.addAttribute("msg",
					"Please Enter the Registered email id & correct Temporary Password provided in the registered mail id");
		}

		return "unlockAccount";
	}

	@GetMapping("/loadLoginForm")
	public String loadLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "userLogin";
	}

	@GetMapping("/viewUsers")
	public String viewUsers(Model model, @RequestParam(required = false) String msg) {

		model.addAttribute("users", service.getAllUsers());
		model.addAttribute("user", new User());
		model.addAttribute("msg", msg);
		return "viewUsers";
	}

	@PostMapping("/userLogin")
	public String userLogin(@ModelAttribute LoginForm loginForm, Model model) {
		String msg = service.loginCheck(loginForm);
		System.out.println(msg);

		model.addAttribute("msg", msg);
		return "userLogin";

	}

	@GetMapping("/loadForgotPasswordForm")
	public String loadForgotPasswordForm() {

		return "forgotPsw";
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email, Model model) {
		String msg = service.forgotPwd(email);

		model.addAttribute("msg", msg);

		return "forgotPsw";

	}

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam Integer userId, Model model, RedirectAttributes attr) {
		boolean deleteUser = service.deleteUser(userId);
		if (deleteUser) {
			attr.addAttribute("msg", "User Deleted Successfully...");
		} else {
			attr.addAttribute("msg", "Something Went Wrong !!!");
		}
		return "redirect:viewUsers";
	}

}
