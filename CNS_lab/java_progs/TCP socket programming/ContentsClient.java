import java.net.*;
import java.io.*;

public class ContentsClient {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 4000);
        System.out.print("Enter the file name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fname = reader.readLine();

        OutputStream ostream = sock.getOutputStream();
        PrintWriter writer = new PrintWriter(ostream, true);
        writer.println(fname);

        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));

        String str;
        while ((str = socketRead.readLine()) != null)
            System.out.println(str);
        sock.close();
        writer.close();
        socketRead.close();
        reader.close();
    }
}