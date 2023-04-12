import java.io.*; 
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TCPClient {
   public static void main(String argv[]) throws Exception 
   {
      String username;
      String password;
      String statusChecking;
      int userOption;
      //String modifiedSentence;
      // Get the buffer from the reader
//      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//      // create new socket
//      Socket clientSocket = new Socket("127.0.0.1", 6789);
//      //Date output
//      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//      // Get the inputBuffer
//      BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
      boolean working = true;
      //HashMap<String, String> userMap = new HashMap<>();

      while(working){

         BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
         // create new socket
         Socket clientSocket = new Socket("127.0.0.1", 6789);
         //Date output
         DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
         // Get the inputBuffer
         BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
         // print options
         System.out.println("0.Connect to the server\n"
                              + "1.Get the user list\n"
                              + "2.Send a message\n"
                              + "3.Get my messages\n"
                              + "4.Exit");
         //user chose which one
         System.out.println("Please enter one number:");
         Scanner input = new Scanner(System.in);
         userOption = input.nextInt();

         if(userOption == 0) {
            System.out.println("Please enter your name: ");
            username = inFromUser.readLine();
            outToServer.writeBytes(username + '\n');

            System.out.println("Please enter your password: ");
            password = inFromUser.readLine();
            outToServer.writeBytes(password + '\n');

            statusChecking = inFromServer.readLine();
            System.out.println("From Server:"+ statusChecking+'\n');

            
         } else if (userOption == 1){
            outToServer.writeBytes("get_user_list\n");

            // Receive list of usernames from server
            String userListString = inFromServer.readLine();
            String[] userList = userListString.split(",");

            // Print list of usernames to user
            System.out.println("List of usernames:");
            for (String userName : userList) {
               System.out.println(userName);
            }

         } else if (userOption == 2){

         } else if (userOption == 3){

         } else if (userOption == 4){
            clientSocket.close();
         }
      }
      //sentence = inFromUser.readLine();
      //outToServer.writeBytes(sentence + '\n');
      //username = inFromServer.readLine();
      //password = inFromServer.readLine();
      //System.out.println("FROM SERVER: " + modifiedSentence);
      //clientSocket.close();
   } 
}
