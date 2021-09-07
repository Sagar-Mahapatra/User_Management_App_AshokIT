package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.State;

public interface StateRepository extends JpaRepository<State, Serializable> {

}
