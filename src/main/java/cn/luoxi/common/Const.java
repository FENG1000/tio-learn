package cn.luoxi.common;

/**
 * @author 夏智峰
 * @create 2017-10-26 15:10
 */
public class Const {
  public static interface Code {
    Integer SUCCESS = 1;
    Integer FAIL = 2;
  }
  //端口号
  public static final Integer PORT = 6789;
  //心跳检查时间间隔
  public static final Integer TIMEOUT = 5000;
  //消息头的长度除去消息内容的其他数据长度：消息头（消息体长度）+消息类型（1byte）
  public static final Integer HEADER_LENGHT = 5;
  //编码格式
  public static final String CHARSET = "utf-8";
}
