package com.dale.log;

public final class LogUtils {

  private static final String DEFAULT_TAG = "Dream";

  private static ILogPrinter printer = new LogPrinter();

  private LogUtils() {
  }

  public static ILogConfig init() {
    return init(DEFAULT_TAG);
  }


  public static ILogConfig init(String tag) {
    printer = new LogPrinter();
    return printer.init(tag);
  }


  public static ILogPrinter t(String tag) {
    return printer.t(tag, printer.getSettings().getMethodCount());
  }

  public static ILogPrinter t(int methodCount) {
    return printer.t(null, methodCount);
  }

  public static ILogPrinter t(String tag, int methodCount) {
    return printer.t(tag, methodCount);
  }

  public static void log(int priority, String tag, String message, Throwable throwable) {
    printer.log(priority, tag, message, throwable);
  }

  public static void d(String message, Object... args) {
    printer.d(message, args);
  }

  public static void d(Object object) {
    printer.d(object);
  }

  public static void e(String message, Object... args) {
    printer.e(null, message, args);
  }

  public static void e(Throwable throwable, String message, Object... args) {
    printer.e(throwable, message, args);
  }

  public static void i(String message, Object... args) {
    printer.i(message, args);
  }

  public static void v(String message, Object... args) {
    printer.v(message, args);
  }

  public static void w(String message, Object... args) {
    printer.w(message, args);
  }

  public static void wtf(String message, Object... args) {
    printer.wtf(message, args);
  }

  public static void json(String json) {
    printer.json(json);
  }

  public static void xml(String xml) {
    printer.xml(xml);
  }

}
