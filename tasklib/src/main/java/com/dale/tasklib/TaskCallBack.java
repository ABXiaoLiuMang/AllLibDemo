package com.dale.tasklib;

public interface TaskCallBack<Result> {

    Result getResult(Object... params);

    void onSuccess(Result result);

}
