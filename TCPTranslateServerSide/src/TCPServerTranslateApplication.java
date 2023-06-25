import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TCPServerTranslateApplication {
    // Track the number of requests made to the server
    private static int requestCount = 0;
    
    // Store the details of each request and the corresponding response
    private static Map<Integer, String> requestDetails = new HashMap<>();
    private static Map<Integer, String> responseDetails = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Create a server socket and listen for client connections
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started and waiting for client...");

            while (true) {
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Set up input and output streams with UTF-8 encoding
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);

                String line;
                while ((line = in.readLine()) != null) {
                    // Increment the request count and assign a unique request ID
                    requestCount++;
                    int requestId = requestCount;

                    // Extract the text and target language from the received line
                    String[] parts = line.split(":");
                    String text = parts[0].trim();
                    String targetLanguage = parts[1].trim();

                    // Translate the text to the target language
                    String translation = TranslateText.translate(text, targetLanguage);
                    String response = "Translation: " + translation;

                    // Send the response back to the client
                    out.println(response);

                    // Store the request and response details
                    requestDetails.put(requestId, line);
                    responseDetails.put(requestId, response);
                }

                // Close the input and output streams and the client socket
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the total number of requests made to the server
    public static int getRequestCount() {
        return requestCount;
    }

    // Get the details of each request
    public static Map<Integer, String> getRequestDetails() {
        return requestDetails;
    }

    // Get the details of each response
    public static Map<Integer, String> getResponseDetails() {
        return responseDetails;
    }
}
