package cn.yunxin.ftp.util;

import cn.yunxin.ftp.entity.FTPBean;
import cn.yunxin.log4jutil.bean.ErrLogBean;
import com.jcraft.jsch.*;

import java.util.Properties;

public class GetChannelSftp {

    public static ChannelSftp channelSftp;
    public static Session session;
    static ErrLogBean errLogBean;
    /**
     * 获取 SFTP Channel
     * @param ftpBean
     * @return
     * @throws JSchException
     */
    public static ChannelSftp getChannelSftp(FTPBean ftpBean) throws JSchException {
        String host = ftpBean.getHostIP();
        int port = ftpBean.getPort();
        String userName = ftpBean.getUserName();
        String password = ftpBean.getPassword();
        // 创建JSch对象
        JSch jsch = new JSch();
        // 根据用户名，主机ip，端口获取一个Session对象
        if (port <= 0) {
            // 连接服务器，采用默认端口
            session = jsch.getSession(userName, host);
            System.out.println("session init successfully...................................");
        } else {
            // 采用指定的端口连接服务器
            session = jsch.getSession(userName, host, port);
            System.out.println("session init successfully...................................");
        }
        // 设置密码
        session.setPassword(password);
        System.out.println("password set successfully....................................");
        Properties configTemp = new Properties();
        configTemp.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(configTemp);
        // 设置timeout时间
        session.setTimeout(60000);
        try {
            session.connect();
            System.out.println("session connect sucessfully...........................");
        } catch (Exception e) {
            e.printStackTrace();
            errLogBean = new ErrLogBean("session连接失败",e.getMessage());
            LoggerUtil.logService.log(errLogBean);
            LoggerUtil.logService.logger.error("");
            throw new JSchException("session 连接失败！！！"+e.getMessage());

        }
        // 通过Session建立链接
        // 打开SFTP通道
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            System.out.println("channel open successfully.................................");
            // 建立SFTP通道的连接
            channel.connect();
            System.out.println("channel connect successfully......................................");
        } catch (Exception e) {
            e.printStackTrace();
            errLogBean = new ErrLogBean("建立SFTP通道连接失败！！！",e.getMessage());
            LoggerUtil.logService.log(errLogBean);
            LoggerUtil.logService.logger.error("");
            throw new JSchException("建立SFTP通道连接失败！！！" + e.getMessage());
        }
        System.out.println("Connected successfully!!!\n【ftpHost ：" + host + "】, 【ftpUserName ： " + userName + "】, 【returning: " + channel + "】");
        return (ChannelSftp) channel;
    }

    /**
     * 关闭连接
     * @throws Exception
     */
    public static void closeChannel() throws Exception {


        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }

        System.out.println("disconnected SFTP successfully!");
    }
}
