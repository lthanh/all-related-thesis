package Architecture_Posting;

import PeerAction.CheckUserOnlineAction;
/**
 *
 * @author Thanh Le Quoc
 */

/**
 * Pinger use to send Ping message in periodic time
 */
public class Pinger extends Thread {

    static Ping myping;
    IPAddress ip;
    CheckUserOnlineAction check = new CheckUserOnlineAction();

    public void run() {
        while (true) {
            try {
                sleep(Preferences.PINGER_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }

            check.checkUserOnline();
            myping = new Ping();
            NetworkManager.writeToAll(myping);
        }
    }
}