package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.controller.UserController;

@SpringBootApplication
public class UserManagementAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UserManagementAppApplication.class, args);
		//UserController userController = context.getBean(UserController.class);
//		User user = new User();
//		user.setFirstName("sagar");
//		user.setLastName("mahapatra");
//		user.setEmail("sagar@gmail.com");
//
//		String userRegistration = userController.UserRegistration(user);
//		System.out.println(userRegistration);
//		String msg = userController.unlockAccount("sagar@gmail.com", "dummy", "newpsaword");
//		System.out.println(msg);
//		String userLogin = userController.userLogin("sagar@gmail.com", "newpsaword");
//		System.out.println(userLogin);
	}

}
