package cn.yunxin.ftp.entity;


public class FTPBean {
    //主机IP
    private String hostIP;
    //端口号
    private int port;
    //用户名
    private String userName;
    //密码
    private String password;
    //源文件地址
    private String srcPathStr;
    //目标文件地址
    private String destPathStr;

    /**
     * 指定端口
     * @param hostIP
     * @param port
     * @param userName
     * @param password
     * @param srcPathStr
     * @param destPathStr
     */
    public FTPBean(String hostIP, int port, String userName, String password, String srcPathStr, String destPathStr) {
        this.hostIP = hostIP;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.srcPathStr = srcPathStr;
        this.destPathStr = destPathStr;
    }

    /**
     * 使用默认端口
     * @param hostIP
     * @param userName
     * @param password
     * @param srcPathStr
     * @param destPathStr
     */
    public FTPBean(String hostIP, String userName, String password, String srcPathStr, String destPathStr) {
        this.hostIP = hostIP;
        this.userName = userName;
        this.password = password;
        this.srcPathStr = srcPathStr;
        this.destPathStr = destPathStr;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSrcPathStr() {
        return srcPathStr;
    }

    public void setSrcPathStr(String srcPathStr) {
        this.srcPathStr = srcPathStr;
    }

    public String getDestPathStr() {
        return destPathStr;
    }

    public void setDestPathStr(String destPathStr) {
        this.destPathStr = destPathStr;
    }
}
