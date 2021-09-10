package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;

public interface UserService {

	public boolean registerUser(User user);

	public boolean unlockAccount(String email, String tempPsw, String newPsw);

	public String loginUser(String email, String psw);

	public String forgotPsw(String email);

	public List<Country> getCountriesList();

	public List<State> getStatesListByCountryId(Integer countryId);

	public List<City> getCitiesByStateId(Integer stateId);

}
