package br.inatel.pos.mobile.scanip.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.pos.mobile.scanip.entities.VerifiquedIP;

@Stateless
public class VerifiquedIPDAO {

	@PersistenceContext(unitName = "scannipdb-DS")
	private EntityManager em;

	public void insert(VerifiquedIP verifiquedip) {
		em.persist(verifiquedip);
	}

	@SuppressWarnings("unchecked")
	public List<VerifiquedIP> findAll(String ip) {
		return em.createQuery("SELECT v from  VerifiquedIP v where v.verifiquedip = :ip")
				.setParameter("ip",ip).getResultList();
	}

}
