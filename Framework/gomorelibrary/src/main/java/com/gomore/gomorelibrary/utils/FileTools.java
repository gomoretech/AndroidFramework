package com.gomore.gomorelibrary.utils;

import android.content.Context;
import android.os.Environment;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 文件读写工具
 */
public class FileTools {
    /**
     * SD卡是否存在
     **/
    private boolean hasSD = false;
    /**
     * SD卡的路径
     **/
    private String SDPATH;

    public void creatSDCardFile(Context context) {
        hasSD = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        hasSD = false;
        if (hasSD) {
            SDPATH = context.getExternalFilesDir(null).getAbsolutePath();
        } else {
            SDPATH = context.getApplicationContext().getFilesDir().getAbsolutePath();
        }

        File destDir = new File(SDPATH);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + "/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /****/
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + "/" + fileName);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 删除SD卡上的文件
     *
     * @param fileName
     */
    public boolean deleteSDFile(String fileName) {
        File file = new File(fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * 写入内容到SD卡中的txt文本中 str为内容
     */
    public void writeSDFile(String str, String fileName) {
        try {
            File f = new File(fileName);
            FileOutputStream os = new FileOutputStream(f);
            DataOutputStream out = new DataOutputStream(os);
            out.writeShort(2);
            out.writeUTF("");
            out.close();
            os.close();
        } catch (Exception e) {
        }
    }


    /**
     * 读取SD卡中文本文件
     *
     * @param fileName
     * @return
     */
    public String readSDFile(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            int c;
            while ((c = fis.read()) != -1) {
                sb.append((char) c);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 递归删除文件和文件夹
     * 要删除的根目录
     */
    public void recursionDeleteFile() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/dvnchina/");
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    public void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    public static String getDownloadApkCachePath() {
        String appCachePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            appCachePath = Environment.getExternalStorageDirectory() + "/VersionPath/";
        } else {
            appCachePath = Environment.getDataDirectory().getPath() + "/VersionPath/";
        }
        File file = new File(appCachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return appCachePath;
    }


}