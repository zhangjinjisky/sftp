package cn.yunxin.ftp.util;

import cn.yunxin.log4jutil.bean.AutoEventBean;
import cn.yunxin.log4jutil.bean.ErrLogBean;
import com.jcraft.jsch.ChannelSftp;

import java.io.File;

public class Upload {

    static ErrLogBean errLogBean;
    static AutoEventBean autoEventBean;
    /**
     * 实现上传文件
     * @param srcPath 源文件地址
     * @param destPath 目标文件地址
     * @param chSftp  sftp通道
     * @throws Exception
     */
    public static void upload(String srcPath, String destPath, ChannelSftp chSftp) throws Exception {
        try {
            File file = new File(srcPath);
            if (!file.exists()) {
                errLogBean = new ErrLogBean("文件不存在",file.getAbsolutePath());
                LoggerUtil.logService.log(errLogBean);
                LoggerUtil.logService.logger.error("");
            }
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files == null || files.length <= 0) {
                    return;
                }
                try {
                    for (File f : files) {
                        String fp = f.getAbsolutePath();
                        if (f.isDirectory()) {
                            String mkdir = destPath + "/" + f.getName();
                            try {
                                chSftp.cd(mkdir);
                            } catch (Exception e) {
                                chSftp.mkdir(mkdir);
                            }
                            upload(fp, mkdir, chSftp);
                        } else {
                            try {
                                chSftp.put(fp, destPath, ChannelSftp.OVERWRITE);
                                autoEventBean = new AutoEventBean("发送文件"+f.getName(),"发送成功");
                            }catch (Exception e){
                                autoEventBean = new AutoEventBean("发送文件"+f.getName(),"发送失败");
                            }finally {
                                LoggerUtil.logBaseService.log(autoEventBean);
                                LoggerUtil.logBaseService.logger.error("");
                            }

                        }
                    }
                }catch (Exception e){
                    throw new Exception(""+e.getMessage()+file.getName());
                }
            } else {
                try {
                    String fp = file.getAbsolutePath();
                    chSftp.put(fp, destPath, ChannelSftp.OVERWRITE);
                    autoEventBean = new AutoEventBean("发送文件"+file.getName(),"发送成功");
                }catch (Exception e){
                    autoEventBean = new AutoEventBean("发送文件"+file.getName(),"发送失败");
                    errLogBean = new ErrLogBean("文件上传失败",e.getMessage());
                    LoggerUtil.logService.log(errLogBean);
                    LoggerUtil.logService.logger.error("");
                    throw new Exception("文件上传失败"+e.getMessage());
                }finally {
                    LoggerUtil.logBaseService.log(autoEventBean);
                    LoggerUtil.logBaseService.logger.error("");
                }
            }

        }catch (Exception e){
            errLogBean = new ErrLogBean("文件上传失败",e.getMessage());
            LoggerUtil.logService.log(errLogBean);
            LoggerUtil.logService.logger.error("");
            throw new Exception("文件上传失败"+e.getMessage());
        }
    }
}
