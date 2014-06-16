
package PeerAction;

import GUI.AppGUI;
import SuperPeerAction.PostObject;
import Architecture_Posting.Utils;
import static PostingService.PostHandler.recieveListPost;
import static PostingService.PostHandler.showListPost;

/**
 * PeerReceivePost use to show status in the Friends' status field
 * 
 * @author Thanh Le Quoc
 */
public class PeerReceivePost {
    
    public PeerReceivePost() {
    }
    
    // receive status and show in the Friends' status field
    public void receivePost(PostObject post) {
        if (!"".equals(post.getNamePost())) {
            recieveListPost.add(0, post);
            showListPost.add(0, Utils.formSHOWSTATUS(post.getNamePost(), post.getContentPost(), post.getCreatedDate()));
            AppGUI.inform(showListPost);
        }
    }
}
