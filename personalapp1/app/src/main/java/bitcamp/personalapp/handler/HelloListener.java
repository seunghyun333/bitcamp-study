package bitcamp.personalapp.handler;

import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener{
   
	
	public void service(BreadcrumbPrompt prompt) {
		String name = prompt.inputString("♡이름을 적어주세요♡ ");
		System.out.printf("%s아~ 고마워♡\n", name);
	}
	
	

}


