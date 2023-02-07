package workflow;

import colinzhu.workflow.core.Payment;

import java.util.function.BiFunction;

public class PaymentCancelHandler implements BiFunction<Payment, Object, String> {
    @Override
    public String apply(Payment payment, Object o) {
        return null;
    }
}
