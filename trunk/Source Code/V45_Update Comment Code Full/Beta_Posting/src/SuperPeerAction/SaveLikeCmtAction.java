package SuperPeerAction;

import Architecture_Posting.*;
import java.util.*;
import PostingService.*;

/**
 * SaveLikeCmtAction uses for super peers save like and comment message
 *
 * @author Thanh Le Quoc
 */
public class SaveLikeCmtAction {

    Utils utils = new Utils();
    boolean isFileStoring;

    public SaveLikeCmtAction() {
    }

    // use to save like message at super peer
    public void saveLikeSuperPeer(Like like) {
        long idPost = like.getIdPost();
        String idUserPost = like.getIdUserPost();
        String idUserLike = like.getIdUserLike();
        String nameLike = like.getNameLike();
        isFileStoring = utils.checkFileSharing(idUserPost + ".txt");

        boolean isLikeOfUser = serverCheckLikeCommentForPeer(Preferences.peerManageList, like, null);
        if (isLikeOfUser) {
            ReqRes_LikeCommentListObject likeComment = new ReqRes_LikeCommentListObject();
            likeComment = Preferences.readUserFile(like.getIdPost(), like.getIdUserPost());
            boolean isLike = checkNameLiked(like.getMessageID(), like.getIdUserLike(), likeComment);
            if ((likeComment.getIdUserLike().equals("") || isLike == true) && (isFileStoring)) {
                Preferences.likeWriteToFileSuperPeer(like.getLikeTypeString(like.getPayload()), idPost, like.getMessageID(), idUserPost, idUserLike, nameLike);
            }
        }
    }

    // use to save comment message at super peer
    public void saveCommentSuperPeer(Comment comment) {
        long idPost = comment.getIdPost();
        String idUserPost = comment.getIdUserPost();
        String idUserComment = comment.getIdUserComment();
        String nameComment = comment.getNameComment();
        String commentContent = comment.getComment();
        boolean isCommentOfUser = serverCheckLikeCommentForPeer(Preferences.peerManageList, null, comment);
        isFileStoring = utils.checkFileSharing(idUserPost + ".txt");

        if (isCommentOfUser) {
            ReqRes_LikeCommentListObject likeComment = new ReqRes_LikeCommentListObject();
            likeComment = Preferences.readUserFile(comment.getIdPost(), comment.getIdUserPost());
            boolean isComment = checkCommentID(comment.getMessageID(), likeComment);
            if (isComment && isFileStoring) {
                Preferences.commentWriteToFileSuperPeer(comment.getCommentTypeString(comment.getPayload()), idPost, comment.getMessageID(), idUserPost, idUserComment, nameComment, commentContent);
            }
        }
    }

    // user to check name liked or not on the status
    public boolean checkNameLiked(long likeID, String userIDLike, ReqRes_LikeCommentListObject likeComment) {
        if (likeComment.getIdLike().contains(String.valueOf(likeID))) {// || likeComment.getIdUserLike().contains(userIDLike)) {  // still like many times.
            return false;
        }
        return true;
    }

    // use to check commentID on the status
    public boolean checkCommentID(long commentID, ReqRes_LikeCommentListObject likeComment) {
        if (likeComment.getIdComment().contains(String.valueOf(String.valueOf(commentID)))) {
            return false;
        }
        return true;
    }

    // check like, comment for peer
    public boolean serverCheckLikeCommentForPeer(Vector<String> listPeerOrFriend, Like like, Comment comment) {
        String userPostID = "";
        if (like != null) {
            userPostID = like.getIdUserPost();
        }
        if (comment != null) {
            userPostID = comment.getIdUserPost();
        }
        isFileStoring = utils.checkFileSharing(userPostID + ".txt");
        for (int i = 0; i < listPeerOrFriend.size(); i++) {
            if (isFileStoring || userPostID.equals(listPeerOrFriend.get(i))) {
                return true;
            }
        }
        return false;
    }
}
