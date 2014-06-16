package Architecture_Posting;

import GUI.*;
import java.util.*;
/**
 *
 * @author Thanh Le Quoc
 */

/**
 * PongHandler use to handle Pong message
 */
public class PongHandler extends Thread {

    public static Map pongTable;  //pong table     
    public static Map listPongTable;  //pong table     
    Pong pong;
    IPAddress pingIP;
    IPAddress ip;
    Ping pingMatch;
    Utils utils = new Utils();
    byte[] useIDOnlineToByte = LoginForm.currentUser.getIdUserLogin().getBytes();
    String userNameOnlineString = LoginForm.currentUser.getUserName();
    public static Vector<Pong> listPong = new Vector<Pong>();

    public PongHandler(IPAddress ip, Pong pong) {
        this.pong = pong;
        pong.setPongRespondIP(ip);
    }

    public static void initPongTable() {
        pongTable = new Hashtable(5000);
    }

    public static void listPongTable() {
        listPongTable = new Hashtable();
    }

    public void run() {
        if (!pongTable.containsKey(pong.getMessageID())) //check that postMessage is not already in table
        {
            pongTable.put(pong.getMessageID(), pong);
            NetworkManager.writeButOne(pong.getPongRespondIP(), pong);

            if (!listPongTable.containsKey(pong.getUserIDOnline())) {
                System.out.println("\n ### Server : Packet == PONG -- " + pong.getUserIDOnline());
                listPongTable.put(pong.getUserIDOnline(), pong);
                listPong.add(pong);
            }

            boolean isSuperPeer = utils.checkSuperPeer(pong.getIP(), pong.getPort());
            if (isSuperPeer) {
                String ipname = pong.getPongRespondIP().toString();
                int port = pong.getPongRespondIP().getPort();
                Host newhost = new Host(ipname, port);
                HostCache.addHost(newhost);
            }
        }
    }
}
