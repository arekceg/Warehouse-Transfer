package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.DummyObjectFactory;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.user.User;
import com.arek.warehousetransfer.user.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WarehouseControllerTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private RestTemplate restTemplate;

	private RestTemplateBuilder restTemplateBuilder;

	@Mock
	private WarehouseService warehouseService;
	@Mock
	private StockService stockService;
	@Mock
	private UserService userService;
	@InjectMocks
	private WarehouseController warehouseController;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.rootUri("http://localhost:8081/")
				.build();

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(warehouseController)
				.build();
	}

	@Test
	public void shouldReturnWarehouse() throws Exception {
		//given
		Long id = 1L;

		given(warehouseService.findWarehouseById(anyLong()))
				.willReturn(DummyObjectFactory.dummyWarehouse());

		//when
		//then
		mockMvc.perform(get("/warehouse/{id}",id))
				.andExpect(status().isOk())
				.andDo(print());
	}


}