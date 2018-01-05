package br.inatel.pos.mobile.scanip.interfaces;

import br.inatel.pos.mobile.scanip.to.VerifiquedIPTO;

public interface ScanIp {

	void ScanNetwork(String ip, int maskcidr);

	VerifiquedIPTO[] listProducts(String ip);

}
