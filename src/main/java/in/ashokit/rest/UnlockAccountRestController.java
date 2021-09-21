package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.service.UserService;

@RestController
public class UnlockAccountRestController {
	@Autowired
	private UserService service;

	@PostMapping("/unlockAccount")
	public String unlockAccount(@RequestBody UnlockAccForm unlockAccForm) {
		String msg = "";
		boolean unlockAccount = service.unlockAccount(unlockAccForm);
		if (unlockAccount) {
			msg = "Account Unlocked, please proceed with login";
		} else {
			msg = "Please Enter the Registered email id & correct Temporary Password provided in the registered mail id";
		}
		return msg;
	}
}
