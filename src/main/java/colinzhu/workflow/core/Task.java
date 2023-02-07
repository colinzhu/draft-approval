package colinzhu.workflow.core;

import lombok.Data;

import java.util.function.BiFunction;

@Data
public class Task<T> {
    private String name;
    private String preStatus;
    private boolean auto;
    private String handlerClassName;
    private BiFunction<T, Object, String> handler;
    private boolean persistStatus;
}
