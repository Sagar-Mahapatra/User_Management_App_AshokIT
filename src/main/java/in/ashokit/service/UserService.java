package in.ashokit.service;

import java.util.List;
import java.util.Map;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.entity.User;

public interface UserService {

	public String loginCheck(LoginForm loginForm);

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public boolean emailUnique(String email);

	public boolean saveUser(UserForm userForm);

	public boolean unlockAccount(UnlockAccForm unlockAccForm);

	public String forgotPwd(String emailId);

	public User getUserById(Integer userId);

	public boolean deleteUser(Integer userId);

	public List<User> getAllUsers();

	/*
	 * public String forgotPsw(String email);
	 * 
	 * public boolean registerUser(User user);
	 * 
	 * public boolean unlockAccount(String email, String tempPsw, String newPsw);
	 * 
	 * public String[] loginUser(String email, String psw);
	 * 
	 * public List<Country> getCountriesList();
	 * 
	 * public List<State> getStatesListByCountryId(Integer countryId);
	 * 
	 * public List<City> getCitiesByStateId(Integer stateId);
	 * 
	 * public List<User> getAllUsers();
	 * 
	 * public boolean deleteUser(Integer id);
	 * 
	 * public User getUserById(Integer id);
	 */
}
