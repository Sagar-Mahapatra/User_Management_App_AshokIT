package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable> {
	@Query("From User WHERE email=:em")
	public User getUserByEmail(String em);

	@Query("FROM User WHERE email=:email and password=:psw")
	public User getUserByEmailAndPsw(String email, String psw);

	@Query("select count(phNo) from User where phNo=:phoneNo")
	public Integer getPhNoCount(String phoneNo);

}
