package com.road.runner.roadrunner;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.road.runner.roadrunner.Controller.RentalFormController;
import com.road.runner.roadrunner.Modal.RentalForm;
import com.road.runner.roadrunner.Repository.RentalFormRepository;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class RoadRunnerRentalFormTests {

    @Autowired
	private MockMvc mvc;

	@InjectMocks
	private RentalFormController formController;

	@Mock
	private RentalFormRepository repo;


	private JacksonTester<RentalForm> jsonForm;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        JacksonTester.initFields(this, new ObjectMapper().registerModule(new JavaTimeModule()));
        mvc = MockMvcBuilders.standaloneSetup(formController).build();
    }


    @Test
	public void testPostForm() throws Exception {

        LocalDateTime dat = LocalDateTime.now();

		RentalForm form = new RentalForm(1L, "Hunaid", "Karachi", 12345, 20000, dat, dat);


		when(repo.save(form)).thenReturn(form);


		mvc.perform(MockMvcRequestBuilders
				.post("/form/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonForm.write(form).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


    
}
