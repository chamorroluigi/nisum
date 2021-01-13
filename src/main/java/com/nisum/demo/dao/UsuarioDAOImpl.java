package com.nisum.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nisum.demo.model.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	
	@PersistenceContext
	private EntityManager em;

	
	@Override
	public void addUser(Usuario user) {
		em.persist(user);
		
	}

	@Override
	public List<Usuario> findAllUsers() {
		
		List<Usuario> users = em.createQuery("Select userp from Usuario userp", Usuario.class)
                .getResultList();
		
		return users;
	}

	@Override
	public Usuario findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findUserByEmail(String emailP) {
		List<Usuario> users = em.createQuery("Select userp from Usuario userp where email =:emailP", Usuario.class)
				.setParameter("emailP", emailP)
                .getResultList();
		
		return users;
	}

}
