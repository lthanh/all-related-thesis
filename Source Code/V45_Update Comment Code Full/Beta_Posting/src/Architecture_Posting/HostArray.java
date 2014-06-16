package Architecture_Posting;

import GUI.AppGUI;
import java.net.*;
import java.util.*;
/**
 *
 * @author Thanh Le Quoc
 */

public class HostArray {

    private static Connection[] hosts;
    public static Vector<IPAddress> cacheConnection = new Vector<IPAddress>();  // store list of IP connection
    public static Vector<String> showListServer = new Vector<String>();
    public static String listPeerConnected;

    public static boolean isNull() {
        if (hosts == null) {
            return true;
        } else {
            return false;
        }
    }

    public static synchronized int getCount() {
        if (isNull()) {
            return 0;
        } else {
            return hosts.length;
        }
    }

    public static synchronized void addConnection(Connection c) {
        if (isNull()) {
            hosts = new Connection[1];
            hosts[0] = c;
            boolean checkConnection = checkConnection(cacheConnection, c.getIPAddress());
            if (checkConnection == true) {
                cacheConnection.add(c.getIPAddress());
                showListServer.add(showIPGUI(c.getIPAddress(), c.getTypeString()));
            }
            AppGUI.listServer.setListData(showListServer);

        } else if (!isLive(c)) {
            Connection[] temp = new Connection[hosts.length + 1];
            System.arraycopy(hosts, 0, temp, 0, hosts.length);
            temp[hosts.length] = c;
            hosts = temp;
            boolean checkConnection = checkConnection(cacheConnection, c.getIPAddress());
            if (checkConnection == true) {
                cacheConnection.add(c.getIPAddress());
                showListServer.add(showIPGUI(c.getIPAddress(), c.getTypeString()));
            }
            AppGUI.listServer.setListData(showListServer);
        }
    }

    public static synchronized void removeConnection(Connection c) {
        removeConnection(c.getIPAddress());
    }

    public static synchronized void removeConnection(IPAddress ip) {
        if (!(isNull()) && isLive(ip)) {
            Connection[] temp = new Connection[hosts.length - 1];
            int j = 0;
            for (int i = 0; i < hosts.length; i++) {
                if (ip.equals(hosts[i].getIPAddress())) {
                    continue;
                }
                temp[j] = hosts[i];
                j++;
            }
            hosts = temp;
            AppGUI.listServer.setListData(updateRemovedConnection(ip));
        }
    }

    public static synchronized Connection getConnection(int i) {
        if ((!isNull() && (i < getCount()))) {
            return hosts[i];
        } else {
            return null;
        }
    }

    public static synchronized Connection getConnection(IPAddress ip) {
        Connection c = null;
        for (int i = 0; i < hosts.length; i++) {
            if (ip.equals(hosts[i].getIPAddress())) {
                c = hosts[i];
            }
        }
        return c;
    }

    public static synchronized boolean isLive(String ipString) {
        if (!isNull()) {
            for (int i = 0; i < hosts.length; i++) {
                InetAddress inet = hosts[i].getSocket().getInetAddress();
                if ((ipString.equals(inet.getHostName())) || (ipString.equals(inet.getHostAddress()))) {
                    //          System.out.println(ipString + " ?= " + inet.getHostName());
                    //          System.out.println(ipString + " ?= " + inet.getHostAddress());
                    return true;
                }

            }
            return false;
        } else {
            return false;
        }
    }

    public static synchronized boolean isLive(Connection c) {
        return (isLive(c.getIPAddress()));
    }

    public static synchronized boolean isLive(IPAddress ip) {
        for (int i = 0; i < hosts.length; i++) {
            if (ip.equals(hosts[i].getIPAddress())) {
                return true;
            }
        }
        return false;
    }

    public static String updateAddedConnection(Connection c) {
        return showIPGUI(c.getIPAddress(), c.getTypeString());
    }

    public static Vector<String> updateRemovedConnection(IPAddress ip) {
        String removeIP = ip.toString();
        for (int i = 0; i < cacheConnection.size(); i++) {
            if (removeIP.equals(cacheConnection.get(i).toString())) {
                cacheConnection.remove(i);
                showListServer.remove(i);
            }
        }
        return showListServer;
    }

    public static boolean checkConnection(Vector<IPAddress> listConnect, IPAddress ip) {
        String checkIP = ip.toString();
        for (int i = 0; i < listConnect.size(); i++) {
            if (checkIP.equals(listConnect.get(i).toString())) {
                return false;
            }
        }
        return true;
    }

    public static String showIPGUI(IPAddress ip, String type) {
        Vector<String> peer = Preferences.ipSuperPeer;

        for (int i = 0; i < peer.size(); i++) {
            if (peer.get(i).contains(ip.toString())) {
                return "Super Peer - " + ip.toString() + ":" + ip.getPort();
            }
        }
        return type + ip.toString() + ":" + ip.getPort();
    }
}
