package cn.luoxi.common.intf;

import org.tio.core.ChannelContext;

import cn.luoxi.common.ShowPacket;


/**
 * @author 夏智峰
 * @create 2017-10-26 14:51
 */
public interface ShowBsHandlerIntf {
  Object handler(ShowPacket packet, ChannelContext channelContext) throws Exception;
}
