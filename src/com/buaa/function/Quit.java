package com.buaa.function;

import java.util.ArrayList;

public class Quit extends Function {
    private static Quit quit = new Quit();

    private Quit() {
    }

    public static Quit getInstance(){
        return getInstance();
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        if(parameterList.isEmpty()){
            System.out.println("----- Good Bye! -----");
            System.exit(0);
        }else{
            System.out.println("arguments illegal");
        }
    }
}
