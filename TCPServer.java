import java.io.*; 
import java.net.*;
import java.util.*;
class TCPServer {
   public static void main(String argv[]) throws Exception
   {
      String clientUsername;
      String clientPassword;
      // create new hashMap
      HashMap<String, String> map1 = new HashMap<>();
      map1.put("Yang","12345");
      map1.put("Liu","23456");
      map1.put("Quincy","34567");

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
         } else {
            System.out.println("Access Denied – Username/Password Incorrect");
         }


         //capitalizedSentence = clientSentence.toUpperCase() + '\n'; outToClient.writeBytes(capitalizedSentence);
      } 
   }
}
