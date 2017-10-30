package cn.luoxi.common;

import org.tio.core.intf.Packet;

/**
 * @author 夏智峰
 * @create 2017-10-26 14:45
 */
public class ShowPacket extends Packet {
  private byte type;
  private byte[] body;
  //初始化Packet里的值
  public ShowPacket() {
    super();
  }

  public ShowPacket(byte type, byte[] body) {
    super();
    this.type = type;
    this.body = body;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public byte[] getBody() {
    return body;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }
}
