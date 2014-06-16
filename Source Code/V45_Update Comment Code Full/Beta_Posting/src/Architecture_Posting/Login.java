package Architecture_Posting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Le Quoc
 */
public class Login {

    public static List<UserLoginObject> userLogin = new ArrayList<UserLoginObject>();
    public static String SHAREPATH = "C:\\Posting\\";

    /*
     * readFileUserID use to get list of user in the userLogin file
     */
    public static void readFileUserID() {
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader(SHAREPATH + "userLogin.txt"));
            String line;
            while ((line = fileIn.readLine()) != null) {
                UserLoginObject user = new UserLoginObject();
                String[] userNameID = line.split("~~");
                user.setIdUserLogin(userNameID[0]);
                user.setUserName(userNameID[1]);
                userLogin.add(user);
            }
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read preferences file");
        }
    }
}
