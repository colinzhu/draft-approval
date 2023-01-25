package colinzhu.workflow.core.service;

public interface WorkflowService<T> {
  T saveNew(T t);
  T firstApprove(T t);
  T secondApprove(T t);

}
