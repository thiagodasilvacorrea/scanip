package br.inatel.pos.mobile.scanip.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "verifiquedip")
@SequenceGenerator(name = "seq_verifiquedip", sequenceName = "seq_verifiquedip", allocationSize = 1)
public class VerifiquedIP {

	@Id
	@GeneratedValue(generator = "seq_verifiquedip", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String verifiquedip;
	private String ipstatus;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVerifiquedip() {
		return verifiquedip;
	}
	public void setVerifiquedip(String verifiquedip) {
		this.verifiquedip = verifiquedip;
	}

	public String getIpstatus() {
		return ipstatus;
	}
	public void setIpstatus(String ipstatus) {
		this.ipstatus = ipstatus;
	}

}
