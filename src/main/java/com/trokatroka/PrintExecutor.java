package com.trokatroka;

public class PrintExecutor implements Executor {

    @Override
    public boolean run(String value) {

        System.out.println(value);
        return value.equals("123456");
    }
}
