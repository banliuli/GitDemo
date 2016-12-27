package dataSql;

/**
 * Created by Administrator on 2016/12/12.
 */
public class UserData {
    private String username;//用户名
    private String password;//用户密码
    public int pwdresetFlag=0;//
    private int userId;

    //获取用户名
    public String getUsername(){
        return username;
    }
    //设置用户名
    public void setUsername(String username){
        this.username=username;
    }
    //获取用户密码
    public String getPassword(){
        return password;
    }
    //设置用户密码
    public  void setPassword(String password){//输入用户密码
        this.password=password;
    }
    //获取用户id
    public int getUserId() {                   //获取用户ID号
        return userId;
    }
    //设置用户id
    public void setUserId(int userId) {       //设置用户ID号
        this.userId = userId;
    }
    public UserData(String username, String password){
        super();
        this.username=username;
        this.password=password;
    }
}
