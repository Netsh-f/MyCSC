package com.buaa.data.VM;

public class VMFactory {

    public static VirtualMachine createVM(String s){
        VMType type = VMType.valueOf(s);
        switch (type){
            case Windows:
                return new Windows();
            case Linux:
                return new Linux();
            case MacOS:
                return new MacOS();
            default:
                return null;
        }
    }

    public static boolean isTypeLegal(String s){
        try{
            VMType type = VMType.valueOf(s);
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
