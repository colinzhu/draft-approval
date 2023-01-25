package colinzhu.workflow.core.model;

import lombok.Data;
import lombok.ToString;

@Data
public class Draft<T extends ID> implements ID {
    private Long id;
    @ToString.Exclude
    private T master;
    private T draft;
    private Status status;
    private long createTime;
    private long updateTime;

    @ToString.Include
    public Long getMasterId() {
        return master != null ? master.getId() : null;
    }
    public Draft(T master, T draft) {
        this.master = master;
        this.draft = draft;
        if (master != null) {
            draft.setId(master.getId());
        }
    }

    public enum Status {
        DRAFTED, FIRST_APPROVED, SECOND_APPROVED, REJECTED, CANCELLED
    }
}
