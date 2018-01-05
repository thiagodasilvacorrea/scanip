package br.inatel.pos.mobile.scanip.ejb;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import br.inatel.pos.mobile.scanip.dao.VerifiquedIPDAO;
import br.inatel.pos.mobile.scanip.interfaces.ScanIpLocal;
import br.inatel.pos.mobile.scanip.interfaces.ScanIpRemote;
import br.inatel.pos.mobile.scanip.iptools.Iptools;
import br.inatel.pos.mobile.scanip.to.VerifiquedIPTO;



@Stateless
@Local( ScanIpLocal.class)
@Remote( ScanIpRemote.class)
public class ScanIpBean implements ScanIpLocal,ScanIpRemote {

	@EJB
	private ScanIpMessageSender messagesender;
	@EJB
	private VerifiquedIPDAO dao;
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/scannipqueue")
	private Queue queue;

	@Override
	public VerifiquedIPTO[] listProducts(String ip) 
	{

		return dao.findAll(ip)
				.stream()
				.map(v -> {
					VerifiquedIPTO to = new VerifiquedIPTO();
					to.setId(v.getId());
					to.setVerifiquedip(v.getVerifiquedip());
					to.setIpstatus(v.getIpstatus());
					return to;
				}).collect(Collectors.toList())
				.toArray(new  VerifiquedIPTO[0]);
	}

	@Override
	public void ScanNetwork(String ip, int maskcidr) 
	{
		try {
			   System.out.println("Acessou metodo ScanNetwork");
			   
			   ArrayList<String> objectips = new ArrayList<>();
			   VerifiquedIPTO to = new VerifiquedIPTO();
			   
			   System.out.println("iniciando geração de ip");
			   
		       int length =	Iptools.generateIps(ip,maskcidr).length; 
	
	       for (int i =0;i<length;i++)
	       { 
	    	 
	    	 to.setVerifiquedip(Iptools.generateIps(ip,maskcidr)[i]);
	    	   System.out.println("ips gerados"+to.getVerifiquedip());
	    	   objectips.add(to.getVerifiquedip());

	       }
	      
	       
	       System.out.println("enviando dados ao mesagesender");  
		     messagesender.sendObjectMessage(objectips);
		}
		  
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	

	
	




	
	

}









