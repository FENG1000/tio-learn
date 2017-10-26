package cn.luoxi.common.packets;

/**
 * @author 夏智峰
 * @create 2017-10-26 15:05
 */
public class BaseBody {
  private Long time = System.currentTimeMillis();

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }
}
