package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Segurado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToMany 
	@JoinTable(name="segurado_seguro", joinColumns= {@JoinColumn(name="id_segurado")},
	inverseJoinColumns= {@JoinColumn(name="id_seguro")})
	private List<Seguro> seguro = new ArrayList<Seguro>();
	
	
	private String nome;
	@Column(unique = true)
	private String cpf;
	private String rg;
	private String sexo;
	private String correntista;
	private String[] diasVisita;
	private String data_nas;
	private Date  data_cad;
	private Date data_alt;

	public Segurado() {
		super();
	}

	public Segurado(Integer id, String nome, String cpf, String rg, String sexo, String correntista, String identificacao_seguro, String[] diasVisita,
			String data_nas, Date data_cad, Date data_alt) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.sexo = sexo;
		this.correntista = correntista;
		this.diasVisita = diasVisita;
		this.data_nas = data_nas;
		this.data_cad = data_cad;
		this.data_alt = data_alt;
	}

	@Override
	public String toString() {
		return "Segurado [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", sexo=" + sexo
				+ ", correntista=" + correntista + ", diasVisita=" + diasVisita + ", data_nas=" + data_nas
				+ ", data_cad=" + data_cad + ", data_alt=" + data_alt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorrentista() {
		return correntista;
	}

	public void setCorrentista(String correntista) {
		this.correntista = correntista;
	}

	public String[] getDiasVisita() {
		return diasVisita;
	}

	public void setDiasVisita(String[] diasVisita) {
		this.diasVisita = diasVisita;
	}

	public String getData_nas() {
		return data_nas;
	}

	public void setData_nas(String data_nas) {
		this.data_nas = data_nas;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}

	public Date getData_alt() {
		return data_alt;
	}

	public void setData_alt(Date data_alt) {
		this.data_alt = data_alt;
	}

	public List<Seguro> getSeguro() {
		return seguro;
	}

	public void setSeguro(List<Seguro> seguro) {
		this.seguro = seguro;
	}	
}
