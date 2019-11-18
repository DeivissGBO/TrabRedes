package servidor;
import java.io.Serializable;
import java.util.Date;


public class Arquivo {

	private String nome;
	private byte[]conteudo;
	private String diretorioDestino;
	private Date dataUpload;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getConteudo() {
		return conteudo;
	}
	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}
	public String getDiretorioDestino() {
		return diretorioDestino;
	}
	public void setDiretorioDestino(String diretorioDestino) {
		this.diretorioDestino = diretorioDestino;
	}
	public Date getDataUpload() {
		return dataUpload;
	}
	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}
	
	
}
