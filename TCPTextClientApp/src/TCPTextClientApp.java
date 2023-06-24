import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPTextClientApp {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12356;

    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server: " + SERVER_HOST + ":" + SERVER_PORT);

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send the text to the server
            String text = "I love Western Food";
            out.println(text);
            System.out.println("Text sent to server: " + text);

            // Receive the word count from the server
            int wordCount = Integer.parseInt(in.readLine());

            // Display the word count
            System.out.println("Number of words: " + wordCount);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
