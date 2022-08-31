package com.trokatroka;

public class Main {

    public static void main(String[] args) {
	    BruteForce<RequestExecutor> bruteForce = new BruteForce<>(new RequestExecutor(), "0123456789".toCharArray(), 6);
        bruteForce.start();

    }
}
