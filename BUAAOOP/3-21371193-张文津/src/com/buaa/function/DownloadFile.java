package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.main.UserOperation;
import com.buaa.utils.FileHelper;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
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
        for (int i = 0; i < parameterList.size(); i++) {//查看是否使用重定向
            String s = parameterList.get(i);
            if (s.matches("^>{1,2}$")) {
                redirectFlag = true;
                redirectPos = i;
                redirectType = s.matches(">>");
                break;
            }
        }

        if (redirectFlag) {//如果有重定向
            if (redirectPos + 1 >= parameterList.size()) {//是否有重定向路径
                System.out.println("please input the path to redirect the file");
                return;
            } else {
                redirectPath = parameterList.get(redirectPos + 1);//保存重定向路径
                destPath = parameterList.get(0);//暂定第一个参数是目标路径
                if (destPath.equals(redirectPath)) {//如果相同，报错
                    System.out.println("input file is output file");
                    return;
                }
            }
            parameterList.remove(redirectPos);
            parameterList.remove(redirectPos);//把重定向符号和后面的路径删除
        }

        try {
            if (!(parameterList.size() == 2 || (parameterList.size() == 1 && redirectFlag))) {
//                System.out.println("arguments illegal");
                FileHelper.redirectPrintln("arguments illegal", redirectPath, redirectType);
            } else {
                if (parameterList.size() == 1) {//如果只剩一个参数
                    fileId = parameterList.get(0);
                } else {//如果有两个参数
                    destPath = parameterList.get(0);
                    fileId = parameterList.get(1);
                }
                if (UserOperation.isNoUser()) {
//                    System.out.println("not logged in");
                    FileHelper.redirectPrintln("not logged in", redirectPath, redirectType);
                } else if (UserOperation.isNoCourse()) {
//                    System.out.println("no course selected");
                    FileHelper.redirectPrintln("no course selected", redirectPath, redirectType);
                } else if (!(currentCourse.isWareIdExist(fileId) || currentCourse.isTaskIdExist(fileId))) {
//                    System.out.println("file not found");
                    FileHelper.redirectPrintln("file not found", redirectPath, redirectType);
                } else {
                    try {
                        String sourcePath;
                        if (currentCourse.isWareIdExist(fileId)) {
                            sourcePath = currentCourse.getWare(fileId).getFilePath();
                        } else {
                            sourcePath = currentCourse.getTask(fileId).getFilePath();
                        }//获取源文件路径
                        if (parameterList.size() == 2) {//如果指定了目标路径
                            FileHelper.copyFile(sourcePath, destPath);
                        }

                        if (redirectFlag) {//如果使用了重定向
                            if (redirectType) {//如果是>>加写
                                FileHelper.extraWriteFile(sourcePath, redirectPath);
                            } else {//不是加写
                                FileHelper.copyFile(sourcePath, redirectPath);
                            }
                        } else {//如果没用重定向（肯定有且只有两个参数，已经复制过了），那么就打印
                            FileHelper.printFile(sourcePath);
                        }
                    } catch (Exception e) {
//                        System.out.println("file operation failed");
                        FileHelper.redirectPrintln("file operation failed", redirectPath, redirectType);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("my redirect operation failed");
        }
    }
}
