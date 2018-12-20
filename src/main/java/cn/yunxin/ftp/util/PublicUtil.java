package cn.yunxin.ftp.util;

import cn.yunxin.log4jutil.bean.LoggerNameEnum;

import javax.swing.*;
import java.io.File;

public class PublicUtil {

    /**
     * 默认相对路径
     */
    public static String configPath="../config";

    /**
     * 日志文件名称
     */
    public static String loggerName = LoggerNameEnum.异常日志入文件.getLoggerName();

    /**
     * 文件路径
     */
    public String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件是否存在，返回File
     * @param fileName
     * @return
     * @throws Exception
     */
    public File readFile(String fileName) throws Exception{

        filePath = configPath + fileName;
        String desc = "configPath:("+ filePath +")\n";
        File file = new File(filePath);
        if (!file.exists()){
            JOptionPane.showMessageDialog(null,fileName+"配置文件路径不存在，检查目录！\n" +desc,"消息",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (file.length() == 0){
            throw new Exception("配置文件为空!!!");
        }
        System.out.println("使用的配置文件路径："+filePath);
        return file;
    }
}
