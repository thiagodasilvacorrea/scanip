package br.inatel.pos.mobile.scanip.mdb;

import java.util.ArrayList;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


import br.inatel.pos.mobile.scanip.dao.VerifiquedIPDAO;
import br.inatel.pos.mobile.scanip.entities.VerifiquedIP;
import br.inatel.pos.mobile.scanip.iptools.Iptools;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",
								  propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",
								  propertyValue = "java:/jms/queue/scannipqueue"),
		@ActivationConfigProperty(propertyName = "maxSession",
								  propertyValue = "1")
})
public class ScanIpMDB implements MessageListener {

	@EJB
	private VerifiquedIPDAO dao;
    
	@Override
	public void onMessage(Message message) 
	{
		
		  
		try {
			   System.out.println("Validando tipo de mensagem");
			if (message instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) message;
				Object obj = objMessage.getObject();
				System.out.println("Mensagem é um objeto");
				@SuppressWarnings("unchecked")
				ArrayList<String> reseivedips = (ArrayList<String>) obj;
			      
				 for (int index2 = 0; index2 <reseivedips.size();index2++)
				 {
					 System.out.println(reseivedips.get(index2));
				 }
				 this.processMessage(reseivedips);
				
			}
			else
			{
				System.out.println("Não é um objeto");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void  processMessage(ArrayList<String> reseivedips)
	{
		
	        String statusativo = "Ativo"; 
		     String statusinativo = "Inativo";
			  
			try {
				
					System.out.println("quantidade de ips recebidos é = " + reseivedips.size());
					for (int index = 0; index < reseivedips.size(); index++) {
						  VerifiquedIP ips = new VerifiquedIP();
						
						ips.setVerifiquedip(reseivedips.get(index));
	
						System.out.println("Valor a ser gravado no banco = "+reseivedips.get(index));
	                boolean resultadoping = Iptools.execPing(ips.getVerifiquedip());
						if (resultadoping == true) {
							System.out.println("definindo status");
							ips.setIpstatus(statusativo);
							System.out.println("gravando no banco");
							dao.insert(ips);
						} 
						   if(resultadoping==false) 
						    {
							
							System.out.println("definindo status");
							ips.setIpstatus(statusinativo);
							System.out.println("gravando no banco");
							dao.insert(ips);
						  }
			
					} 
				
		   } 
			catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}





