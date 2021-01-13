package com.nisum.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.demo.dao.UsuarioDAO;
import com.nisum.demo.model.Usuario;

@Service
public class UsuarioService {
	
	
	@Autowired
	UsuarioDAO userDao;
	
	
	@Transactional
	public void addUser(Usuario userp)
	{
		userDao.addUser(userp);
	}
	

	@Transactional
	public List<Usuario> getUsers(){
		
		return userDao.findAllUsers();
	}

	
	@Transactional
	public List<Usuario> getUsersByEmail(String email){
		
		return userDao.findUserByEmail(email);
	}

	
}
