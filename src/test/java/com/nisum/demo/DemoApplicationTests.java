package com.nisum.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.nisum.demo.bean.RespuestaBean;
import com.nisum.demo.bean.UsuarioBean;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	private String FOO_RESOURCE_URL = "http://localhost:8080";
	
//	@Test
//    public void testWrongPassword() {
//        //assert(2147483646, Integer.sum(2147183646, 300000));
//        
//        TestRestTemplate testRestTemplate = new TestRestTemplate();
//        
//        UsuarioBean usuario = new UsuarioBean();
//        usuario.setEmail("YYYYYYYY");
//        
//        ResponseEntity<RespuestaBean> response = testRestTemplate
//        		.postForEntity(FOO_RESOURCE_URL + "/addUsuario", usuario, RespuestaBean.class);
//        
//        System.out.println("Respuesta"+response.getBody().getMensaje());
//        
//       assertEquals(response.getBody().getMensaje(), "password invalida");
//        		  
//    }
	
}
