import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    public TCPClient() {}

    public static void main(String[] args) throws IOException {

	// IP Addresses will be discussed in detail in lecture 4
	String IPAddressString = "104.18.26.120";
	InetAddress host = InetAddress.getByName(IPAddressString);

	// Port numbers will be discussed in detail in lecture 5
	int port = 80;
	String request = "GET / HTTP/1.1\r\nHost: www.example.com\r\n\r\n";

	// This is where we create a socket object
	// That creates the TCP conection
	System.out.println("TCPClient connecting to " + ip + ":" + port);
	System.out.println("Sending request:\n" + request);	
	
	try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), 5000);
            socket.setSoTimeout(5000);

            // Send request
            OutputStream out = socket.getOutputStream();
            out.write(request.getBytes(StandardCharsets.US_ASCII));
            out.flush();

            // Read and print full response
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
    }
}