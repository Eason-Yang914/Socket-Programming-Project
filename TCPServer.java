import java.io.*;
import java.net.*;
import java.util.*;

class TCPServer {
    public static void main(String[] argv) throws Exception {
        // create new hashMap
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("Alice", "1234");
        map1.put("Bob", "5678");
        List<String> userNames = new ArrayList<String>(map1.keySet());

        while (true) {
            ServerSocket welcomeSocket = new ServerSocket(6789);
            System.out.println("SERVER is running ... ");

            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            while (true) {
//                Socket connectionSocket = welcomeSocket.accept();
//                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                String option = inFromClient.readLine();


                switch (option) {
                    case "0":
                        String clientUsername = inFromClient.readLine();
                        String clientPassword = inFromClient.readLine();
                        System.out.println("The user's option is:" + option);
                        System.out.println("UserName is: " + clientUsername);
                        System.out.println("UserPassword: " + clientPassword);
                        if (map1.containsKey(clientUsername) && map1.containsValue(clientPassword)) {
                            System.out.println("Access Granted");
                            //System.out.println(inFromServer);
                            outToClient.writeBytes("Access Granted\n");
                        } else {
                            System.out.println("Access Denied – Username/Password Incorrect");
                            outToClient.writeBytes("Access Denied – Username/Password Incorrect\n");
                        }

                        break;
                    case "1":
//                        System.out.println("The option is:" + option);
//                        outToClient.writeBytes("Alice Bob\n");
//                        System.out.println("Alice Bob");
//                        break;
                        System.out.println("The user's option is:" + option);
                        String userList = String.join(" ", userNames);
                        outToClient.writeBytes(userList + "\n");
                        System.out.println("All User List is: " + userList);
                        break;

                    case "2":
                        System.out.println("The user's option is:" + option);
                        String receiver = inFromClient.readLine();
                        System.out.println("The receiver is " + receiver);
                        String message = inFromClient.readLine();
                        System.out.println("The message is " + message);
                        break;

                    case "3":
                        System.out.println("The user's option is:" + option);
                        outToClient.writeBytes("\n");
                        break;

                    case "4" : {
                        connectionSocket.close();
                        break;
                    }
                }
            }
        }
    }
}
