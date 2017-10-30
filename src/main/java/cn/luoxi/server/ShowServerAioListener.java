package cn.luoxi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;
import org.tio.utils.json.Json;

import cn.luoxi.common.ShowSessionContext;


/**
 * 服务器监听
 *
 * @author 夏智峰
 * @create 2017-10-26 17:11
 */
public class ShowServerAioListener implements ServerAioListener {
  private static Logger log = LoggerFactory.getLogger(ShowServerAioListener.class);
  //关闭
  @Override
  public void onAfterClose(ChannelContext channelContext, Throwable throwable, String remark, boolean
          isRemove) throws Exception {
    log.info("onAfterClose channelContext:{}, throwable:{}, remark:{}, isRemove:{}", channelContext, throwable, remark, isRemove);
  }

  //连接后
  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
    log.info("onAfterConnected channelContext:{}, isConnected:{}, isReconnect:{}", channelContext, isConnected, isReconnect);
    channelContext.setAttribute(new ShowSessionContext());
  }

  @Override
  public void onAfterReceived(ChannelContext channelContext, Packet packet, int packetSize) throws
          Exception {
    log.info("onAfterReceived channelContext:{}, packet:{}, packetSize:{}", channelContext, Json.toJson(packet), packetSize);

  }

  @Override
  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws
          Exception {
    log.info("onAfterSent channelContext:{}, packet:{}, isSentSuccess:{}", channelContext, Json.toJson(packet), isSentSuccess);

  }

  @Override
  public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) {

  }
}
