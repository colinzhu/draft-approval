package colinzhu.draft.example;

import colinzhu.draft.core.model.Draft;
import colinzhu.draft.core.service.DraftService;
import colinzhu.draft.core.service.DraftApprovalService;
import colinzhu.draft.example.model.Contact;
import colinzhu.draft.example.service.ContactService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {
  public static void main(String[] args) {
    Contact master = new Contact(2222L, "Colin", "Tianhe");
    Contact temp = new Contact(null, "Jim", "UK");
    log.info("Master contact: " + master);
    log.info("Draft contact: " + temp);

    Draft<Contact> draft = new Draft<>(master, temp);
    DraftApprovalService<Draft> draftApprovalService = new DraftService();
    ContactService contactService = new ContactService();

    draft = draftApprovalService.saveNew(draft);
    draft = draftApprovalService.firstApprove(draft);
    draft = draftApprovalService.secondApprove(draft);

    contactService.save(draft.getDraft());
  }
}
