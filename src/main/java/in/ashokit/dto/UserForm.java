package in.ashokit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

	private String firstName;

	private String lastName;

	private String email;

	private String phNo;

	private String dob;

	private String gender;

	private String country;

	private String state;

	private String city;

}
