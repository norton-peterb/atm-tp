package org.norton.peterb.atm.model;

public abstract class BaseResponse {
    private String message = "OK";
    private int status = 0;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
