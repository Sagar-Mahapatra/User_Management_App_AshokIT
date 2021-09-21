package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.entity.User;
import in.ashokit.service.UserService;

@Controller
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
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		try {
			return new ResponseEntity<User>(service.getUserById(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/phNoUnique")
	@ResponseBody
	public String phNoUniqueCheck(@RequestParam String phNo) {
		boolean isphNoUnique = service.isphNoUnique(phNo);
		return (isphNoUnique) ? "" : "Phone Number already exist";

	}

	@GetMapping("/loadUnlockAccountForm")
	public String loadUnlockAccountForm(@RequestParam(required = false) String email, Model model) {
		model.addAttribute("email", email);
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

	@GetMapping("/loadForgotPasswordForm")
	public String loadForgotPasswordForm() {

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
