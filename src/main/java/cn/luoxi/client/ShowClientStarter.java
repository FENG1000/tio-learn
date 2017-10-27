package cn.luoxi.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import cn.luoxi.common.packets.P2PReqBody;


/**
 * 客户端启动
 *
 * @author 夏智峰
 * @create 2017-10-26 18:44
 */
public class ShowClientStarter {
  private static Logger log = LoggerFactory.getLogger(ShowClientStarter.class);
  //ip地址端口号
  private static Node serverNode = new Node("127.0.0.1", Const.PORT);
  //编码、解码、消息处理
  public static ClientAioHandler aioHandler = new ShowClientAioHandler();
  //监听
  public static ClientAioListener aioListener = new ShowClientListener();
  //断开连接后自动连接的，不想自动连接的请设置为null
  public static ReconnConf reconnConf = new ReconnConf(10000);
  //公用的上下文对象
  public static ClientGroupContext groupContext = new ClientGroupContext(aioHandler, aioListener, reconnConf);
  public static AioClient aioClient = null;
  public static ClientChannelContext clientChannelContext = null;
  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    read();
  }
  public static void read() {
    try {
      //自定义服务器地址和端口
      //node();
      //采用默认的
      aioClient = new AioClient(groupContext);
      clientChannelContext = aioClient.connect(serverNode);
      StringBuffer sb = new StringBuffer();
      int i = 1;
      sb.append("欢迎使用控制台聊天工具\n");
      sb.append(i++ + "、登录\n");
      sb.append(i++ + "、进入聊天界面\n");
      log.info(sb.toString());
      //根据输入的值不同进入不同的功能
      String type = sc.nextLine();
      switch (type) {
        case "1":
          //用户名密码登录接口
          login();
          break;
        case "2":
          //聊天功能
          write();
          break;
      }
    }catch (Exception e) {
      log.error(e.getMessage());
    }

  }

  //自定义服务器地址和端口
  public static void node() throws Exception{
    //设置可以自定义连接服务器
    log.info("请输入想要连接的服务器ip地址");
    String ip = sc.nextLine();
    log.info("请输入服务器端口号");
    String port = sc.nextLine();
    serverNode = new Node(ip, Integer.parseInt(port));
    log.info("连接服务器成功");
  }

  //用户名密码登录接口
  public static void login() throws Exception {
    Thread.sleep(500);
    log.info("请输入用户名");
    String name = sc.nextLine();
    log.info("请输入密码");
    String password = sc.nextLine();

    LoginReqBody loginReqBody = new LoginReqBody();
    loginReqBody.setLoginName(name);
    loginReqBody.setPassWord(password);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setBody(Json.toJson(loginReqBody).getBytes(Const.CHARSET));
    showPacket.setType(Type.LOGIN_REQ);

    Aio.send(clientChannelContext, showPacket);
    log.info("登录成功");
    read();
  }

  //聊天框
  public static void write() throws Exception {
    Thread.sleep(500);
    while (true) {
      log.info("消息格式：用户名 消息内容，对出聊天请输入'T+'");
      String write = sc.nextLine();
      String toName = StringUtils.substringBefore(write, " ");
      if ("T+".equals(toName)) {
        log.info("感谢使用");
        break;
      }
      String  text = StringUtils.substringAfter(write, " ");

      P2PReqBody p2PReqBody = new P2PReqBody();
      p2PReqBody.setToUserId(toName);
      p2PReqBody.setText(text);

      ShowPacket showPacket = new ShowPacket();
      showPacket.setType(Type.P2P_REQ);
      showPacket.setBody(Json.toJson(p2PReqBody).getBytes(Const.CHARSET));

      Aio.send(clientChannelContext, showPacket);
      log.info("发送成功");
    }

  }
}
