import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    // Command that we want to execute in terminal
    private final static String command = "bash GetTemperature.sh";

    public static void main(String[] args) throws IOException {
        // Creating server socket
        ServerSocket serverSocket = new ServerSocket(7778);
        // Infinite loop
        while (true) {
            try {
                // Accepting connection
                Socket socket = serverSocket.accept();
                String linuxCommandResult = "";
                // Executing command
                Process p = Runtime.getRuntime().exec(command);
                // Getting command output and sending it to client
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((linuxCommandResult = stdInput.readLine()) != null) {
                    OutputStream outputStream = socket.getOutputStream();
                    // Create an object output stream from the output stream so we can send an object through it
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    // Convert to double because we should get string like "40.0"
           
                    objectOutputStream.writeObject(Double.valueOf(linuxCommandResult));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
