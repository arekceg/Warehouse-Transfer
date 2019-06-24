package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.user.User;
import com.arek.warehousetransfer.utils.Mappings;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseService {

	public List<Warehouse> findAllWarehouses() {
		final String url = Mappings.BACKEND_ADRESS + "/warehouse/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Warehouse>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference <List<Warehouse>>() {
				}
		);
		return response.getBody();
	}

	public List<Warehouse> findWarehousesWithIdNotEqual(Long id) {
		return findAllWarehouses().stream()
				.filter(warehouse -> !warehouse.getManager().getId().equals(id))
				.collect(Collectors.toList());
	}

	public Warehouse findWarehouseById(Long id) {
		final String uri = Mappings.BACKEND_ADRESS+"/warehouse/"+id.toString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri,Warehouse.class);
	}

	public Warehouse findWarehouseByManager(User manager) {
		Long managerId = manager.getId();
		final String uri = Mappings.BACKEND_ADRESS+"/warehouse/user/"+managerId.toString();
		RestTemplate restTemplate = new RestTemplate();
		return  restTemplate.getForObject(uri, Warehouse.class);
	}
}
