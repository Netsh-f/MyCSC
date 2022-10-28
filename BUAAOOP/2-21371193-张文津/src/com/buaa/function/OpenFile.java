package com.buaa.function;

import com.buaa.utils.FileHelper;

import java.util.ArrayList;

public class OpenFile extends Function {
    private static OpenFile openFile = new OpenFile();

    private OpenFile() {
    }

    public static final OpenFile getInstance() {
        return openFile;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        String sourcePath = null;

        boolean redirectFlag = false;
        String redirectPath = null;
        boolean redirectPathMissingFlag = false;
        int redirectPos = 0;
        for (int i = 0; i < parameterList.size(); i++) {
            if (parameterList.get(i).matches("^<$")) {
                redirectFlag = true;
                redirectPos = i;
                break;
            }
        }

        if (redirectFlag) {
            if (redirectPos + 1 >= parameterList.size()) {
                redirectPathMissingFlag = true;
            } else {
                redirectPath = parameterList.get(redirectPos);
            }
            if (redirectPathMissingFlag) {
                parameterList.remove(redirectPos);
            } else {
                parameterList.remove(redirectPos);
                parameterList.remove(redirectPos);
            }
        }

        if (!redirectFlag && parameterList.size() == 0) {
            System.out.println("please input the path to open the file");
        } else if (redirectPathMissingFlag) {
            System.out.println("please input the path to redirect the file");
        } else if (!((parameterList.size() == 0 && redirectFlag) || parameterList.size() == 1)) {
            System.out.println("arguments illegal");
        } else {
            try {
                if (parameterList.size() == 0) {
                    FileHelper.printFile(redirectPath);
                } else {
                    FileHelper.printFile(sourcePath);
                }
            } catch (Exception e) {
                System.out.println("file open failed");
            }
        }
    }
}
