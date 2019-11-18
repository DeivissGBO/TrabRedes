package funcoes;

import java.net.*;
import java.io.*;

public class Conexao {

    public static void envia(Socket socket, Object obj) {
        ObjectOutputStream out;
        try {
            
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
        } catch (Exception e) {
            System.err.println("Exceção no OutputStream");
        }
    }

    public static Object recebe(Socket socket) {
        ObjectInputStream in;
        Object obj = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            obj = in.readObject();
        } catch (Exception e) {
            System.err.println("Exceção no InputStream: " + e);
        }
        return obj;
    }
}