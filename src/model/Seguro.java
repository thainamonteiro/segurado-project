package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Seguro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@ManyToMany(mappedBy = "seguro", fetch = FetchType.EAGER)
	private List<Segurado> segurado = new ArrayList<Segurado>();


	private String identificacao;
	private String valor;

	public Seguro() {
		super();
	}

	public Seguro(Integer id, String identificacao, String valor) {
		super();
		this.id = id;
		this.identificacao = identificacao;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", identificacao=" + identificacao + ", valor=" + valor + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
