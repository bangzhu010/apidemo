package com.example.liangwenchao.appdemo.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by LiangWenchao on 2016/4/15.
 */
public class FileUtils {
    public static void writeStrToFile(Context context, String fileName, String data, int mode) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(fileName, mode);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readStrFromFile(Context context, String fileName) {

        FileInputStream input;
        BufferedReader reader = null;

        StringBuffer data = new StringBuffer();

        try {
            input = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}
