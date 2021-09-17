package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.props.AppProperties;
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

	private Map<String, String> props;

	public UserServiceImpl() {

		props = AppProperties.getProperties();
		System.out.println(props);

	}

	private boolean isTempPwdValid(String email, String tempPwd) {

		User user = userRepo.getUserByEmail(email);

		if (user != null && user.getPassword().equals(tempPwd)) {

			return true;

		} else {

			return false;
		}

	}

	@Override
	public String loginCheck(LoginForm loginForm) {
		User user = userRepo.getUserByEmailAndPsw(loginForm.getEmail(), loginForm.getPassword());

		if (user == null) {
			return props.get("invalidCredentials");
		} else if (user.getAccountStatus().equals("Locked")) {
			return props.get("accountLocked");
		} else {
			return props.get("loginSuccess");
		}

	}

	@Override
	public Map<Integer, String> getCountries() {
		List<Country> list = countryRepo.findAll();
		Map<Integer, String> map = new HashMap<>();
		list.forEach(l -> {
			map.put(l.getCountryId(), l.getCountryName());
		});

		return map;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<State> list = stateRepo.getStatesByCountryId(countryId);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(l -> {
			map.put(l.getStateId(), l.getStateName());
		});
		return map;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<City> list = cityRepo.getCitiesByStateId(stateId);
		Map<Integer, String> map = new HashMap<>();
		list.forEach(l -> {
			map.put(l.getCityId(), l.getCityName());
		});
		return map;
	}

	@Override
	public boolean emailUnique(String email) {
		User user = userRepo.getUserByEmail(email);
		if (user == null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean saveUser(UserForm userForm) {
		User user = new User();
		user.setAccountStatus("Locked");
		user.setPassword("dummy");
		BeanUtils.copyProperties(userForm, user);
		User savedUser = userRepo.save(user);
		return savedUser != null && savedUser.getUserId() != null;
	}

	@Override
	public boolean unlockAccount(UnlockAccForm unlockAccForm) {
		boolean tempPwdValid = isTempPwdValid(unlockAccForm.getEmail(), unlockAccForm.getTempPsw());
		if (tempPwdValid) {
			User user = userRepo.getUserByEmailAndPsw(unlockAccForm.getEmail(), unlockAccForm.getTempPsw());
			user.setPassword(unlockAccForm.getNewPsw());
			user.setAccountStatus("Unlocked");
			userRepo.save(user);
			return true;
		}
		return false;

	}

	@Override
	public String forgotPwd(String emailId) {
		User user = userRepo.getUserByEmail(emailId);
		if (user == null) {
			return "We can't find a user with that e-mail address";
		}
		// logic to send email to user email-id
		return "we have sent password on your registered email-id";
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public boolean deleteUser(Integer userId) {
		User user = getUserById(userId);
		if (user != null) {
			userRepo.deleteById(userId);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<User> getAllUsers() {

		return userRepo.findAll();
	}

	/*
	 * @Override public boolean registerUser(User user) {
	 * user.setAccountStatus("Locked"); user.setPassword("dummy"); User savedUser =
	 * userRepo.save(user);
	 * 
	 * return savedUser != null && savedUser.getUserId() != null; }
	 * 
	 * @Override public List<Country> getCountriesList() {
	 * 
	 * return countryRepo.findAll(); }
	 * 
	 * @Override public List<State> getStatesListByCountryId(Integer countryId) {
	 * 
	 * return stateRepo.getStatesByCountryId(countryId); }
	 * 
	 * @Override public List<City> getCitiesByStateId(Integer stateId) {
	 * 
	 * return cityRepo.getCitiesByStateId(stateId); }
	 * 
	 * @Override public boolean unlockAccount(String email, String tempPsw, String
	 * newPsw) {
	 * 
	 * User user = userRepo.getUserByEmail(email);
	 * 
	 * if (user != null) { if (user.getPassword().equalsIgnoreCase(tempPsw)) {
	 * user.setPassword(newPsw); user.setAccountStatus("Unlocked");
	 * userRepo.save(user); return true; } }
	 * 
	 * return false; }
	 * 
	 * @Override public String[] loginUser(String email, String psw) { String[] msg
	 * = new String[2]; User user = userRepo.getUserByEmailAndPsw(email, psw); if
	 * (user == null) { msg[0] = "Invalid Credentials"; msg[1] = "failure"; return
	 * msg; } else if (user.getAccountStatus().equals("Locked")) { msg[0] =
	 * "Your Account is Locked"; msg[1] = "failure"; return msg; } else { msg[0] =
	 * "Welcome To Ashok IT....."; msg[1] = "success"; return msg; }
	 * 
	 * }
	 * 
	 * @Override public String forgotPsw(String email) { User user =
	 * userRepo.getUserByEmail(email); if (user == null) { return
	 * "We can't find a user with that e-mail address"; }
	 * 
	 * String password = user.getPassword();
	 * 
	 * // logic to send password to mail id
	 * 
	 * return "we have sent your password on registered email:- " + password;
	 * 
	 * }
	 * 
	 * @Override public List<User> getAllUsers() {
	 * 
	 * return userRepo.findAll(); }
	 * 
	 * @Override public boolean deleteUser(Integer id) { Optional<User> user =
	 * userRepo.findById(id); if (user.isPresent()) { userRepo.deleteById(id);
	 * return true; }
	 * 
	 * return false; }
	 * 
	 * @Override public User getUserById(Integer id) { Optional<User> user =
	 * userRepo.findById(id); if (user.isPresent()) { return user.get(); } return
	 * null; }
	 * 
	 * 
	 */
}
