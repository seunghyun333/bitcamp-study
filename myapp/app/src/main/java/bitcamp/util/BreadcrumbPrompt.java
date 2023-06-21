package bitcamp.util;

public class BreadcrumbPrompt extends Prompt {

  private Stack breadcrumbs = new Stack();

  public void appendBreadcrumb(String title) {
    this.breadcrumbs.push(title);
  }

  public void removeBreadcrumb() {
    this.breadcrumbs.pop();

  }

  public String inputMenu() {
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인 / 회원 >
    for (int i = 0; i < this.breadcrumbs.size(); i++) {// 기존 타이틀이 있다면 새로 붙일 타이틀과 구분하기 위해 / 추가
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadcrumbs.get(i));
    }
    titleBuilder.append("> ");

    return this.inputString(titleBuilder.toString());

  }
}
