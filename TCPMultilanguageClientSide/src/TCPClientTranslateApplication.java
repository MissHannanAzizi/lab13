/*
 * CLIENT SIDE TO SENIDNG DATA TO SERVER SIDE
 * @author nurulhannan
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientTranslateApplication {
    public static void main(String[] args) {
        try {
            // Establish a connection with the server on the specified host and port
            Socket socket = new Socket("localhost", 1234);

            // Create input and output streams to communicate with the server
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connected to server.");

            while (true) {
                // Prompt the user to enter the text
                System.out.print("Enter text: ");
                String text = userInputReader.readLine();

                // Prompt the user to enter the target language
                System.out.print("Enter target language: ");
                String targetLanguage = userInputReader.readLine();

                // Prepare the user input to send to the server in the format "text : targetLanguage"
                String userInput = text + " : " + targetLanguage;
                out.println(userInput);

                // Receive the translated response from the server
                String response = in.readLine();
                System.out.println("Server response: " + response);

                // Prompt the user if they want to translate more text
                System.out.print("Do you want to translate more text? (yes/no): ");
                String choice = userInputReader.readLine();

                // Check the user's choice and break the loop if they choose "no"
                if (choice.equalsIgnoreCase("no")) {
                    break;
                }
            }

            // Close the input and output streams, and the socket
            userInputReader.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
