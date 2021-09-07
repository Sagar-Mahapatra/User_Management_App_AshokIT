package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.City;

public interface CityRepository extends JpaRepository<City, Serializable> {

}
