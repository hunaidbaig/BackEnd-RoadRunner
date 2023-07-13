package com.road.runner.roadrunner;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.road.runner.roadrunner.Controller.CarController;
import com.road.runner.roadrunner.Modal.CarModal;
import com.road.runner.roadrunner.Repository.CarRepository;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class RoadRunnerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private CarController carController;

	@Mock
	private CarRepository repo;


	private JacksonTester<List<CarModal>> jsonCarList;

	private JacksonTester<CarModal> jsonCar;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        JacksonTester.initFields(this, new ObjectMapper().registerModule(new JavaTimeModule()));
        mvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

	// get all cars
	@Test
	public void testGetAllCars() throws Exception {
		CarModal car = new CarModal(1L, "porsche 718 spyder rs", "nleash pure driving pleasure with the porsche 718 spyder rs", "experience the pinnacle of open-top driving with the porsche 718 spyder rs, a true automotive masterpiece that combines", "https://github.com/jeff-lent/roadrunnercars/blob/main/Porsche718SpyderRS.png?raw=true", 220000L );
		CarModal car2 = new CarModal(1L, "porsche 718 spyder rs", "nleash pure driving pleasure with the porsche 718 spyder rs", "experience the pinnacle of open-top driving with the porsche 718 spyder rs, a true automotive masterpiece that combines", "https://github.com/jeff-lent/roadrunnercars/blob/main/Porsche718SpyderRS.png?raw=true", 220000L );

		List<CarModal> cars = Arrays.asList(car, car2);

		when(repo.findAll()).thenReturn(cars);


		mvc.perform(MockMvcRequestBuilders.get("/cars")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonCarList.write(cars).getJson()));
	}

	// post a car
	@Test
	public void testPostCars() throws Exception {
		CarModal car = new CarModal(1L, "porsche 718 spyder rs", "nleash pure driving pleasure with the porsche 718 spyder rs", "experience the pinnacle of open-top driving with the porsche 718 spyder rs, a true automotive masterpiece that combines", "https://github.com/jeff-lent/roadrunnercars/blob/main/Porsche718SpyderRS.png?raw=true", 220000L );


		when(repo.save(car)).thenReturn(car);


		mvc.perform(MockMvcRequestBuilders
				.post("/cars/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCar.write(car).getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


}
