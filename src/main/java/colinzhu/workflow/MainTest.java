package colinzhu.workflow;

import colinzhu.workflow.model.Contact;
import colinzhu.workflow.core.model.Draft;
import colinzhu.workflow.service.ContactService;
import colinzhu.workflow.core.service.DraftService;
import colinzhu.workflow.core.service.WorkflowService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {
  public static void main(String[] args) {
    Contact master = new Contact(2222L, "Colin", "Tianhe");
    Contact temp = new Contact(null, "Jim", "UK");
    log.info("Master contact: " + master);
    log.info("Draft contact: " + temp);

    Draft<Contact> draft = new Draft<>(master, temp);
    WorkflowService<Draft> workflowService = new DraftService();
    ContactService contactService = new ContactService();

    draft = workflowService.saveNew(draft);
    draft = workflowService.firstApprove(draft);
    draft = workflowService.secondApprove(draft);

    contactService.save(draft.getDraft());
  }
}
