package br.inatel.pos.mobile.scanip.to;

import java.io.Serializable;

public class VerifiquedIPTO implements Serializable {


	private static final long serialVersionUID = 1856279173028731354L;
	
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


