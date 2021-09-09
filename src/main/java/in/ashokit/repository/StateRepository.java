package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.State;

public interface StateRepository extends JpaRepository<State, Serializable> {

	@Query("from State where countryId=:countryId")
	List<State> getStatesByCountryId(Integer countryId);
}
