
package br.inatel.pos.mobile.scanip.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.pos.mobile.scanip.to.VerifiquedIPTO;

@Path("/poller")
public interface ScanIpService {

	@GET
	@Path("/start/{IP}/{Mask}")
	@Produces(MediaType.APPLICATION_JSON)
	void ScanNetwork(@PathParam("IP") String IP,@PathParam("Mask") int Mask);

	@GET
	@Path("/status/{IP}")
	@Produces(MediaType.APPLICATION_JSON)
	VerifiquedIPTO[] listIps(@PathParam("IP") String IP);

}
