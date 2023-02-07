package colinzhu.workflow.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.function.BiFunction;

public class WorkflowEngine {
    ObjectMapper objectMapper = new ObjectMapper();
    private final List<Task<Payment>> tasks;

    public WorkflowEngine(String ruleJson) throws JsonProcessingException {
        tasks = objectMapper.readValue(ruleJson, new TypeReference<>() {});
        System.out.println(tasks.size());
        for (Task<Payment> task : tasks) {
            try {
                ClassLoader loader = WorkflowEngine.class.getClassLoader();
                String className = task.getHandlerClassName();
                BiFunction<Payment, Object, String> handler = (BiFunction<Payment, Object, String>) Class.forName(className).newInstance();
                task.setHandler(handler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void process(Payment payment, Object params) {
        for (Task<Payment> task : tasks) {
            if (task.getPreStatus().equals(payment.getStatus()) && task.isAuto()) {
                task.getHandler().apply(payment, params);
                System.out.println(task.getHandler().getClass().getName() + " " + payment.getStatus());
                process(payment, params);
            } else {
                break;
            }
        }
    }
}
