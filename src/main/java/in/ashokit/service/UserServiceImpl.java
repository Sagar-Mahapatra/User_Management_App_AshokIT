package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;

	@Override
	public boolean registerUser(User user) {
		user.setAccountStatus("Locked");
		user.setPassword("dummy");
		User savedUser = userRepo.save(user);

		return savedUser != null && savedUser.getUserId() != null;
	}

	@Override
	public Map<Integer, String> getCountries() {

		List<Country> list = countryRepo.findAll();

		Map<Integer, String> countries = new HashMap<>();

		for (Country c : list) {
			countries.put(c.getCountryId(), c.getCountryName());
		}

		return countries;
	}

	@Override
	public Map<Integer,String> getStates(Integer countryId) {
		Map<Integer,String> states=new HashMap<>();
		List<State> statesByCountryId = stateRepo.getStatesByCountryId(countryId);
		for(State s:statesByCountryId){
			states.put(s.getStateId(), s.getStateName());
		}

		return states;
	}

	@Override
	public List<City> getCities(Integer stateId) {

		return cityRepo.findAll();
	}

	@Override
	public boolean unlockAccount(String email, String tempPsw, String newPsw) {

		User user = userRepo.getUserByEmail(email);

		if (user != null) {
			if (user.getPassword().equalsIgnoreCase(tempPsw)) {
				user.setPassword(newPsw);
				user.setAccountStatus("Unlocked");
				userRepo.save(user);
				return true;
			}
		}

		return false;
	}

	@Override
	public String loginUser(String email, String psw) {
		User user = userRepo.getUserByEmailAndPsw(email, psw);
		if (user == null) {
			return "Invalid Credentials";
		} else if (user.getAccountStatus().equalsIgnoreCase("Locked")) {
			return "Your Account is Locked";
		} else {
			return "Welcome To Ashok IT.....";
		}

	}

	@Override
	public String forgotPsw(String email) {
		User user = userRepo.getUserByEmail(email);
		if (user == null) {
			return "We can't find a user with that e-mail address";
		}

		String password = user.getPassword();

//		logic to send password to mail id

		return "we have sent your password on registered email:- " + password;

	}

}
