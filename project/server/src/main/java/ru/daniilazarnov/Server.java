package ru.daniilazarnov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(8888))
        {
            while (true)
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer =
                              new BufferedWriter(
                                      new OutputStreamWriter(
                                              socket.getOutputStream()));
                        BufferedReader reader =
                              new BufferedReader(
                                      new InputStreamReader(
                                              socket.getInputStream()))
            ) {
                    String request = reader.readLine();
                    System.out.println(request);
                    String response = null;
                    if (request.equals("Hello")) {
                        response = "Hello from server";
                    }
                    else if (request.equals("Buy")) {
                        response = "Good buy";
                    }
                    else {
                        response = "unknown request";
                    }
                    writer.write(response);
                    writer.newLine();
                    writer.flush();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
