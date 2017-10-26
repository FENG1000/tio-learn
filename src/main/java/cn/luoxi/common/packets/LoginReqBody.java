package cn.luoxi.common.packets;

/**
 * 登陆请求
 *
 * @author 夏智峰
 * @create 2017-10-26 15:14
 */
public class LoginReqBody extends BaseBody {
  private String loginName;
  private String passWord;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }
}
