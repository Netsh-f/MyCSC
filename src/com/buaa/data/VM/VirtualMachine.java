package com.buaa.data.VM;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class VirtualMachine implements Serializable {
    private ArrayList<String> cmdList = new ArrayList<>();
    private String type;

    public void printCmdList() {
        for (String s : cmdList) {
            System.out.println(s);
        }
    }

    public boolean isCmdListEmpty() {
        return cmdList.isEmpty();
    }

    public void clearCmd() {
        cmdList.clear();
    }

    public String getType() {
        return type;
    }

    public void addCmd(String s) {
        cmdList.add(s);
    }

    void setType(String type) {
        this.type = type;
    }
}
