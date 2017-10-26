package cn.luoxi.common.intf;

import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.packets.BaseBody;


/**
 * @author 夏智峰
 * @create 2017-10-26 15:04
 */
public abstract class AbsShowBsHandler<T extends BaseBody> implements ShowBsHandlerIntf {

  //下边将json转为对象时用到
  public abstract Class<T> bodyClass();
  //消息处理
  @Override
  public Object handler(ShowPacket packet, ChannelContext channelContext) throws Exception {
    String jsonStr = null;
    T bsBody = null;
    if (packet.getBody() != null) {
      jsonStr = new String(packet.getBody(), Const.CHARSET);
      bsBody = Json.toBean(jsonStr, bodyClass());
    }
    return handler(packet, bsBody, channelContext);
  }
  public abstract Object handler(ShowPacket packet, T bsBody, ChannelContext channelContext) throws Exception;
}
