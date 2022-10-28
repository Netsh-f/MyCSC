package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.io.File;
import java.util.ArrayList;

public class DownloadFile extends Function {
    private static final DownloadFile downloadFile = new DownloadFile();

    private DownloadFile() {
    }

    public static DownloadFile getInstance() {
        return downloadFile;
    }

    @Override
    public void run(ArrayList<String> parameterList) {
        String destPath = null;
        String fileId;
        String redirectPath = null;
        Course currentCourse = UserOperation.getCurrentCourse();

        boolean redirectFlag = false;
        boolean redirectType = false;// > is false   >> is true
        int redirectPos = 0;
        for (int i = 0; i < parameterList.size(); i++) {
            String s = parameterList.get(i);
            if (s.matches("^>{1,2}$")) {
                redirectFlag = true;
                redirectPos = i;
                redirectType = s.matches(">>");
                break;
            }
        }

        if (redirectFlag) {
            if (redirectPos + 1 >= parameterList.size()) {
                System.out.println("please input the path to redirect the file");
                return;
            } else {
                redirectPath = parameterList.get(redirectPos);
                destPath = parameterList.get(0);
                if (destPath.equals(redirectPath)) {
                    System.out.println("input file is output file");
                    return;
                }
            }
            parameterList.remove(redirectPos);
            parameterList.remove(redirectPos);//把重定向符号和后面的路径删除
        }

        if (!(parameterList.size() == 2 || (parameterList.size() == 1 && redirectFlag))) {
            System.out.println("arguments illegal");
        } else {
            if (parameterList.size() == 1) {
                fileId = parameterList.get(0);
            } else {
                destPath = parameterList.get(0);
                fileId = parameterList.get(1);
            }
            if (UserOperation.isNoUser()) {
                System.out.println("not logged in");
            } else if (UserOperation.isNoCourse()) {
                System.out.println("no course selected");
            } else if (!(currentCourse.isWareIdExist(fileId) || currentCourse.isTaskIdExist(fileId))) {
                System.out.println("file not found");
            } else {
                try {
                    String sourcePath;
                    if (currentCourse.isWareIdExist(fileId)) {
                        sourcePath = currentCourse.getWare(fileId).getFilePath();
                    } else {
                        sourcePath = currentCourse.getTask(fileId).getFilePath();
                    }
                    if (parameterList.size() == 2) {
                        FileHelper.copyFile(sourcePath, destPath);
                    }

                    if (redirectFlag) {
                        if (redirectType) {//如果是>>加写
                            FileHelper.extraWriteFile(sourcePath, redirectPath);
                        } else {
                            FileHelper.copyFile(sourcePath, redirectPath);
                        }
                    } else {//如果没用重定向（肯定有且只有两个参数），那么就打印
                        FileHelper.printFile(sourcePath);
                    }
                } catch (Exception e) {
                    System.out.println("file operation failed");
                }
            }
        }
    }
}
