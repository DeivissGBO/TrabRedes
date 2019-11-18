package funcoes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Funcao implements Serializable {

    private int idFuncao;
    private String titulo;
    private String conteudo;
    private String nomePasta;
    private String a;
    private String caminhoArquivo;
    private int b; //gambiarra para funcionar a criação de diretorio, pois o contrutor ficaria parecido com outro ja existente
    private ArrayList<String> lista = new ArrayList<>();
   
    public Funcao(int idFuncao, String caminhoArquivo, int b, String a) {
    	this.idFuncao=idFuncao;
    	this.caminhoArquivo=caminhoArquivo;
    	this.b=b;
    	this.a=a;
    	
    	
    }
   
    public Funcao(int idFuncao, String nomePasta,int b) {
        this.idFuncao = idFuncao;
        this.nomePasta = nomePasta;
        this.b = b;
    }
    
    public Funcao(int idFuncao, String titulo, String conteudo) {
        this.idFuncao = idFuncao;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Funcao(int idFuncao, String titulo) {
        this.idFuncao = idFuncao;
        this.titulo = titulo;
    }

    public Funcao(String conteudo) {
        this.conteudo = conteudo;
    }

    public Funcao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public Funcao(ArrayList<String> lista) {
        this.lista = lista;
    }

    public int escreveArquivo(int idFuncao, String nomeArquivo, String corpoArquivo, String caminho) {
        BufferedWriter escreve;
        try {
            escreve = new BufferedWriter(new FileWriter(caminho + nomeArquivo + ".txt"));
            escreve.write(corpoArquivo);
            escreve.close();
            return 200;
        } catch (IOException ex) {
            return 404;
        }
    }

    public String leArquivo(String nomeArquivo, String endereco) throws FileNotFoundException, IOException {
        String conteudo;
        try (BufferedReader leitor = new BufferedReader(
                new FileReader(endereco + nomeArquivo + ".txt"))) {
            conteudo = leitor.readLine();
        }
        return conteudo;
    }

    public ArrayList<String> listagem(String endereco) {
        File file = new File(endereco);
        String[] lista = file.list();
        for (int i = 0; i < lista.length; i++) {
            this.lista.add(lista[i]);
        }
        return getListagem();
    }

    public void imprimeListagem() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    public void deletaArquivo(String caminho) {
        File file = new File(caminho + getTitulo()+ ".txt");
        file.delete();
    }

	public void criaDiretorio(String caminho) {
    	File file = new File(caminho+getNomePasta());
    	file.mkdirs();
    }
	
	public void enviaArquivo(String caminho) throws UnknownHostException, IOException {
		Socket cliente = new Socket ("localhost", 9800);
		ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
		FileInputStream file = new FileInputStream(caminho);
		
		
		
		
		
	}
    
    public String getNomePasta() {
		return nomePasta;
	}

	public void setNomePasta(String nomePasta) {
		this.nomePasta = nomePasta;
	}

    
    public ArrayList<String> getListagem() {
        return lista;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setLista(ArrayList<String> listagem) {
        this.lista = listagem;
    }
}
