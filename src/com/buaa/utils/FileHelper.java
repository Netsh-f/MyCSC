package com.buaa.utils;

import java.io.*;

public class FileHelper {

    public static void extraWriteFile(String sourcePath, String destPath) throws IOException {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        File destFile = new File(destPath);
        String destDirectoryPath = destFile.getParent();
        File destDirectorFile = new File(destDirectoryPath);
        if (!destDirectorFile.exists()) {
            destDirectorFile.mkdirs();
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
        byte[] buffer = new byte[(int) file.length()];
        while (fileInputStream.read(buffer) != -1) {
            System.out.println(new String(buffer));
        }
    }

    public static void deleteFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.delete()) {
            throw new Exception();
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
        File destDirectorFile = new File(destDirectoryPath);
        if (!destDirectorFile.exists()) {
            destDirectorFile.mkdirs();
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