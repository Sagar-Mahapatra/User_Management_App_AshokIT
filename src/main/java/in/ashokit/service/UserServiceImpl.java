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
import in.ashokit.constants.AppContstants;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.props.AppProperties;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserRepository;
import in.ashokit.utils.PasswordGenerator;

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
	@Autowired
	private AppProperties appProps;

	public boolean isTempPwdValid(String email, String tempPwd) {

		User user = userRepo.getUserByEmail(email);

		return (user != null && user.getPassword().equals(tempPwd)) ? true : false;

	}

	private String generateTempPsw() {

		return PasswordGenerator.generatePassword(6);

	}

	@Override
	public String loginCheck(LoginForm loginForm) {

		Map<String, String> messages = appProps.getMessages();
		User user = userRepo.getUserByEmailAndPsw(loginForm.getEmail(), loginForm.getPassword());

		if (user == null) {
			return messages.get(AppContstants.INVALID_CREDENTIALS);
		} else if (user.getAccountStatus().equals(AppContstants.ACC_LOCKED)) {
			return messages.get(AppContstants.ACC_LOCKED_MESSAGE);
		} else {
			return messages.get(AppContstants.LOGIN_SUCCESS);
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

		return (user == null) ? true : false;

	}

	@Override
	public boolean saveUser(UserForm userForm) {
		User user = new User();
		user.setAccountStatus(AppContstants.ACC_LOCKED);
		user.setPassword(generateTempPsw());
		BeanUtils.copyProperties(userForm, user);
		User savedUser = userRepo.save(user);
		return savedUser != null && savedUser.getUserId() != null;
	}

	@Override
	public boolean unlockAccount(UnlockAccForm unlockAccForm) {

		User user = userRepo.getUserByEmailAndPsw(unlockAccForm.getEmail(), unlockAccForm.getTempPsw());
		if (user != null) {
			user.setPassword(unlockAccForm.getNewPsw());
			user.setAccountStatus(AppContstants.ACC_UNLOCKED);
			userRepo.save(user);
			return true;
		}

		return false;

	}

	@Override
	public String forgotPwd(String emailId) {
		Map<String, String> messages = appProps.getMessages();
		User user = userRepo.getUserByEmail(emailId);
		if (user == null) {
			return messages.get(AppContstants.FORGET_PSW_FAIL);
		}
		// logic to send email to user email-id
		return messages.get(AppContstants.FORGET_PSW_SUCCESS);
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);

		return (user.isPresent()) ? user.get() : null;

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

	@Override
	public boolean isphNoUnique(String phNo) {
		Integer phNoCount = userRepo.getPhNoCount(phNo);
		return (phNoCount == 0) ? true : false;

	}

}
