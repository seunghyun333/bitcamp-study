package bitcamp.util;

import org.springframework.transaction.TransactionStatus;

public interface TransactionCallback<T> {
  T doInTransaction(TransactionStatus stuats);

}
