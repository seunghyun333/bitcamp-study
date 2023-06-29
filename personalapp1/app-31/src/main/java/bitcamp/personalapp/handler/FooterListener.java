package bitcamp.personalapp.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class FooterListener implements ActionListener {
	public void service(BreadcrumbPrompt prompt) {
		System.out.println("Copyright \n00a9 by 네클7기 -----------------");
	}

}
