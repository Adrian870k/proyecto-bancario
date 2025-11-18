package com.banco.clientes.servicio_clientes;

import com.banco.clientes.domain.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ServicioClientesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Prueba Unitaria para la entidad de dominio Cliente.
     */
    @Test
    void testClienteEntityGettersSetters() {

        Cliente cliente = new Cliente();

        cliente.setNombre("Juan Perez");
        cliente.setIdentificacion("12345");
        cliente.setEstado(true);
        cliente.setContrasena("pass123");

        assertEquals("Juan Perez", cliente.getNombre());
        assertEquals("12345", cliente.getIdentificacion());
        assertEquals(true, cliente.getEstado());
        assertEquals("pass123", cliente.getContrasena());
    }

    /**
     * Prueba de Integración.
     */
    @Test
    void testCreateClienteIntegration() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente Prueba Integracion");
        cliente.setIdentificacion("987654321");
        cliente.setContrasena("segura");
        cliente.setEstado(true);
        cliente.setDireccion("Calle 45");
        cliente.setTelefono("653-1234");
        cliente.setEdad(30);
        cliente.setGenero("M");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Cliente Prueba Integracion"))
                .andExpect(jsonPath("$.identificacion").value("987654321"));
    }

}
