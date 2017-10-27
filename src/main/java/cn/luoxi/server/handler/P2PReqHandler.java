package cn.luoxi.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import cn.luoxi.common.Const;
import cn.luoxi.common.ShowPacket;
import cn.luoxi.common.ShowSessionContext;
import cn.luoxi.common.Type;
import cn.luoxi.common.intf.AbsShowBsHandler;
import cn.luoxi.common.packets.P2PReqBody;
import cn.luoxi.common.packets.P2PRespBody;

/**
 * 两人聊天请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-27 11:24
 */
public class P2PReqHandler extends AbsShowBsHandler<P2PReqBody> {
  private static Logger log = LoggerFactory.getLogger(P2PReqHandler.class);
  @Override
  public Class<P2PReqBody> bodyClass() {
    return P2PReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, P2PReqBody bsBody, ChannelContext channelContext)
          throws Exception {
    log.info("(bsBody的信息)收到聊天请求，发送给用户" + bsBody.getToUserId() + "，消息内容为"+ bsBody.getText());
    String  str = new String(packet.getBody(), Const.CHARSET);
    log.info("试试packet里的信息是什么：" + str);

    //取出在监听器中模拟登陆创建的session
    ShowSessionContext context = (ShowSessionContext) channelContext.getAttribute();

    //创建聊天响应信息对象
    P2PRespBody p2PRespBody = new P2PRespBody();
    p2PRespBody.setFromUserId(context.getUserId());
    p2PRespBody.setText(bsBody.getText());

    //将响应信息对象转成json字符串放入到消息包中
    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.P2P_REQ);
    showPacket.setBody(Json.toJson(p2PRespBody).getBytes(Const.CHARSET));

    //向服务器发出请求
    Aio.sendToUser(channelContext.getGroupContext(), bsBody.getToUserId(), showPacket);
    return null;
  }
}
