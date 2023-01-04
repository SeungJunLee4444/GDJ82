package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionForward {
	private String view;
	private boolean isRedirect;
}
