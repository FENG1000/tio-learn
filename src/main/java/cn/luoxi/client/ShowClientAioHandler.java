package cn.luoxi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

import java.util.HashMap;
import java.util.Map;

import cn.luoxi.client.handler.LoginRespHandler;
import cn.luoxi.client.handler.P2PRespHandler;
import cn.luoxi.common.ShowAbsAioHandler;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.Type;
import cn.luoxi.common.intf.AbsShowBsHandler;

/**
 * @author 夏智峰
 * @create 2017-10-26 16:56
 */
public class ShowClientAioHandler extends ShowAbsAioHandler implements ClientAioHandler {
  private static Logger log = LoggerFactory.getLogger(ShowClientAioHandler.class);
  private static Map<Byte, AbsShowBsHandler<?>> handlerMap = new HashMap<>();
  static {
    handlerMap.put(Type.LOGIN_RESP, new LoginRespHandler());
    handlerMap.put(Type.P2P_REQ, new P2PRespHandler());
  }
  private static ShowPacket heartbeatPacket = new ShowPacket(Type.HEART_BEAT_REQ, null);
  @Override
  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    ShowPacket showPacket = (ShowPacket) packet;
    Byte type = showPacket.getType();
    AbsShowBsHandler<?> showBsHandler = handlerMap.get(type);
    showBsHandler.handler(showPacket, channelContext);
    return;
  }

  //此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
  @Override
  public Packet heartbeatPacket() {
    return heartbeatPacket;
  }
}
