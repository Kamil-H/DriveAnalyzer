package kamilhalko.com.driveanalyzer.managers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import kamilhalko.com.driveanalyzer.utils.AppConstants;
import kamilhalko.com.driveanalyzer.utils.DateUtils;

public class FileManager {

    private static File createOrOpenDir(Context context) {
        return context.getDir("sync", Context.MODE_PRIVATE);
    }

    public static void saveToFile(Context context, String content) {
        String fileName = DateUtils.now() + "_" + AppConstants.getDeviceId(context) + ".json";

        File dir = createOrOpenDir(context);
        File file = new File(dir, fileName);
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFile(Context context) {
        File dir = createOrOpenDir(context);
        List<File> fileList = getListFiles(dir);
        FileInputStream fin;

        for (File file : fileList) {
            try {
                fin = new FileInputStream(file);
                String ret = convertStreamToString(fin);
                Log.i("FILE: ", ret);
                fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".json")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
