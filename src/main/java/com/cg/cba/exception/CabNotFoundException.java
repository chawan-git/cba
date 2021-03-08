package com.cg.cba.exception;

public class CabNotFoundException extends RuntimeException {

    /**
     * Author D Sri Madhu Priya
     */
    private static final long serialVersionUID = -9090587718141879101L;

    public CabNotFoundException() {
        super();
    }

    public CabNotFoundException(String msg) {
        super(msg);
    }
}