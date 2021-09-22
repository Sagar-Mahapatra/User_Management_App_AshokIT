package in.ashokit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.constants.AppContstants;
import in.ashokit.props.AppProperties;
import in.ashokit.service.UserService;

@RestController
public class UnlockAccountRestController {

	private UserService service;

	private AppProperties props;

	public UnlockAccountRestController(UserService service, AppProperties props) {
		this.service = service;
		this.props = props;
	}

	@PostMapping("/unlockAccount")
	public String unlockAccount(@RequestBody UnlockAccForm unlockAccForm) {

		boolean unlockAccount = service.unlockAccount(unlockAccForm);
		if (unlockAccount) {
			return props.getMessages().get(AppContstants.UNLOCK_SUCCESS);
		} else {
			return props.getMessages().get(AppContstants.UNLOCK_FAIL);
		}

	}
}
