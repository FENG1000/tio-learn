package cn.luoxi.common.packets;

/**
 * 两人聊天响应
 *
 * @author 夏智峰
 * @create 2017-10-27 11:18
 */
public class P2PRespBody extends BaseBody {
  //消息内容
  private String text;
  //来自哪个用户
  private String fromUserId;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(String fromUserId) {
    this.fromUserId = fromUserId;
  }
}
