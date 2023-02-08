package colinzhu.draft.core.service;

public interface DraftApprovalService<T> {
  T saveNew(T t);
  T firstApprove(T t);
  T secondApprove(T t);

}
