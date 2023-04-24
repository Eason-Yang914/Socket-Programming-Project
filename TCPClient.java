import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {

        boolean working = true;

        // get buffer from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // create new socket
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        //Date output
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        // Get the inputBuffer
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (working) {

            // print options
            System.out.println("0.Connect to the server\n"
                    + "1.Get the user list\n"
                    + "2.Send a message\n"
                    + "3.Get my messages\n"
                    + "4.Exit");
            //user chose which one
            System.out.println("Please enter one number:");
            String option = inFromUser.readLine();
            outToServer.writeBytes(option + "\n");

            //switch
            switch (option) {
                case "0":
                    System.out.println("Please enter your name: ");
                    String username = inFromUser.readLine();
                    outToServer.writeBytes(username + "\n");

                    System.out.println("Please enter your password: ");
                    String password = inFromUser.readLine();
                    outToServer.writeBytes(password + "\n");

                    String statusChecking = inFromServer.readLine();
                    System.out.println("From Server:" + statusChecking + '\n');
                    break;

                case "1":
                    System.out.println("All User List is:");
                    String allUser = inFromServer.readLine();
                    System.out.println(allUser);
                    break;

                case "2":
                    System.out.println("Please enter the receiver's username: ");
                    String receiver = inFromUser.readLine();
                    System.out.println("Enter the message you want to leave: ");
                    String message = inFromUser.readLine();

                    outToServer.writeBytes(receiver + "\n");
                    outToServer.writeBytes(message + "\n");
                    break;

                case "3":
                    System.out.println("Retrieving your messages...");
                    System.out.println("Please enter your username: ");
                    String user = inFromUser.readLine();
                    outToServer.writeBytes(user + "\n");

                    String messages = inFromServer.readLine();
                    System.out.println("Your messages: " + messages);
                    break;


                case "4":
                    clientSocket.close();

            }
        }
    }
}
