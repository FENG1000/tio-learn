package cn.luoxi.common;

/**
 * 一般生产项目中，都需要定义一个这样的SessionContext，用于保存连接的会话数据
 * @author 夏智峰
 * @create 2017-10-26 15:36
 */
public class ShowSessionContext {
  private String token = null;
  private String userId = null;
  public ShowSessionContext() {
  }
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
