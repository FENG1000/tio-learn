package cn.luoxi.client;

import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;
import org.tio.utils.json.Json;

import java.util.Scanner;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.Type;
import cn.luoxi.common.packets.LoginReqBody;


/**
 * 客户端启动
 *
 * @author 夏智峰
 * @create 2017-10-26 18:44
 */
public class ShowClientStarter {
  public static Node serverNode = new Node("192.168.1.127", Const.PORT);
  //编码、解码、消息处理
  public static ClientAioHandler aioHandler = new ShowClientAioHandler();
  //监听
  public static ClientAioListener aioListener = new ShowClientListener();
  //断开连接后自动连接的，不想自动连接的请设置为null
  public static ReconnConf reconnConf = new ReconnConf(2000);
  //公用的上下文对象
  public static ClientGroupContext groupContext = new ClientGroupContext(aioHandler, aioListener, reconnConf);
  public static AioClient aioClient = null;
  public static ClientChannelContext clientChannelContext = null;

  public static void main(String[] args) throws Exception {
    read();
  }
  public static void read() throws Exception {
    Scanner sc = new Scanner(System.in);
    StringBuffer str = new StringBuffer();
    int i = 1;
    System.out.println("欢迎使用控制台聊天java小程序");
//    System.out.println("请输入想要连接的服务器ip地址");
//    String ip = sc.nextLine();
//    System.out.println("请输入服务器端口号");
//    String port = sc.nextLine();
    aioClient = new AioClient(groupContext);
    clientChannelContext = aioClient.connect(serverNode);
    System.out.println("请输入用户名");
    String name = sc.nextLine();
    System.out.println("请输入密码");
    String password = sc.nextLine();
    LoginReqBody loginReqBody = new LoginReqBody();
    loginReqBody.setLoginName(name);
    loginReqBody.setPassWord(password);
    ShowPacket showPacket = new ShowPacket();
    showPacket.setBody(Json.toJson(loginReqBody).getBytes(Const.CHARSET));
    showPacket.setType(Type.LOGIN_REQ);
    Aio.send(clientChannelContext, showPacket);

  }
}
