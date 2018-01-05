Segue dados para utilização da aplicação:

metodo :GET

url : http://127.0.0.1:8080/scanip/api/poller/start/{IP}/{MASK}

exemplo : http://127.0.0.1:8080/scanip/api/poller/start/192.168.50.0/28

Finalidade: Gera a faixade ip de acordo com o ip informado e a mascara na notação cidr.

Resultados e log no console do wildfly.

metodo :GET

url : http://127.0.0.1:8080/scanip/api/poller/status/{IP}

exemplo : http://127.0.0.1:8080/scanip/api/poller/start/192.168.50.0/28

Finalidade: |Retorna as informações  do ip solicitado

Resultados  são retornados para o usuario na formade jsom.
Log no console do wildfly.

Dados do banco e das filas:

O arquivo do banco se encontra na pasta sql na raiz do projeto.
Basta importar o arquivo novamente no postgresql.

Segue configurações dafila e do connection factory no wildfly 10.
dados das fila:
Name:scannipQueue
JNDI Names:java:/jms/queue/scannipqueue
Durable?:false

Connection factory:

Name:InVmConnectionFactory
JNDI Name:java:/ConnectionFactory
Connectors:in-vm
Group ID:
Failover Initial?:false
Thread Pool Max:30
Transaction Batch Size:1048576
Global Pools?:true
