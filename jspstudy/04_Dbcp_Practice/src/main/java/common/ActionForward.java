package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionForward {
	private String view;         // getView(),    setView()
	private boolean isRedirect;  // isRedirect(), setRedirect()
}
