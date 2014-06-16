package Architecture_Posting;
/**
 *
 * @author Thanh Le Quoc
 */

/**
 * UserLoginObject is object of user who are logging in.
 */
public class UserLoginObject {

    public UserLoginObject() {
    }
    public String idUserLogin;
    public String userName;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(String idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
