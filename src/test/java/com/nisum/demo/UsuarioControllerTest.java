package com.nisum.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.demo.bean.RespuestaBean;
import com.nisum.demo.bean.UsuarioBean;
import com.nisum.demo.controller.UserController;
import com.nisum.demo.services.UsuarioService;
import com.nisum.demo.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {
	
//	private UserController controller;
//	private UsuarioService service;
////	
	@Autowired
	private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	
	
	
	@MockBean
	private UsuarioService userService;
	
	@Test
	public void testHola() {
		
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/hola")
			            .accept(MediaType.APPLICATION_JSON))
			            .andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testWrongEmail() {
		
		UsuarioBean us = new UsuarioBean();
		us.setEmail("jjjj");
		us.setName("luis");
		us.setPassword("HOla12");
		 try {
			mockMvc.perform(MockMvcRequestBuilders.post("/addUsuario")
					.content(mapper.writeValueAsString(us))
					.contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isBadRequest())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("formato email invalido"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testWrongPassword() {
		
		UsuarioBean us = new UsuarioBean();
		us.setEmail("luis@jujuy.com");
		us.setName("luis");
		us.setPassword("Hola1");
		 try {
			mockMvc.perform(MockMvcRequestBuilders.post("/addUsuario")
					.content(mapper.writeValueAsString(us))
					.contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isBadRequest())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("password invalida"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
