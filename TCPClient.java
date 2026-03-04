import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {

    public static void main(String[] args) throws Exception {

        String ip = "104.18.26.120";
        int port = 80;

        // If you MUST send exactly what you wrote, keep it like this:
        String request = "GET / HTTP/1.1\r\nHost: www.example.com\r\n\r\n";

        // (Recommended for easier "read all response" behaviour)
        // String request = "GET / HTTP/1.1\r\nHost: www.example.com\r\nConnection: close\r\n\r\n";

        System.out.println("TCPClient connecting to " + ip + ":" + port);
        System.out.println("Sending request:\n" + request);

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), 5000);
            socket.setSoTimeout(5000);

            // Send request
            OutputStream out = socket.getOutputStream();
            out.write(request.getBytes(StandardCharsets.US_ASCII));
            out.flush();

            // Read and print full response (until server closes or timeout)
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
