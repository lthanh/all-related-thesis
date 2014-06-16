************ Posting Message Service **************

@Author: Thanh Le Quoc
HoChiMinh City International University 
@Version: 0.0.1
***************************************************

I) Requirements:
 - Install Java JDK 1.7 or higher
 - Turn off Window Firewall
 - Require at least 2 devices, 1 for Super peer and 1 for peer (recommend 2 for Super peers and 1 for peer)
 
II) Step-by-step to run the service

 1) Bring Posting folder to C hard disk such that C:/Posting/		(because there are 4 main files of database for this service in the folder that need to match with the path "C:/Posting/" : userLogin, preferences, listPeerSPManage, listFriendPeer . And user's social data is also stored at this folder)
 2) Configure IP in the preference file such that "Host" line in the file is IP of at least one Super peer. If there are many Super peer, insert new "Host" line.
 3) Create new project and choose LoginForm is the main file to run this service.
 4) Run the project and login with user name as in the userLogin file (Ex, Quoc Thanh)
 5) Enjoy the service.

Notice: Currently, the service supports 5 Super peer name : Server1 to Server5. So, you have to login with one of them for Super Peer.