import java.io.*; 
import java.net.*;
import java.util.*;
class TCPServer {
   public static void main(String argv[]) throws Exception
   {
      String clientUsername;
      String clientPassword;
      String clientMessage;
      // create new hashMap
      HashMap<String, String> map1 = new HashMap<>();
      map1.put("Alice","1234");
      map1.put("Bob","5678");

      ServerSocket welcomeSocket = new ServerSocket(6789); 
      System.out.println("SERVER is running ... ");

      while(true) {
         Socket connectionSocket = welcomeSocket.accept();
         BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
         DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

         clientUsername = inFromClient.readLine();
         clientPassword = inFromClient.readLine();
         System.out.println("UserName is: " + clientUsername);
         System.out.println("UserPassword: " + clientPassword);


         if(map1.containsKey(clientUsername) && map1.containsValue(clientPassword)){
            System.out.println("Access Granted");
            //System.out.println(inFromServer);
            outToClient.writeBytes("Access Granted\n");
         } else {
            System.out.println("Access Denied – Username/Password Incorrect");
            outToClient.writeBytes("Access Denied – Username/Password Incorrect\n");
         }

//         if (clientMessage.equals("get_user_list")) {
//            // Send list of usernames to client
//            StringBuilder userListBuilder = new StringBuilder();
//            for (String username : map1.keySet()) {
//               userListBuilder.append(username);
//               userListBuilder.append(",");
//            }
//            String userListString = userListBuilder.toString();
//            outToClient.writeBytes(userListString + "\n");
//         }



         //capitalizedSentence = clientSentence.toUpperCase() + '\n'; outToClient.writeBytes(capitalizedSentence);
      } 
   }
}
