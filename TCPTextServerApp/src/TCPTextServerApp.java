import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTextServerApp {

    private static final int SERVER_PORT = 12356;

    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started and listening on port " + SERVER_PORT);

            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Set up input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the text from the client
                String text = in.readLine();
                System.out.println("Received text from client: " + text);

                // Calculate the word count
                int wordCount = CalculateLength.countWords(text);

                // Send the word count to the client
                out.println(wordCount);
                System.out.println("Sent word count to client: " + wordCount);

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
