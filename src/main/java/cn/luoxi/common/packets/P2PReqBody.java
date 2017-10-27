package cn.luoxi.common.packets;

/**
 * 两人聊天请求
 *
 * @author 夏智峰
 * @create 2017-10-27 11:15
 */
public class P2PReqBody extends BaseBody {
  //消息内容
  private String text;
  //发送给谁,在登陆时已经设置userName等于userId
  private String toUserId;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getToUserId() {
    return toUserId;
  }

  public void setToUserId(String toUserId) {
    this.toUserId = toUserId;
  }
}
