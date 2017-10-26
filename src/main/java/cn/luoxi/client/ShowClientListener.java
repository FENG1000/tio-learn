package cn.luoxi.client;

import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

import cn.luoxi.common.ShowSessionContext;


/**
 * 客户端监听器
 *
 * @author 夏智峰
 * @create 2017-10-26 18:34
 */
public class ShowClientListener implements ClientAioListener {
  @Override
  public void onAfterClose(ChannelContext channelContext, Throwable throwable, String s, boolean
          b) throws Exception {

  }

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
