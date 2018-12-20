package cn.yunxin.ftp.util;


import cn.yunxin.log4jutil.bean.LoggerNameEnum;
import cn.yunxin.log4jutil.service.LogBaseService;
import cn.yunxin.log4jutil.service.impl.AutoEventLogService;
import cn.yunxin.log4jutil.service.impl.ErrLogService;

/**
 * @ClassName: LoggerUtil
 * @Description: 获取Logger 日志对象的功能类
 */
public class LoggerUtil {

    public static LogBaseService logService = new ErrLogService(PublicUtil.loggerName,PublicUtil.configPath + "log4j.xml");

    public static LogBaseService logBaseService =new AutoEventLogService(PublicUtil.loggerName= LoggerNameEnum.自动程序入文件.getLoggerName(),PublicUtil.configPath+"log4j.xml");
}
