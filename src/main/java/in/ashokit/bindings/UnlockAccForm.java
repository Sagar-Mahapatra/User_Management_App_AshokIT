package in.ashokit.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnlockAccForm {

	private String email;

	private String tempPsw;

	private String newPsw;

	private String confirmPsw;

}
