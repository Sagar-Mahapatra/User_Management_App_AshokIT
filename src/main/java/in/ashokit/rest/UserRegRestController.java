package in.ashokit.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UserForm;
import in.ashokit.constants.AppContstants;
import in.ashokit.props.AppProperties;
import in.ashokit.service.UserService;

@RestController
public class UserRegRestController {

	private UserService service;

	private AppProperties props;

	public UserRegRestController(UserService service, AppProperties props) {

		this.service = service;
		this.props = props;
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStatesByCountry(@PathVariable Integer countryId) {
		return service.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCitiesByState(@PathVariable Integer stateId) {
		return service.getCities(stateId);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return service.getCountries();
	}

	@GetMapping("/emailUnique")
	public String emailUniqueCheck(@RequestParam String email) {

		return (service.emailUnique(email)) ? AppContstants.UNIQUE : AppContstants.DUPLICATE;

	}

	@PostMapping("/register")
	public String UserRegistration(@RequestBody UserForm userForm) {

		try {
			boolean registerUser = service.saveUser(userForm);
			if (registerUser) {
				return props.getMessages().get(AppContstants.REG_SUCCESS);
			} else {
				return props.getMessages().get(AppContstants.REG_FAIL);
			}
		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
