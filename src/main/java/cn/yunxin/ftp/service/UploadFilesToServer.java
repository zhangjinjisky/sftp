package cn.yunxin.ftp.service;

import cn.yunxin.ftp.entity.FTPBean;

import java.util.List;

public interface UploadFilesToServer {

    /**
     * 连接服务器上传文件
     * @param ftpBeans
     * @throws Exception
     */
    public void uploadFilesToServer(List<FTPBean> ftpBeans) throws Exception;
}
