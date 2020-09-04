package com.example.demo.exception;

public class MyException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public MyException(String arg0) {
    super(arg0);
  }

  public MyException(Throwable arg0) {
    super(arg0);
  }

  public MyException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

}
