package com.shujuan.studyframework.common;

import java.io.*;
import java.util.UUID;

/**
 * @Auther: guany
 * @Date: 2023/02/12
 */
public class IdGenerator {
    private static final String LIB_BIN = "lib-bin/";
    private static final String ACWRAPPER = "libIDGenerator";

    private IdGenerator() {
    }

    private static IdGenerator getInstance() {
        return IdGenerator.SingletonHolder.instance;
    }

    private native long nextId();

    public static String getNextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static synchronized void loadLib(String libName) throws IOException {
        String systemType = System.getProperty("os.name");
        String libExtension = "";
        if (systemType.toLowerCase().indexOf("win") != -1) {
            libExtension = ".dll";
        } else if (systemType.toLowerCase().indexOf("mac") != -1) {
            libExtension = ".dylib";
        } else {
            libExtension = ".so";
        }

        String libFullName = libName + libExtension;
        String nativeTempDir = System.getProperty("java.io.tmpdir");
        InputStream in = null;
        BufferedInputStream reader = null;
        FileOutputStream writer = null;
        File extractedLibFile = new File(nativeTempDir + File.separator + libFullName);
        if (!extractedLibFile.exists()) {
            try {
                in = IdGenerator.class.getClassLoader().getResourceAsStream("lib-bin/".concat(libFullName));
                if (in == null) {
                    in = IdGenerator.class.getResourceAsStream(libFullName);
                }

                IdGenerator.class.getResource(libFullName);
                reader = new BufferedInputStream(in);
                writer = new FileOutputStream(extractedLibFile);

                for(byte[] buffer = new byte[1024]; reader.read(buffer) > 0; buffer = new byte[1024]) {
                    writer.write(buffer);
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }

                if (writer != null) {
                    writer.close();
                }

            }
        }

        System.load(extractedLibFile.toString());
    }

    private static class SingletonHolder {
        private static final IdGenerator instance = new IdGenerator();

        private SingletonHolder() {
        }
    }
}
