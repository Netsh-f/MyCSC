package com.buaa.utils;

import java.io.*;

public class FileHelper {

    public static Object deserialize(String path) throws Exception {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object obj = objectInputStream.readObject();

        inputStream.close();
        objectInputStream.close();
        return obj;
    }

    public static void serialize(Object obj, String path) throws Exception {
        File file = new File(path);
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        outputStream = new FileOutputStream(file);
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);

        outputStream.close();
        objectOutputStream.close();
    }

    //返回两个文件的“相似度”，相同行数/base文件总行数
    public static double compareFiles(String baseFilePath, String testFilePath) throws IOException {
        FileReader baseFileReader = null;
        FileReader testFileReader = null;
        double ans;
        try {
            baseFileReader = new FileReader(baseFilePath);
            testFileReader = new FileReader(testFilePath);
            BufferedReader baseBufferReader = new BufferedReader(baseFileReader);
            BufferedReader testBufferReader = new BufferedReader(testFileReader);
            String baseLine;
            String testLine;
            int totalLineCnt = 0;
            int sameLineCnt = 0;
            while ((baseLine = baseBufferReader.readLine()) != null) {
                totalLineCnt++;
                testLine = testBufferReader.readLine();
                if (testLine != null && testLine.equalsIgnoreCase(baseLine)) {//不区分大小写
                    sameLineCnt++;
                }
            }
            ans = sameLineCnt * 1.0 / totalLineCnt;
        } catch (Exception e) {
            ans = -1;
        } finally {
            baseFileReader.close();
            testFileReader.close();
        }
        return ans;
    }

    public static void extraWriteFile(String sourcePath, String destPath) throws IOException {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        File destFile = new File(destPath);
        String destDirectoryPath = destFile.getParent();
        if (destDirectoryPath != null) {
            File destDirectorFile = new File(destDirectoryPath);
            if (!destDirectorFile.exists()) {
                destDirectorFile.mkdirs();
            }
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try {
            inputStream = new FileInputStream(sourcePath);
            randomAccessFile = new RandomAccessFile(destPath, "rw");
            long destFileLength = randomAccessFile.length();
            randomAccessFile.seek(destFileLength);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                randomAccessFile.write(buffer, 0, length);
            }
        } finally {
            inputStream.close();
            randomAccessFile.close();
        }
    }

    public static void printFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) > 0) {
            System.out.write(buffer, 0, length);
        }
        fileInputStream.close();
    }

    public static void deleteFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.delete()) {
            throw new Exception();
        }
    }

    public static void redirectPrintln(String s, String redirectPath, boolean type) throws IOException {//type==true为加写
        if (redirectPath == null) {
            System.out.println(s);
        } else {
            FileOutputStream fileOutputStream = new FileOutputStream(redirectPath, type);
            byte[] bytes = (s + "\n").getBytes();
            fileOutputStream.write(bytes, 0, bytes.length);
            fileOutputStream.close();
        }
    }

    /***
     * 传入两个路径就可以完成文件复制操作，目标文件夹，目标文件不存在时，会自动创建，前提是源文件存在
     * @param sourcePath 源文件路径
     * @param destPath 目标文件路径
     * @throws IOException 抛出异常
     */
    public static void copyFile(String sourcePath, String destPath) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File destFile = new File(destPath);
        String destDirectoryPath = destFile.getParent();
        if (destDirectoryPath != null) {
            File destDirectorFile = new File(destDirectoryPath);
            if (!destDirectorFile.exists()) {
                destDirectorFile.mkdirs();
            }
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try {
            inputStream = new FileInputStream(sourcePath);
            outputStream = new FileOutputStream(destPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

    public static boolean isFileExist(String filePath) {
        return new File(filePath).exists();
    }

    public static String getFileName(String filePath) {
        return new File(filePath).getName();
    }
}
