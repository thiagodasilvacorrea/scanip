package br.inatel.pos.mobile.scanip.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import br.inatel.pos.mobile.scanip.api.ScanIpService;
import br.inatel.pos.mobile.scanip.interfaces.ScanIpRemote;
import br.inatel.pos.mobile.scanip.to.VerifiquedIPTO;

@RequestScoped
public class ScanIpServiceImpl implements ScanIpService {

	
	@EJB(lookup = "java:app/scanip-ejb-1.0.0-SNAPSHOT/ScanIpBean!br.inatel.pos.mobile.scanip.interfaces.ScanIpRemote")
	private ScanIpRemote scanIpBean;


	@Override
	public VerifiquedIPTO[] listIps(String ip) {
		
		return scanIpBean.listProducts(ip);
	}


	@Override
	public void ScanNetwork(String IP, int Mask) {
		try 
		{
			  System.out.println("iciando scaneaento de ip");
			  System.out.println(IP);
			  System.out.println(Mask);
			scanIpBean.ScanNetwork(IP, Mask);
		} 
		catch (Exception e) 
		{
			System.out.println("erro  scaneaento de ip");
			e.printStackTrace();
		}
		
	}


	
	

	
	

	

}
