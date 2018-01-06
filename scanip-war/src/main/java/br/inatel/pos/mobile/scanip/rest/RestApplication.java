package br.inatel.pos.mobile.scanip.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.pos.mobile.scanip.impl.ScanIpServiceImpl;




@ApplicationPath("/app")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(ScanIpServiceImpl.class);
		return classes;
	}

}
