package com.dale.log;

final class LogConfig implements ILogConfig{

  private int methodCount = 2;
  private boolean showThreadInfo = false;
  private int methodOffset = 1;
  private ILogAdapter logAdapter;
  private boolean debug = false;

  @Override
  public LogConfig hideThreadInfo() {
    showThreadInfo = false;
    return this;
  }

  @Override
  public LogConfig methodCount(int methodCount) {
    if (methodCount < 0) {
      methodCount = 0;
    }
    this.methodCount = methodCount;
    return this;
  }

  @Override
  public LogConfig setDebug(boolean debug) {
    this.debug = debug;
    return this;
  }

  @Override
  public LogConfig methodOffset(int offset) {
    this.methodOffset = offset;
    return this;
  }

  public LogConfig logAdapter(ILogAdapter logAdapter) {
    this.logAdapter = logAdapter;
    return this;
  }

  public int getMethodCount() {
    return methodCount;
  }

  public boolean isShowThreadInfo() {
    return showThreadInfo;
  }

  public boolean isDebug() {
    return debug;
  }

  public int getMethodOffset() {
    return methodOffset;
  }

  public ILogAdapter getLogAdapter() {
    if (logAdapter == null) {
      logAdapter = new LogAdapter();
    }
    return logAdapter;
  }

}
