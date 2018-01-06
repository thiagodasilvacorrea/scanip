package br.inatel.pos.mobile.scanip.ejb;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
public class ScanIpMessageSender 
{
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/scannipqueue")
	private Queue queue;
	
	//Divide os ips gerados em grupos de 10 e os envia para o mdb.
	public  void sendObjectMessage(ArrayList<String> objectips)
	{
		//recebe  o tamanho da lista recebida.
		int size = objectips.size();
		//Lista usada para dividir os ips recebidos
		ArrayList<String> splitedlist = new ArrayList<>();
		//valor que define quantos elementos tem em cada grupo.
	 final int maxsizearray = 10;
		try (
				Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession();
				MessageProducer producer = session.createProducer(queue);	
		) 
		{
			     
			for (int start = 0; start < size; start += maxsizearray) 
			{
		        int end = Math.min(start + maxsizearray, size);
		      
		        System.out.println( "Valor gerado pela lista  apos divisão "+objectips.subList(start, end));
		          splitedlist.addAll(objectips.subList(start, end));
		          
		        ObjectMessage objMessage = session.createObjectMessage();
		     	objMessage.setObject(splitedlist);
		     	System.out.println("enviando mensagem "+start);
				producer.send(objMessage);
				
				splitedlist.clear();
				
		   }
			     
		} catch (JMSException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	
	
}
