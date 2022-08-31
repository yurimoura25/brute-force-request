package com.trokatroka;

import java.util.Arrays;
import java.util.Date;

public class BruteForce<T extends Executor> {

    private final T executor;
    private final char[] charset;
    private final Integer size;

    public BruteForce(T executor, char[] charset, Integer size) {
        this.executor = executor;
        this.charset = charset;
        this.size = size;
    }

    public void start() {
        char[] currentValue = new char[size];
        for(int i = 0; i < size; i ++){
            currentValue[i] = charset[0];
        }

        Integer quantity = 0;
        Date start = new Date();
        Date end;
        while(true) {

            if(executor.run(getStringFromCharArray(currentValue))) {
                end = new Date();
                break;
            }
            int index = currentValue.length - 1;
            while (index >= 0)
            {
                quantity++;
                if (currentValue[index] == charset[charset.length - 1])
                {
                    if (index == 0)
                    {
                        currentValue = new char[currentValue.length];
                        Arrays.fill(currentValue, charset[0]);
                        break;
                    }
                    else
                    {
                        currentValue[index] = charset[0];
                        index--;
                    }
                }
                else
                {
                    currentValue[index] = charset[Arrays.binarySearch(charset, currentValue[index]) + 1];
                    break;
                }
            }
        }

        System.out.println("Quantidade de tentativas: " + quantity);
        System.out.println("Tempo gasto:" + (end.getTime() - start.getTime()) + " milisegundos");
    }

    public String getStringFromCharArray(char[] array) {
        String string = "";
        for (char c : array) {
            string = string.concat(c + "");
        }

        return string;
    }
}
