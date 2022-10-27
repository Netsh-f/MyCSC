package com.buaa.function;

import com.buaa.data.Course;
import com.buaa.main.UserOperation;

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
        boolean redirectFlag = false;
        int redirectPos = 0;
        for (int i = 0; i < parameterList.size(); i++) {
            String s = parameterList.get(i);
            if (s.matches("^>{1,2}$")) {
                redirectFlag = true;
                redirectPos = i;
                break;
            }
        }
        if (redirectFlag) {//使用了重定向
            if (!(redirectPos < parameterList.size() - 1)) {//如果>是最后一个参数
                System.out.println("please input the path to redirect the file");
            } else if (parameterList.get(0).equals(parameterList.get(redirectPos))) {
                //如果第一个参数和重定向后面的参数相同，就输出两者路径相同，但是现在没有判断第一个参数是不是可选参数1
                System.out.println("input file is output file");
            } else if (!(parameterList.size() == 3 || parameterList.size() == 4)) {
                System.out.println("arguments illegal");
            } else if(UserOperation.isNoUser()){
                System.out.println("not logged in");
            }else if(UserOperation.isNoCourse()){
                System.out.println("no course selected");
            }else{
                String saveLocation;
                String id;
                boolean redirectType;//true is >> 追加写入  false is > 写入
                String redirectLocation;
                Course currentCourse = UserOperation.getCurrentCourse();
                if(parameterList.size()==3){
                    id = parameterList.get(0);
                    if(!(currentCourse.isWareIdExist(id)||currentCourse.isTaskIdExist(id))){
                        System.out.println("file not found");
                    }else{
                        File inFile;
                        if(currentCourse.isWareIdExist(id)){
                            inFile = new File(currentCourse.getWareTreeMap().get(id).getLocation());
                        }else{
                            inFile = new File(currentCourse.getTaskTreeMap().get(id).getLocation());
                        }
                    }
                }
            }
        } else {

        }
    }
}
