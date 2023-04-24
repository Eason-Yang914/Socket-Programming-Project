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
        //new hashmap store the leaved message
        HashMap<String, List<String>> messageMap = new HashMap<>();


        while (true) {
            ServerSocket welcomeSocket = new ServerSocket(6789);
            System.out.println("SERVER is running ... ");

            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            while (true) {
                String option = inFromClient.readLine();


                switch (option) {
                    case "0":
                        String clientUsername = inFromClient.readLine();
                        String clientPassword = inFromClient.readLine();
                        System.out.println("The user's option is: " + option);
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
                        System.out.println("The user's option is: " + option);
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

                        if (map1.containsKey(receiver)) {
                            List<String> messages = messageMap.getOrDefault(receiver, new ArrayList<>());
                            messages.add(message);
                            messageMap.put(receiver, messages);
                            outToClient.writeBytes("Message sent to " + receiver + "\n");
                            System.out.println("Message sent to " + receiver);
                        } else {
                            outToClient.writeBytes("Invalid receiver: " + receiver + "\n");
                            System.out.println("Invalid receiver: " + receiver);
                        }
                        break;

                    case "3":
                        System.out.println("The user's option is: " + option);
                        String user = inFromClient.readLine();
                        List<String> userMessages = messageMap.getOrDefault(user, new ArrayList<>());
                        if (!userMessages.isEmpty()) {
                            for (String userMessage : userMessages) {
                                outToClient.writeBytes(userMessages + "\n");
                                System.out.println(userMessages);
                            }
                        } else {
                            outToClient.writeBytes("No messages for " + user + "\n");
                            System.out.println("No messages for " + user);
                        }
                        break;


                    case "4": {
                        connectionSocket.close();
                        break;
                    }
                }
            }
        }
    }
}
