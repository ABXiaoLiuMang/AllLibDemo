package com.dale.log;

public interface ILogConfig {

  ILogConfig hideThreadInfo();

  ILogConfig methodCount(int methodCount);

  ILogConfig setDebug(boolean debug);

  ILogConfig methodOffset(int offset);

}
