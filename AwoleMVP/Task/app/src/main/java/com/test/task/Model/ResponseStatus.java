package com.test.task.Model;

public abstract class ResponseStatus {

    private
    String  status;

    private
    String msg="";

    private
    int code;

    private Flash flash;

    public abstract  void onResult(ResponseStatus status);

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

}
