package org.esnack24api.esnack24api.user.exception;

import lombok.Data;


@Data
public class UserTaskException extends RuntimeException {

    private int status;

    private String msg;

    public UserTaskException(final int status, String msg) {

        super(status + "_" + msg);

        this.status = status;
        this.msg = msg;
    }
}
