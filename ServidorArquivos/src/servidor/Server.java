package servidor;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import funcoes.*;

public class Server {

    static String endereco = ".\\src\\servidor\\";
    static ServerSocket serverSocket;
    static Socket clienteSocket;
    static Conexao conexao;
    static String mensagem;

    public static void main(String args[]) throws IOException, InterruptedException {

        new Server();
        do {
            Thread.sleep(2000);
            servidorEscutaControlador();
            if (connect()) {

            	Funcao funcaoRequisitada = (Funcao) conexao.recebe(clienteSocket);

                if (funcaoRequisitada.getIdFuncao() == 1) {
                    funcaoRequisitada.escreveArquivo(funcaoRequisitada.getIdFuncao(),
                            funcaoRequisitada.getTitulo(),
                            funcaoRequisitada.getConteudo(),
                            endereco);
                    Thread.sleep(1000);
                    
                }else if(funcaoRequisitada.getIdFuncao() == 3){
                    ArrayList<String> lista = funcaoRequisitada.listagem(endereco);
                    
                    Funcao funcaoRequisitadalist = new Funcao(lista);
                    conexao.envia(clienteSocket, funcaoRequisitadalist);
                    
                }else if (funcaoRequisitada.getIdFuncao() == 2){
                    funcaoRequisitada.deletaArquivo(endereco);
                    
                }else if (funcaoRequisitada.getIdFuncao() == 4){
                    String conteudo = funcaoRequisitada.leArquivo(funcaoRequisitada.getTitulo(), endereco);
                    
                    Funcao funcaoRequisitadacont = new Funcao(conteudo);
                    conexao.envia(clienteSocket, funcaoRequisitadacont);
                }else if (funcaoRequisitada.getIdFuncao()==5) {
                	funcaoRequisitada.criaDiretorio(endereco);
                }
                fechaConexao();
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

    static void servidorEscutaControlador() {
        try {
            serverSocket = new ServerSocket(9800);
            System.out.println("Server Escutando na porta: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            System.err.println("Não foi possível criar o controlador");
        }
    }

    static void fechaConexao() {
        try {
            clienteSocket.close();
            serverSocket.close();           
        } catch (Exception e) {
            System.err.println("Não encerrou a conexão corretamente" + e.getMessage());
        }
    }

}
