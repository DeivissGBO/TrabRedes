package controlador;


import java.net.*;

import funcoes.*;

public class Controlador {

    static ServerSocket serverSocket;
    static Socket clienteSocket;
    static Conexao conexao;
    
   
    static Socket server_Socket;
    static int servId;
   

    public static void main(String args[]) throws InterruptedException {

        new Controlador();
        servId = 2;
        do {
            Thread.sleep(2500);

            Socket servidor_conectado = controladorConectaServidor(9800);
            controladorEscutaCliente();
            

            if (connect()) {
            	Funcao funcao;
            	funcao = (Funcao) conexao.recebe(clienteSocket);
                conexao.envia(servidor_conectado, funcao);
                Thread.sleep(2000);
                funcao = (Funcao) conexao.recebe(servidor_conectado);
                conexao.envia(clienteSocket, funcao);

                controladorFechaConexao();
            }
        } while (true);
    }

    static boolean connect() {
        boolean ret;
        try {
            clienteSocket = serverSocket.accept();
            ret = true;
        } catch (Exception e) {
            System.err.println("Não fez conexão" + e.getMessage());
            ret = false;
        }
        return ret;
    }

    static Socket controladorConectaServidor(int a) {

        if (a == 1) {
            try {
                server_Socket = new Socket("localhost", 9800);
                System.out.println("Conexão com OmegaServer realizada");
                return server_Socket;
            } catch (Exception e) {

                System.err.println("Nao consegui conectar com servidor ...");
                return null;
            }
        }
        return null;
    }

    static void controladorEscutaCliente() {
        try {
            serverSocket = new ServerSocket(9600);
            System.out.println("Controlador Escutando na porta: " + serverSocket.getLocalPort());
        } catch (Exception e) {
            System.err.println("Não foi possível criar o controlador");
        }
    }

    static void controladorFechaConexao() {
        try {
            clienteSocket.close();
            serverSocket.close();           // fase de desconexão
        } catch (Exception e) {
            System.err.println("Não encerrou a conexão corretamente" + e.getMessage());
        }
    }

 
    
    
}
