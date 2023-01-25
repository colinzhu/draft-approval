package colinzhu.workflow.model;

import colinzhu.workflow.core.model.ID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact implements ID {
  private Long id;
  private String name;
  private String address;
}
