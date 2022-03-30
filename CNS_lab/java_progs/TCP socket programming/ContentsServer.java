import java.net.*;
import java.io.*;

public class ContentsServer {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server is ready for connection.");
        Socket sock = sersock.accept();
        System.out.println("Connection is successful and waiting for client requests.");

        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();

        BufferedReader contentRead = new BufferedReader(new FileReader(fname));

        OutputStream ostream = sock.getOutputStream();
        PrintWriter writer = new PrintWriter(ostream, true);

        String str;
        while ((str = contentRead.readLine()) != null)
            writer.println(str);

        sersock.close();
        contentRead.close();
        sock.close();
        writer.close();
    }
}
