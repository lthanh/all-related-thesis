package Architecture_Posting;

/*
 * Friends object use to get list of Friend in the listFriendFile in term of object
 * 
 * @author Thanh Le Quoc
 */
public class Friends extends UserLoginObject {

    public Friends() {
    }
    private int countOffline;
    private String checkFriendsGroup;

    public int getCountOffline() {
        return countOffline;
    }

    public void setCountOffline(int countOffline) {
        this.countOffline = countOffline;
    }

    public String getCheckFriendsGroup() {
        return checkFriendsGroup;
    }

    public void setCheckFriendsGroup(String checkFriendsGroup) {
        this.checkFriendsGroup = checkFriendsGroup;
    }
}
