package bitcamp.personalapp.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener{
	
	public void service(BreadcrumbPrompt prompt) {
		String name = prompt.inputString("이름은 ? ");
		System.out.printf("%s아~ 안녕!!\n", name);
	}

}
