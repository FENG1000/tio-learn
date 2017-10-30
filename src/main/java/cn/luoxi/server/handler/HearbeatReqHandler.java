package cn.luoxi.server.handler;

import org.tio.core.ChannelContext;

import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.common.packets.P2PReqBody;

/**
 * 心跳处理
 *
 * @author 夏智峰
 * @create 2017-10-28 13:37
 */
public class HearbeatReqHandler extends AbsShowBsHandler<P2PReqBody> {
  @Override
  public Class<P2PReqBody> bodyClass() {
    return P2PReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, P2PReqBody bsBody, ChannelContext channelContext)
          throws Exception {
    //心跳消息啥也不用干
    return null;
  }
}
