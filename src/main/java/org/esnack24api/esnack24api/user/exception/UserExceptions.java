package org.esnack24api.esnack24api.user.exception;

public enum UserExceptions {

    BAD_AUTH(400, "ID/PW incorrect"),
    TOKEN_NOT_ENOUGH(401, "More Tokens requeired"),
    ACCESSTOKEN_TOO_SHORT(402, "Access Token Too short"),
    REQUIRE_SIGN_IN(403, "Require sign in");

    private UserTaskException exception;

    UserExceptions(int status, String msg) {

        this.exception = new UserTaskException(status, msg);
    }

    public UserTaskException get() {
        return exception;
    }
}
