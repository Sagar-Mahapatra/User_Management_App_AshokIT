package in.ashokit.service;

import java.util.List;
import java.util.Optional;

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
	public List<Country> getCountriesList() {

		return countryRepo.findAll();
	}

	@Override
	public List<State> getStatesListByCountryId(Integer countryId) {

		return stateRepo.getStatesByCountryId(countryId);
	}

	@Override
	public List<City> getCitiesByStateId(Integer stateId) {

		return cityRepo.getCitiesByStateId(stateId);
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
	public String[] loginUser(String email, String psw) {
		String[] msg = new String[2];
		User user = userRepo.getUserByEmailAndPsw(email, psw);
		if (user == null) {
			msg[0] = "Invalid Credentials";
			msg[1] = "failure";
			return msg;
		} else if (user.getAccountStatus().equals("Locked")) {
			msg[0] = "Your Account is Locked";
			msg[1] = "failure";
			return msg;
		} else {
			msg[0] = "Welcome To Ashok IT.....";
			msg[1] = "success";
			return msg;
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

	@Override
	public List<User> getAllUsers() {

		return userRepo.findAll();
	}

	@Override
	public boolean deleteUser(Integer id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	private boolean isTempPswValid(String tempPsw) {
		return false;

	}

}
