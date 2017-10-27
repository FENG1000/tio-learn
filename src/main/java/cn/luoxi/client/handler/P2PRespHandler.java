package cn.luoxi.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.common.packets.P2PRespBody;

/**
 * 两人聊天响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-27 13:16
 */
public class P2PRespHandler extends AbsShowBsHandler<P2PRespBody> {
  private static Logger log = LoggerFactory.getLogger(P2PRespHandler.class);
  @Override
  public Class<P2PRespBody> bodyClass() {
    return P2PRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, P2PRespBody bsBody, ChannelContext channelContext)
          throws Exception {
    log.info("来自" + bsBody.getFromUserId() + "的消息，消息内容为" + bsBody.getText());
    String  str = new String(packet.getBody(), Const.CHARSET);
    log.info("试试packet里的信息是什么：" + str);
    return null;
  }
}
