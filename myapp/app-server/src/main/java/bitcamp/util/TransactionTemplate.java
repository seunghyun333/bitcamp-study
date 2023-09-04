package bitcamp.util;

import org.springframework.transaction.PlatformTransactionManager;

public class TransactionTemplate {
  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  public Object execute(트랜젝션으로 처리할 작업을 작성한 객체) throws Exception {

  }
}
