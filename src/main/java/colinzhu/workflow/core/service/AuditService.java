package colinzhu.workflow.core.service;

import colinzhu.workflow.core.model.Audit;
import lombok.extern.slf4j.Slf4j;

import java.util.random.RandomGenerator;

@Slf4j
public class AuditService {
    public Audit save(Audit audit) {
        //persist audit
        audit.setId(RandomGenerator.getDefault().nextLong(1000));
        log.info("Audit saved: " + audit);
        return audit;
    }
}
