package org.jfaster.generator.util;

/**
 * Created by xiaojianyu on 17/5/23.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 文件操作工具类
 */
public class FileUtil {
    /**
     * 在指定的位置创建指定的文件
     *
     * @param filePath 完整的文件路径
     * @throws Exception
     */
    public static void mkdirFile(String filePath) throws Exception {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        file = null;
    }

    public static void writer(String filePath, String templateStr, boolean isAppend) throws Exception {
        FileWriter fileWritter = new FileWriter(filePath, isAppend);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(templateStr);
        bufferWritter.close();
    }

    /**
     * 在指定的位置创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 若创建成功，则返回True；反之，则返回False
     */
    public static boolean mkDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

}