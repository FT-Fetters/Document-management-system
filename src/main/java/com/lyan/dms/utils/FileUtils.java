package com.lyan.dms.utils;

import java.io.*;

public class FileUtils {


    static int BUFFER_SIZE = 16 * 1024;

    public static void copy(File src, File dst) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int i;
            while ((i = in.read(buffer)) != -1) {
                out.write(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != out) {
                out.close();
            }
        }
    }
}
