package in.ashokit.service;

import java.util.List;

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
	public List<Country> getCountries() {

		return countryRepo.findAll();
	}

	@Override
	public List<State> getStates(Integer countryId) {

		return stateRepo.findAll();
	}

	@Override
	public List<City> getCities(Integer stateId) {

		return cityRepo.findAll();
	}

	@Override
	public boolean unlockAccount(String email, String tempPsw, String newPsw) {

		User user = userRepo.getUserByEmail(email);
		if (user.getPassword().equalsIgnoreCase(tempPsw)) {
			user.setPassword(newPsw);
			user.setAccountStatus("Unlocked");
			userRepo.save(user);
			return true;
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

}
