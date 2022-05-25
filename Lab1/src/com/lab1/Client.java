package com.lab1;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                while (true) {
                    clientSocket = new Socket("localhost", 4004);

                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in     = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out    = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("Введіть рядок з букв і цифр:");

                    String line = reader.readLine();

                    out.write(line + "\n");
                    out.flush();

                    String serverLine = in.readLine();
                    System.out.println(serverLine);
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
