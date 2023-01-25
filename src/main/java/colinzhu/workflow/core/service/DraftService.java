package colinzhu.workflow.core.service;

import colinzhu.workflow.core.model.Audit;
import colinzhu.workflow.core.model.Draft;
import lombok.extern.slf4j.Slf4j;

import java.util.random.RandomGenerator;

@Slf4j
public class DraftService implements WorkflowService<Draft> {

  AuditService auditService = new AuditService();

  @Override
  public Draft saveNew(Draft draft) {
    long eventTime = System.currentTimeMillis();
    draft.setStatus(Draft.Status.DRAFTED);
    draft.setCreateTime(eventTime);
    draft.setId(RandomGenerator.getDefault().nextLong(9999)); // simulate DB auto create ID

    Audit<Draft> audit = new Audit<>();
    audit.setEvent(Audit.Event.DRAFT);
    audit.setEventTime(eventTime);
    audit.setEventBy("system");
    audit.setTarget(draft);

    log.info("newSaved: " + draft);
    auditService.save(audit);
    return draft;
  }

  public Draft firstApprove(Draft draft) {
    long eventTime = System.currentTimeMillis();
    draft.setStatus(Draft.Status.FIRST_APPROVED);
    draft.setUpdateTime(eventTime);

    Audit<Draft> audit = new Audit<>();
    audit.setEvent(Audit.Event.FIRST_APPROVE);
    audit.setEventTime(eventTime);
    audit.setEventBy("system");
    audit.setTarget(draft);

    log.info("firstApproved: " + draft);
    auditService.save(audit);
    // update draft state
    // persist into DB
    return draft;
  }

  public Draft secondApprove(Draft draft) {
    long eventTime = System.currentTimeMillis();
    draft.setStatus(Draft.Status.SECOND_APPROVED);
    draft.setUpdateTime(eventTime);

    Audit<Draft> audit = new Audit<>();
    audit.setEvent(Audit.Event.SECOND_APPROVE);
    audit.setEventTime(eventTime);
    audit.setEventBy("system");
    audit.setTarget(draft);

    log.info("secondApproved: " + draft);
    auditService.save(audit);
    return draft;
  }

}
