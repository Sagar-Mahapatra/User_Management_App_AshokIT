package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.constants.AppContstants;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.exception.EmailBodyGenerationFailException;
import in.ashokit.exception.UserNotFoundException;
import in.ashokit.props.AppProperties;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserRepository;
import in.ashokit.utils.EmailUtils;
import in.ashokit.utils.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	private CountryRepository countryRepo;

	private StateRepository stateRepo;

	private CityRepository cityRepo;

	private AppProperties appProps;

	private EmailUtils emailUtils;

	public UserServiceImpl(UserRepository userRepo, CountryRepository countryRepo, StateRepository stateRepo,
			CityRepository cityRepo, AppProperties appProps, EmailUtils emailUtils) {

		this.userRepo = userRepo;
		this.countryRepo = countryRepo;
		this.stateRepo = stateRepo;
		this.cityRepo = cityRepo;
		this.appProps = appProps;
		this.emailUtils = emailUtils;
	}

	private boolean isTempPwdValid(String email, String tempPwd) {

		return userRepo.findAll().stream()
				.filter(user -> user.getEmail().equals(email) && user.getPassword().equals(tempPwd)).findAny()
				.isPresent();

	}

	private String generateTempPsw() {

		return PasswordGenerator.generatePassword(6);

	}

	private String generateMailBodyToUnlockAccount(User user) throws Exception {
		String body = "";
		FileReader reader = new FileReader(AppContstants.UNLOCK_ACC_TEMPLATE);
		BufferedReader bufferedReader = new BufferedReader(reader);

		Stream<String> lines = bufferedReader.lines();
		body = lines
				.map(line -> line.replace("{FNAME}", user.getFirstName()).replace("{LNAME}", user.getLastName())
						.replace("{TEMP-PWD}", user.getPassword()).replace("{EMAIL}", user.getEmail()))
				.map(Object::toString).collect(Collectors.joining(body));

		bufferedReader.close();
		return body;
	}

	private String forgetPasswordMailBody(User user) {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(AppContstants.FORGOT_PSW_TEMPLATE))) {

			Stream<String> lines = bufferedReader.lines();
			List<String> list = lines.map(line -> line.replace("{FNAME}", user.getFirstName())
					.replace("{LNAME}", user.getLastName()).replace("{PWD}", user.getPassword()))
					.collect(Collectors.toList());
			return String.join("", list);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EmailBodyGenerationFailException(
					appProps.getMessages().get(AppContstants.EMAIL_BODY_EXCEPTION_MSG));
		}

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

		return list.stream().collect(Collectors.toMap(Country::getCountryId, Country::getCountryName));

	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<State> list = stateRepo.getStatesByCountryId(countryId);

		return list.stream().collect(Collectors.toMap(State::getStateId, State::getStateName));
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

		return user == null;

	}

	@Override
	public boolean saveUser(UserForm userForm) {

		User user = new User();
		user.setAccountStatus(AppContstants.ACC_LOCKED);
		user.setPassword(generateTempPsw());
		BeanUtils.copyProperties(userForm, user);
		String subject = AppContstants.UNLOCK_MAIL_SUBJECT;
		String mailBody = "";
		boolean sendEmail = false;
		try {
			mailBody = generateMailBodyToUnlockAccount(user);
		} catch (Exception e) {
			throw new EmailBodyGenerationFailException(
					appProps.getMessages().get(AppContstants.EMAIL_BODY_EXCEPTION_MSG));
		}
		User savedUser = userRepo.save(user);
		sendEmail = emailUtils.sendEmail(savedUser.getEmail(), subject, mailBody);
		return savedUser != null && sendEmail;
	}

	@Override
	public boolean unlockAccount(UnlockAccForm unlockAccForm) {

		boolean isValid = isTempPwdValid(unlockAccForm.getEmail(), unlockAccForm.getTempPsw());
		if (isValid) {
			User user = userRepo.getUserByEmailAndPsw(unlockAccForm.getEmail(), unlockAccForm.getTempPsw());
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
		String subject = AppContstants.FORGOT_PSW_MAIL_SUBJECT;

		String mailBody = forgetPasswordMailBody(user);

		emailUtils.sendEmail(emailId, subject, mailBody);

		return messages.get(AppContstants.FORGET_PSW_SUCCESS);
	}

	@Override
	public User getUserById(Integer userId) {
		Map<String, String> messages = appProps.getMessages();

		return userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(messages.get(AppContstants.USER_NOT_FOUND)));

	}

	@Override
	public boolean deleteUser(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			userRepo.deleteById(userId);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<User> getAllUsers() {

		return userRepo.findAll().stream().sorted(Comparator.comparing(User::getFirstName))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isphNoUnique(String phNo) {
		Integer phNoCount = userRepo.getPhNoCount(phNo);
		return phNoCount == 0;

	}

}
