import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7778);
        while (true) {
            try {
                Socket socket = ss.accept();
                String linuxCommandResult = "";
                Process p = Runtime.getRuntime().exec("bash t.sh");

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((linuxCommandResult = stdInput.readLine()) != null) {

                    OutputStream outputStream = socket.getOutputStream();
                    // create an object output stream from the output stream so we can send an object through it
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(Double.valueOf(linuxCommandResult));
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }


    }
}