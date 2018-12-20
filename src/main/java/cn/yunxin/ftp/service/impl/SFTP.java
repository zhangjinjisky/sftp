package cn.yunxin.ftp.service.impl;

import cn.yunxin.ftp.entity.FTPBean;
import cn.yunxin.ftp.service.UploadFilesToServer;
import cn.yunxin.ftp.util.GetChannelSftp;
import cn.yunxin.ftp.util.LoggerUtil;
import cn.yunxin.ftp.util.Upload;
import cn.yunxin.log4jutil.bean.AutoEventBean;
import cn.yunxin.log4jutil.bean.ErrLogBean;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

import java.util.List;

public class SFTP implements UploadFilesToServer {

    ErrLogBean errLogBean;
    AutoEventBean autoEventBean;

    public SFTP() {
    }

    /**
     * 上传文件
     * @param ftpBeans
     * @throws Exception
     */
    @Override
    public void uploadFilesToServer(List<FTPBean> ftpBeans) throws Exception {
        for (FTPBean ftpBean : ftpBeans) {
            ChannelSftp chSftp = null;
            try {
                chSftp = GetChannelSftp.getChannelSftp(ftpBean);
            } catch (JSchException e) {
                errLogBean = new ErrLogBean("服务器连接失败",e.getMessage());
                LoggerUtil.logService.log(errLogBean);
                LoggerUtil.logService.logger.error("");
                throw new Exception("服务器连接失败"+e.getMessage());
            }
            if (chSftp == null) {
                return ;
            }
            String srcPath = ftpBean.getSrcPathStr();
            String destPath = ftpBean.getDestPathStr();
            Upload.upload(srcPath, destPath, chSftp);
            GetChannelSftp.closeChannel();
        }
    }
}
