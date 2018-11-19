package top.iofox.lib.otool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtil {
    public static byte[] readFile(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            int length = in.available();
            byte[] buffer = new byte[length];
            int read = in.read(buffer, 0, length);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[]{};
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
