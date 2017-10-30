package cn.luoxi.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.util.concurrent.atomic.AtomicLong;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.ShowSessionContext;
import cn.luoxi.common.Type;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.common.packets.LoginReqBody;
import cn.luoxi.common.packets.LoginRespBody;

/**
 * 登陆请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-26 15:20
 */
public class LoginReqHandler extends AbsShowBsHandler<LoginReqBody> {
  private static Logger log = LoggerFactory.getLogger(LoginReqHandler.class);
  //JDK在concurrent包里提供了一些线程安全的基本数据类型的实现，比如 Long型
  // 对应的concurrent包的类是AtomicLong。
  AtomicLong tokenSeq = new AtomicLong();
  @Override
  public Class<LoginReqBody> bodyClass() {
    return LoginReqBody.class;
  }
  @Override
  public Object handler(ShowPacket packet, LoginReqBody bsBody, ChannelContext channelContext)
          throws Exception {
    log.info("收到登陆请求消息：{}", Json.toJson(bsBody));
    LoginRespBody respBody = new LoginRespBody();
    //始终默认登陆成功
    respBody.setCode(Const.Code.SUCCESS);
    respBody.setToken(newToken());

    //默认把登陆名称设置为登陆的id
    String userId = bsBody.getLoginName();
    //绑定用户
    Aio.bindUser(channelContext, userId);

    //通过监听器在用户连接到服务器的时候创建session然后通过bindUser绑定用户信息
    ShowSessionContext context = (ShowSessionContext) channelContext.getAttribute();
    //模拟存放用户id
    context.setUserId(userId);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.LOGIN_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));
    //发送到指定用户
    Aio.send(channelContext, showPacket);
    return null;
  }
  //在线程安全的情况下创建token
  private String newToken() {
    return System.currentTimeMillis() + "_" + tokenSeq.incrementAndGet();
  }
}
