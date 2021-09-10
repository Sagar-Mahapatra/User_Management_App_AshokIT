package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.City;

public interface CityRepository extends JpaRepository<City, Serializable> {
	@Query("from City where stateId=:stateId")
	List<City> getCitiesByStateId(Integer stateId);
}
