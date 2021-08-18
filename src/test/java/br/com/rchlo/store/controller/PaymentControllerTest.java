package br.com.rchlo.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    PaymentControllerTest() throws URISyntaxException {
    }

    @Test
    void putMethodshouldReturnOkStatusWhenPaymentIsNotCanceled() throws Exception {
        URI uri = new URI("/payments/1");
        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(200));
    }

    @Test
    void putMethodsShouldReturnBadRequestStatusWhenPaymentIsCanceled() throws Exception {
        URI uri = new URI("/payments/4");

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(400));
    }

    @Test
    void deleteMethodShouldReturnOkStatusWhenPaymentIsNotConfirmed() throws Exception {
        URI uri = new URI("/payments/2");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(200));
    }

    @Test
    void deleteMethodShouldReturnBadRequestStatusWhenPaymentIsConfirmed() throws Exception {
        URI uri = new URI("/payments/3");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.
                        status().is(400));
    }
}