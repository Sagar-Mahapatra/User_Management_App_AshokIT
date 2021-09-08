package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;

public interface UserService {

	public boolean registerUser(User user);

	public List<Country> getCountries();

	public List<State> getStates(Integer countryId);

	public List<City> getCities(Integer stateId);

	public boolean unlockAccount(String email, String tempPsw, String newPsw);

	public String loginUser(String email, String psw);

	public String forgotPsw(String email);

}
