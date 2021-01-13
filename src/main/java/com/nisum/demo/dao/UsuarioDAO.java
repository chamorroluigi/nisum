package com.nisum.demo.dao;

import java.util.List;

import com.nisum.demo.model.Usuario;

public interface UsuarioDAO {

	public void addUser(Usuario user);
	public List<Usuario> findAllUsers();
	public Usuario findUserById(int id);
	public List<Usuario> findUserByEmail(String email);

	
}
