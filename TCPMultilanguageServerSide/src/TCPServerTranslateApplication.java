/*
 * SERVER SIDE TO REECEIVE TEXT DATA FROM THE CLIENT SIDE
 * @author nurulhannan
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerTranslateApplication {
    public static void main(String[] args) {
        try {
            // Create a server socket and listen on a specific port
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started and waiting for client...");

            // Accept incoming client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Create input and output streams to communicate with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                // Split the received line into text and target language
                String[] parts = line.split(":");
                String text = parts[0].trim();
                String targetLanguage = parts[1].trim();

                // Perform the translation using the TranslateText class
                String translation = TranslateText.translate(text, targetLanguage);

                // Send the translated response back to the client
                out.println("Translation: " + translation);
            }

            // Close the input and output streams, and the client socket
            in.close();
            out.close();
            clientSocket.close();

            // Close the server socket
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
