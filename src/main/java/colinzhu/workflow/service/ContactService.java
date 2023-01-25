package colinzhu.workflow.service;

import colinzhu.workflow.core.model.Audit;
import colinzhu.workflow.model.Contact;
import colinzhu.workflow.core.service.AuditService;
import lombok.extern.slf4j.Slf4j;

import java.util.random.RandomGenerator;

@Slf4j
public class ContactService {
    AuditService auditService = new AuditService();
    public Contact save(Contact contact) {
        long eventTime = System.currentTimeMillis();
        Audit<Contact> audit = new Audit<>();

        if (contact.getId() == null) {
            contact.setId(RandomGenerator.getDefault().nextLong(1000)); // simulate DB action
            audit.setEvent(Audit.Event.CREATE);
            log.info("Master contact created: " + contact);
        } else {
            audit.setEvent(Audit.Event.UPDATE);
            log.info("Master contact updated: " + contact);
        }

        audit.setEventTime(eventTime);
        audit.setEventBy("system");
        audit.setTarget(contact);

        auditService.save(audit);
        return contact;
    }

}
