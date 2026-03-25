import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws IOException {

        int port = 18080; // IMPORTANT: change to 18080

        System.out.println("Opening server on port " + port);
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) { // LOOP FOREVER
            System.out.println("Waiting for connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());

            // Read HTTP request (first line)
            String requestLine = reader.readLine();
            System.out.println("Request: " + requestLine);

            // Read remaining headers (until blank line)
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            // Respond properly (HTTP!)
            String body = "<html><body>webpage.html</body></html>";

            String response =
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + body.length() + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    body;

            writer.write(response);
            writer.flush();

            clientSocket.close();
            System.out.println("Connection closed\n");
        }
    }
}
