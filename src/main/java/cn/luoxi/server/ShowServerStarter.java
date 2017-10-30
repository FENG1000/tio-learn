package cn.luoxi.server;

import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;

import cn.luoxi.common.Const;


/**
 * 启动服务
 *
 * @author 夏智峰
 * @create 2017-10-26 18:36
 */
public class ShowServerStarter {
  //编码、解码、消息处理
  static ServerAioHandler aioHandler = new ShowServerAioHandler();
  //事件监听，可以为空不监听
  static ServerAioListener aioListener = new ShowServerAioListener();
  //连接公用的上下文对象
  static ServerGroupContext serverGroupContext = new ServerGroupContext(aioHandler, aioListener);
  //aioServer对象
  static AioServer aioServer = new AioServer(serverGroupContext);
  //需要绑定的ip,为空默认是本机ip
  static String serverIp = null;
  //监听的端口
  static int serverPort = Const.PORT;

  /**
   * 启动程序
   */
  public static void main(String[] args) throws IOException {
    aioServer.start(serverIp, serverPort);
  }
}
