import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 5000; // Nuevo puerto
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor corriendo en el puerto " + port);

        while (true) {
            Socket client = server.accept();
            OutputStream output = client.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Obtener la hora actual
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            // Responder con la hora actual
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println("Content-Length: " + time.length());
            writer.println();
            writer.println(time);

            client.close();
        }
    }
}
