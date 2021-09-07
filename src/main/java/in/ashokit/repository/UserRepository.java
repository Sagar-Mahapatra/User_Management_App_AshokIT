package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findUserByEmail(String email);

	@Query("FROM User U WHERE U.email = :email and U.password = :psw")
	public User getUserByEmailAndPsw(String email, String psw);

}
