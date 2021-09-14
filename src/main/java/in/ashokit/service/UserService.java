package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;

public interface UserService {

	// public String loginCheck(LoginForm loginForm);
	// public boolean saveUser(UserForm userForm);
	// public boolean emailUnique(String email);
	// public boolean unlockAccount(UnLockAccForm unLockAccForm);
	// public Map<Integer,String> getContries();
	// public Map<Integer,String> getStates(Integer countryId);
	// public Map<Integer,String> getCities(Integer stateId);

	public String forgotPsw(String email);

	public boolean registerUser(User user);

	public boolean unlockAccount(String email, String tempPsw, String newPsw);

	public String[] loginUser(String email, String psw);

	public List<Country> getCountriesList();

	public List<State> getStatesListByCountryId(Integer countryId);

	public List<City> getCitiesByStateId(Integer stateId);

	public List<User> getAllUsers();

	public boolean deleteUser(Integer id);

	public User getUserById(Integer id);

}
