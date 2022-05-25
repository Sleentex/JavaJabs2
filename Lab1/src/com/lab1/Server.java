package com.lab1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try  {
            server = new ServerSocket(4004);
            System.out.println("Сервер запущен!");

            while (true) {
                clientSocket = server.accept();

                try {
                    in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String line = in.readLine();
                    System.out.println(line);

                    out.write(getResult(line));
                    out.flush();

                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static String getResult(String line)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); ++i) {
            result.append(replaceChar(line.charAt(i)));
        }

        return result.toString();
    }

    public static String replaceChar(char l)
    {
        return switch (l) {
            case '0' -> "нуль";
            case '1' -> "один";
            case '2' -> "два";
            case '3' -> "три";
            case '4' -> "чотири";
            case '5' -> "п'ять";
            case '6' -> "шість";
            case '7' -> "сім";
            case '8' -> "вісім";
            case '9' -> "дев'ять";
            default -> String.valueOf(l);
        };
    }
}
