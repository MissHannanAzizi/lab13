import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClientTranslateApplication {
    private static JFrame frame;
    private static JTextArea requestArea;
    private static JTextArea responseArea;

    public static void main(String[] args) {
        frame = new JFrame("Translation Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Set up inputPanel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        // Set up textLabel & textField
        JLabel textLabel = new JLabel("Text:");
        inputPanel.add(textLabel);
        JTextField textInput = new JTextField();
        inputPanel.add(textInput);

        // Set up languageLabel & textField
        JLabel languageLabel = new JLabel("Target Language:");
        inputPanel.add(languageLabel);
        JTextField languageInput = new JTextField();
        inputPanel.add(languageInput);

        // Set up button
        JButton translateButton = new JButton("Translate");
        inputPanel.add(translateButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Set up outputPanel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(2, 1));

        // Set up requestLabel
        JLabel requestLabel = new JLabel("Request Details:");
        outputPanel.add(requestLabel);

        // Set up requestArea
        requestArea = new JTextArea();
        requestArea.setEditable(false);
        JScrollPane requestScrollPane = new JScrollPane(requestArea);
        outputPanel.add(requestScrollPane);

        // Set up responseLabel
        JLabel responseLabel = new JLabel("Response Details:");
        outputPanel.add(responseLabel);

        // Set up responseArea
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        JScrollPane responseScrollPane = new JScrollPane(responseArea);
        outputPanel.add(responseScrollPane);

        frame.add(outputPanel, BorderLayout.CENTER);

        // Add ActionListener to translateButton
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textInput.getText();
                String language = languageInput.getText();

                sendTranslationRequest(text, language);

                textInput.setText("");
                languageInput.setText("");
            }
        });

        frame.setVisible(true);
    }

    private static void sendTranslationRequest(String text, String language) {
        try {
            Socket socket = new Socket("localhost", 1234);

            // Set up input and output streams with UTF-8 encoding
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            String userInput = text + " : " + language;
            out.println(userInput);

            String response = in.readLine();

            updateRequestDetails(userInput);
            updateResponseDetails(response);

            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateRequestDetails(String request) {
        requestArea.append(request + "\n");
    }

    private static void updateResponseDetails(String response) {
        responseArea.append(response + "\n");
    }
}
