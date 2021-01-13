package com.nisum.demo.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nisum.demo.bean.RespuestaBean;
import com.nisum.demo.bean.TelefonoBean;
import com.nisum.demo.bean.UsuarioBean;
import com.nisum.demo.model.Telefono;
import com.nisum.demo.model.Usuario;
import com.nisum.demo.services.UsuarioService;
import com.nisum.demo.util.JwtTokenUtil;

@Controller
public class UserController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioService userService;
	
	@GetMapping(value = "/list")
	@ResponseBody
	public List<Usuario> userDetails() {
        
		List<Usuario> userDetails = userService.getUsers();
		
		
		
		
		
		userDetails.forEach(item -> System.out.println("Nombre "+item.getName()));
		
		
		
		
		//return new ResponseEntity<List<Usuario>>(userDetails, HttpStatus.OK);
		
		return userDetails;
	}
	
	
	@PostMapping(path= "/addUsuario", consumes = "application/json", produces = "application/json")
	public ResponseEntity<RespuestaBean> addUsuario(@RequestBody UsuarioBean usuarioBean) throws Exception 
	{   
		
		
		String emailRegex = "^(.+)@(.+)$";
		
		Pattern pattern = Pattern.compile(emailRegex);
		
		Matcher matcher = pattern.matcher(usuarioBean.getEmail());
		
		if(!matcher.matches()) {
			RespuestaBean respuesta = new RespuestaBean("formato email invalido");
			
			return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
		}
		
		
		String passRegex = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=\\w*[a-z]).{1,}$";
		
		pattern = Pattern.compile(passRegex);
		
		matcher = pattern.matcher(usuarioBean.getPassword());
		
		if(!matcher.matches()) {
			RespuestaBean respuesta = new RespuestaBean("password invalida");
			System.out.println("password invalida");
			return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
		}
		
		
		List<Usuario> lista = userService.getUsersByEmail(usuarioBean.getEmail()) ;
		
		if(lista.size()>0) {
			RespuestaBean respuesta = new RespuestaBean("email ya registrado");
			
			return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);
		}
		
	    //
		UUID uuid = UUID.randomUUID();
		
		Usuario usuario = new Usuario();
		
		usuario.setId(uuid.toString());
		usuario.setEmail(usuarioBean.getEmail());
		usuario.setPassword(usuarioBean.getPassword());
		
		//Set<Telefono> telefonos = new 
				
		Set<Telefono> hSet = new HashSet<Telefono>();
		
		for(TelefonoBean x: usuarioBean.getPhones()) {
			Telefono tmp = new Telefono();
			tmp.setNumber(Integer.valueOf(x.getNumber()));
			tmp.setCitycode(Integer.valueOf(x.getCitycode()));
			tmp.setContrycode(Integer.valueOf(x.getCountrycode()));
			tmp.setUsuario(usuario);
			
			hSet.add(tmp);
			
			
		}
		
		usuario.setTelefono(hSet);
		
		String token = jwtTokenUtil.generateToken(usuarioBean);
		
	
		usuario.setToken(token);
		
		usuario.setIsActive(true);
		
		
		LocalDate now = LocalDate.now();
		
		java.sql.Date sqlDate = java.sql.Date.valueOf(now);
		
		usuario.setCreated(sqlDate);
		usuario.setModified(sqlDate);
		usuario.setLastLogin(sqlDate);
		
		
		
		userService.addUser(usuario);
		
		
		
		
		RespuestaBean respuesta = new RespuestaBean();
		
		respuesta.setId(usuario.getId());
		respuesta.setMensaje("Creacion exitosa");
		respuesta.setCreado(usuario.getCreated());
		respuesta.setModificado(usuario.getModified());
		respuesta.setUltimologin(sqlDate);
		respuesta.setToken(token);
		respuesta.setEsActivo(usuario.getIsActive());
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping(path= "/hola")
	public ResponseEntity<String> prueba(){
		RespuestaBean re = new RespuestaBean("password invalida");
		
		
		
		return new ResponseEntity<String>("hola", HttpStatus.OK);
	}
}
