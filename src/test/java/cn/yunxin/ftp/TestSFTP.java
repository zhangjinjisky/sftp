package cn.yunxin.ftp;

import cn.yunxin.ftp.entity.FTPBean;
import cn.yunxin.ftp.service.impl.SFTP;
import cn.yunxin.ftp.util.PublicUtil;
import cn.yunxin.log4jutil.bean.LoggerNameEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSFTP {

    /**
     * 向多台服务发送文件或文件夹
     */
    @Test
    public void test1(){

        PublicUtil.configPath="C:/DataBaseTool/config/";
        List<FTPBean> ftpBeans = new ArrayList();
        FTPBean ftpBean = new FTPBean("192.168.140.129","zhang","123456","D:/ico/sftp.txt","/upload/");
        ftpBeans.add(ftpBean);
        FTPBean ftpBean1 = new FTPBean("192.168.140.130","mysftp","123456","D:/dd/sftp.txt","/upload/");
        ftpBeans.add(ftpBean1);
        SFTP sftp = new SFTP();
        try {
            sftp.uploadFilesToServer(ftpBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 向单台服务器发送文件或文件夹
     */
    @Test
    public void test2(){

        PublicUtil.configPath="C:/DataBaseTool/config/";
        List<FTPBean> ftpBeans = new ArrayList();
        FTPBean ftpBean1 = new FTPBean("192.168.140.130","mysftp","123456","D:/dd/sftp.txt","/upload/");
        ftpBeans.add(ftpBean1);
        SFTP sftp = new SFTP();
        try {
            sftp.uploadFilesToServer(ftpBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
