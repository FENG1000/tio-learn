package cn.luoxi.client.handler;

import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.ShowSessionContext;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.common.packets.LoginRespBody;

/**
 * 登陆响应
 *
 * @author 夏智峰
 * @create 2017-10-26 16:01
 */
public class LoginRespHandler extends AbsShowBsHandler<LoginRespBody> {
  @Override
  public Class<LoginRespBody> bodyClass() {
    return LoginRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, LoginRespBody bsBody, ChannelContext channelContext)
          throws Exception {
    System.out.println("收到返回的登陆响应："+ Json.toJson(bsBody));
    if(Const.Code.SUCCESS.equals(bsBody.getCode())) {
      //模拟存放token
      ShowSessionContext showSessionContext = (ShowSessionContext) channelContext.getAttribute();
      showSessionContext.setToken(bsBody.getToken());
      System.out.println("登陆成功，token是："+bsBody.getToken());
    }
    return null;
  }
}
