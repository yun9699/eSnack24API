package org.esnack24api.esnack24api.common.exception;

public enum CommonExceptions {

    READ_ERROR(new TaskException("READ",404)),
    CREATE_ERROR(new TaskException("READ",405)),
    UPDATE_ERROR(new TaskException("READ",406)),
    DELETE_ERROR(new TaskException("READ",407)),
    LIST_ERROR(new TaskException("LIST",408));


    private TaskException exception;

    CommonExceptions(TaskException exception) {
        this.exception = exception;
    }

    public TaskException get() {
        return this.exception;
    }
}
