package io.kenxue.devops.coreclient.exception.code;

public interface ErrorCode {
    Integer getCode();

    String getDesc();

    ErrorCode setMsg(String msg);
}
