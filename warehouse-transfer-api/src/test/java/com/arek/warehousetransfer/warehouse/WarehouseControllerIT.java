package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.WarehouseTransferApplication;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = WarehouseTransferApplication.class
)

@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WarehouseControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldReturnAdminWarehouse() throws Exception {
		//given
		Long id = 1L;
		//when
		//then
		mockMvc.perform(get("/warehouse/user/{id}", id))
				.andExpect(status().isOk())
               .andExpect(jsonPath("$.name", Matchers.is("admin-warehouse")))
				.andDo(print());
	}

	@Test
	public void warehouseNotFound_shouldReturn404() throws Exception {
		//given
		Long id = 9L;

		//when
		//then
		mockMvc.perform(get("/warehouse/{id}", id))
                .andExpect(status().is(404))
				.andDo(print());

	}

//	@Test
//	public void shouldAddNewWarehouseToDb() throws Exception {
//		//given
//		Random r = new Random();
//
//		//must create new User to act as warehouse manager and add it to DB
//		//since one User can only have one warehouse in management
//		User manager = User.empty();
//		String userName = "User" + r.nextInt(100);
//		manager.setUsername(userName);
//		manager.setName(userName);
//		manager.setPassword(Integer.toString(r.nextInt(1000)));
//
//		manager = restTemplate.postForObject(
//				"/user/add/",
//				manager,
//				User.class
//		);
//
//		//create a new random warehouse
//		Warehouse newWarehouse = Warehouse.empty();
//		newWarehouse.setManager(manager);
//		newWarehouse.setName("newTestWarehouse" + r.nextInt(20));
//
//		//when
//		ResponseEntity<Warehouse> responseEntity = restTemplate
//				.postForEntity(
//						"/warehouse/add/",
//						newWarehouse,
//						Warehouse.class
//				);
////		then
//		assertEquals(responseEntity.getStatusCodeValue(), 201);
//	}
}
