package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UserForm;
import in.ashokit.service.UserService;

@RestController
public class UserRegRestController {
	@Autowired
	private UserService service;

	@GetMapping("/getStatesByCountry/{countryId}")
	public Map<Integer, String> getStatesByCountry(@PathVariable Integer countryId) {
		return service.getStates(countryId);
	}

	@GetMapping("/getCitiesByState/{stateId}")
	public Map<Integer, String> getCitiesByState(@PathVariable Integer stateId) {
		return service.getCities(stateId);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return service.getCountries();
	}

	@GetMapping("/emailUnique")
	public String emailUniqueCheck(@RequestParam String email) {

		return (service.emailUnique(email)) ? "" : "Email-Id already exists";

	}

	@PostMapping("/register")
	public String UserRegistration(@RequestBody UserForm userForm) {
		String msg = "";
		try {
			boolean registerUser = service.saveUser(userForm);
			if (registerUser) {
				msg = "Successfully registered, Please check your registered email to unlock account";
			} else {
				msg = "something went wrong please try again !!!";
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
}
