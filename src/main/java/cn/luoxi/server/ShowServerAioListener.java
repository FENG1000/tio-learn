package cn.luoxi.server;

import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;

import cn.luoxi.common.ShowSessionContext;


/**
 * 服务器监听
 *
 * @author 夏智峰
 * @create 2017-10-26 17:11
 */
public class ShowServerAioListener implements ServerAioListener {
  //连接前
  @Override
  public void onAfterClose(ChannelContext channelContext, Throwable throwable, String s, boolean
          b) throws Exception {

  }

  //连接后
  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {
    channelContext.setAttribute(new ShowSessionContext());
  }

  @Override
  public void onAfterReceived(ChannelContext channelContext, Packet packet, int i) throws
          Exception {

  }

  @Override
  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws
          Exception {

  }

  @Override
  public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) {

  }
}
