package Arquivo;

import java.io.Serializable;
import java.util.Date;

public class Arquivo {
	
	private static final long serialVersion=1L;
	
	private String nome;
	private byte[] conteudo;
	private transient long tamanho;
	private transient Date dataUpload;
	private transient String ipDestino;
	private transient String portaDestino;
	private String diretorioDestino;
	
	
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
	public long getTamanho() {
		return tamanho;
	}
	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}
	public Date getDataUpload() {
		return dataUpload;
	}
	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}
	public String getIpDestino() {
		return ipDestino;
	}
	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}
	public String getPortaDestino() {
		return portaDestino;
	}
	public void setPortaDestino(String portaDestino) {
		this.portaDestino = portaDestino;
	}
	public String getDiretorioDestino() {
		return diretorioDestino;
	}
	public void setDiretorioDestino(String diretorioDestino) {
		this.diretorioDestino = diretorioDestino;
	}
	public static long getSerialversion() {
		return serialVersion;
	}
	
	

}
