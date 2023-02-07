package colinzhu.workflow.core;

public class WorkflowExample {
    /*
name	preStatus	auto	handlerClassName	persistStatus
SAVE	NONE	TRUE	workflow.PaymentSaveHandler	TRUE
CHECK	SAVED	TRUE	workflow.PaymentCheckHandler	FALSE
CLEAR_EXP	BLOCKED	FALSE	workflow.PaymentClearExpHandler	TRUE
STOP	BLOCKED	FALSE	workflow.PaymentStopHandler	TRUE
CANCEL	BLOCKED	FALSE	workflow.PaymentCancelHandler	TRUE
*/
// [{"name":"SAVE","preStatus":"NONE","auto":"TRUE","handlerClassName":"workflow.PaymentSaveHandler","persistStatus":"TRUE"},{"name":"CHECK","preStatus":"SAVED","auto":"TRUE","handlerClassName":"workflow.PaymentCheckHandler","persistStatus":"FALSE"},{"name":"CLEAR_EXP","preStatus":"BLOCKED","auto":"FALSE","handlerClassName":"workflow.PaymentClearExpHandler","persistStatus":"TRUE"},{"name":"STOP","preStatus":"BLOCKED","auto":"FALSE","handlerClassName":"workflow.PaymentStopHandler","persistStatus":"TRUE"},{"name":"CANCEL","preStatus":"BLOCKED","auto":"FALSE","handlerClassName":"workflow.PaymentCancelHandler","persistStatus":"TRUE"}]

    private static final String RULE = "[{\"name\":\"SAVE\",\"preStatus\":\"NONE\",\"auto\":true,\"handlerClassName\":\"workflow.PaymentSaveHandler\",\"persistStatus\":true},{\"name\":\"CHECK\",\"preStatus\":\"SAVED\",\"auto\":true,\"handlerClassName\":\"workflow.PaymentCheckHandler\",\"persistStatus\":false},{\"name\":\"CLEAR_EXP\",\"preStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"workflow.PaymentClearExpHandler\",\"persistStatus\":true},{\"name\":\"STOP\",\"preStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"workflow.PaymentStopHandler\",\"persistStatus\":true},{\"name\":\"CANCEL\",\"preStatus\":\"BLOCKED\",\"auto\":false,\"handlerClassName\":\"workflow.PaymentCancelHandler\",\"persistStatus\":true}]";

    public static void main(String[] args) throws Exception {
        WorkflowEngine engine = new WorkflowEngine(RULE);
        Payment payment = new Payment();
        payment.setStatus("NONE");

        engine.process(payment, null);
    }
}
