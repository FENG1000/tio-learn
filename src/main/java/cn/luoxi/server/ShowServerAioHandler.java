package cn.luoxi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.util.HashMap;
import java.util.Map;

import cn.luoxi.common.ShowAbsAioHandler;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.Type;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.server.handler.LoginReqHandler;
import cn.luoxi.server.handler.P2PReqHandler;


/**
 * @desc 消息处理
 * @author 夏智峰
 * @create 2017/10/26 16:46
**/
public class ShowServerAioHandler extends ShowAbsAioHandler implements ServerAioHandler {
  private static Logger log = LoggerFactory.getLogger(ShowServerAioHandler.class);
  private static Map<Byte, AbsShowBsHandler<?>> handlerMap = new HashMap<>();
  static {
    handlerMap.put(Type.LOGIN_REQ, new LoginReqHandler());
    handlerMap.put(Type.P2P_REQ, new P2PReqHandler());
  }
  @Override
  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    ShowPacket showPacket = (ShowPacket) packet;
    Byte type = showPacket.getType();
    AbsShowBsHandler<?> showBsHandler = handlerMap.get(type);
    if(showBsHandler ==null) {
      log.error("{}，找不到处理类，type：{}", channelContext, type);
      return;
    }
    showBsHandler.handler(showPacket, channelContext);
  }
}
