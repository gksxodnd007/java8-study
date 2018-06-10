package com.codingsquid.stream;

import java.io.FileInputStream;

public class GetData {

    public static String data;

    static {
        try {
            byte[] file = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream("/Users/codingsquid/dev/java8-study/src/main/resources/test-data.txt");
            int length = fileInputStream.read(file);
            data = new String(file, 0, length);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
