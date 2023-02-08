package colinzhu.draft.core.model;

import lombok.Data;
import lombok.ToString;

@Data
public class Audit<T extends ID> implements ID{
    private Long id;
    @ToString.Exclude
    private T target;
    private Event event;
    private String eventBy;
    private long eventTime;

    @ToString.Include
    public String getEntityType() {
        return target.getClass().getName();
    }

    @ToString.Include
    public long getEntityId() {
        return target.getId();
    }

    public enum Event {
        CREATE, UPDATE, DRAFT, FIRST_APPROVE, SECOND_APPROVE, REJECT, CANCEL
    }
}
