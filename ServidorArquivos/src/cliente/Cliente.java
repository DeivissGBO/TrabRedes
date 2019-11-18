package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import funcoes.*;
import funcoes.Conexao;

public class Cliente {

    static Scanner scanner = new Scanner(System.in);
    static Conexao conexao;
    static Socket serverSocket;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Cliente();
        menuOperacoes();
    }

    public static int exibeMenu() {
    	
    	//exibe "menu" com as funcoes do sistema
    	System.out.println("\n\n\n\n\n****************************");
        System.out.println("servidor de arquivos");
        System.out.println("**************************************");
        System.out.println("qual operaÃ§Ã£o deseja realizar:");
        System.out.println("1 - Criar um arquivo");
        System.out.println("2 - Excluir um arquivo");
        System.out.println("3 - Listar arquivos");
        System.out.println("4 - Ler um arquivo");
        System.out.println("5 - Criar um diretorio");
        System.out.println("6 - Enviar um arquivo");
        int idFuncao = scanner.nextInt(); //le qual comando ira realizar
        scanner.nextLine();//esvazia o buffer
        return idFuncao; //retorna o id do comando
    }

    public static void menuOperacoes() throws IOException, InterruptedException {
        boolean exit = false;
        do {
            Thread.sleep(2000);
            if (clienteConectaControlador() == true) {
            	//dependendo do id retornado entra em uma caso e executa uma funcao
                String nomeArquivo, nomePasta, caminhoArquivo;
                int idFuncao = exibeMenu();
                switch (idFuncao) {
                    case 1:
                        System.out.println("insira o nome que deseja dar ao arquivo: ");
                        nomeArquivo = scanner.nextLine();
                        System.out.println("Entre com o conteudo do arquivo que deseja salvar: ");
                        String conteudoArquivo = scanner.nextLine();
                        criarArquivo(idFuncao, nomeArquivo, conteudoArquivo);
                        break;
                        
                    case 2:
                        System.out.println("Escreva o nome do arquivo: ");
                        nomeArquivo = scanner.nextLine();
                        deletarArquivo(idFuncao, nomeArquivo);
                        break;

                    case 3:
                        listarArquivo(idFuncao);
                        break;
                    case 4:
                        System.out.println("Qual o nome do arquivo que você quer ler ?");
                        nomeArquivo = scanner.nextLine();
                        leArquivo(idFuncao, nomeArquivo);
                        break;
                    case 5:
                        System.out.println("Qual o nome do diretorio que você quer criar ?  ");
                        nomePasta = scanner.nextLine();
                        criaDiretorio(idFuncao, nomePasta);
                        break;
                    case 6:
                    	System.out.println("Qual o CAMINHO do arquivo que deseja enviar ?");
                    	 caminhoArquivo = scanner.nextLine();
                    	enviarArquivo(idFuncao, caminhoArquivo);
                    	break;
                    default:
                        System.out.println("Numero invalido");
                }
            }
        } while (exit != true);
    }

    static boolean clienteConectaControlador() {
        try {
        	//cria conexao com o controllar onde direciona para o server
            serverSocket = new Socket("localhost", 9800); 
          
            return true;
        } catch (IOException e) {
            System.err.println("Nao consegui conectar no controlador...");
            return false;
        }
    }
    
    static void enviarArquivo(int idFuncao, String nomeArquivo) throws InterruptedException, IOException{
    	Funcao enviarArquivo = new Funcao(idFuncao, nomeArquivo, 0, "a");
    	conexao.envia(serverSocket, enviarArquivo);
    	Thread.sleep(2000);
    	
    	
    }
    // Aqui é criado objetos Funcao cada um com as informações necessarias para realizar a funcao desejada
    static void criarArquivo(int idFuncao, String nomeArquivo, String conteudoArquivo) throws InterruptedException {
    	Funcao criarArquivo = new Funcao(idFuncao, nomeArquivo, conteudoArquivo);
        conexao.envia(serverSocket, criarArquivo);
        Thread.sleep(1000);
    }

    static void listarArquivo(int idFuncao) throws InterruptedException {
    	Funcao listagem = new Funcao(idFuncao);
        conexao.envia(serverSocket, listagem);
        Thread.sleep(1000);
        listagem = (Funcao) conexao.recebe(serverSocket);
        listagem.imprimeListagem();
    }

    static void deletarArquivo(int idFuncao, String nomeArquivo) throws InterruptedException {
        Funcao deleta = new Funcao(idFuncao, nomeArquivo);
        conexao.envia(serverSocket, deleta);
    }
    
    static void criaDiretorio(int idFuncao, String nomePasta) throws InterruptedException {
        Funcao criaDiretorio = new Funcao(idFuncao, nomePasta,0);
        conexao.envia(serverSocket, criaDiretorio);
        
    
    }

    static void leArquivo(int idFuncao, String nomeArquivo) throws InterruptedException {
    	Funcao le = new Funcao(idFuncao, nomeArquivo);
        conexao.envia(serverSocket, le);
        Thread.sleep(1000);
        le = (Funcao) conexao.recebe(serverSocket);
        System.out.println("Conteudo do arquivo selecionado: ");
        System.out.println(le.getConteudo());
    }

}
