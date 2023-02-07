package colinzhu.workflow.core;

import java.util.function.BiFunction;

public interface TaskBak<T,U> {
    String getPreStatus();
    boolean isAuto();
    BiFunction<T, U, String> getHandler();
    boolean isPersistStatus();

    static enum Type {
        FIRST_APPROVE, SECOND_APPROVE
    }
}

// rule: if pre-condition = true, then perform task and if post-condition, else break
//
// IF         TASK               POST-TASK-IF        TASK
// BLOCKED -> CLEAR EXCEPTION -> READY-TO-RELEASE -> RELEASE,UPDATE_TO_RELEASED -> RELEASED


// IF (CONDITION)                   TASK AND UPDATE STATUS
// ST=BLOCKED & HAS_AUTO_TASK    -> RUN TASK & UPDATE_STATUS
// ST=BLOCKED &  NO_AUTO_TASK    -> NA
//                                  CREATE & UPDATE_STATUS TO SAVED
// ST=BLOCKED &  NO_AUTO_TASK    -> HANDLE_EXCEPTION & UPDATE_STATUS

// RULES OF TASKS
// IF...............     THEN....................................
// PRE_STATUS   IS_AUTO  FUNCTION    FUNCTION_RETURN_POST_STATUS    PERSIST STATUS
// NONE         AUTO     SAVE        ()->SAVED                      TRUE
// SAVED        AUTO     CHECK       FUNCTION                       FALSE
// BLOCKED      MANU     CLEAR-EXP   FUNCTION                       TRUE
// BLOCKED      MANU     STOP        ()->STOPPED                    TRUE
// BLOCKED      MANU     CANCEL      ()->CANCELLED                  TRUE
/*
name	preStatus	isAuto	handleClassName	isPersistStatus
SAVE	NONE	TRUE	workflow.PaymentSaveHandler	TRUE
CHECK	SAVED	TRUE	workflow.PaymentCheckHandler	FALSE
CLEAR_EXP	BLOCKED	FALSE	workflow.PaymentClearExpHandler	TRUE
STOP	BLOCKED	FALSE	workflow.PaymentStopHandler	TRUE
CANCEL	BLOCKED	FALSE	workflow.PaymentCancelHandler	TRUE
*/
// [{"name":"SAVE","preStatus":"NONE","isAuto":"TRUE","handleClassName":"workflow.PaymentSaveHandler","isPersistStatus":"TRUE"},{"name":"CHECK","preStatus":"SAVED","isAuto":"TRUE","handleClassName":"workflow.PaymentCheckHandler","isPersistStatus":"FALSE"},{"name":"CLEAR_EXP","preStatus":"BLOCKED","isAuto":"FALSE","handleClassName":"workflow.PaymentClearExpHandler","isPersistStatus":"TRUE"},{"name":"STOP","preStatus":"BLOCKED","isAuto":"FALSE","handleClassName":"workflow.PaymentStopHandler","isPersistStatus":"TRUE"},{"name":"CANCEL","preStatus":"BLOCKED","isAuto":"FALSE","handleClassName":"workflow.PaymentCancelHandler","isPersistStatus":"TRUE"}]


// INPUT: PAYMENT (STATUS), TASK (AUTO-DETECT / PROVIDED)
