package com.example.springproj1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class Springproj1ApplicationTests {

	public void integrationTest(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(response);
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@DisplayName("First endpoint test")
	@Test
	public void testSuccessfulResponseRepos() {
		integrationTest("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos");
	}

	@DisplayName("Second endpoint test")
	@Test
	public void testSuccessfulResponseBranches() {
		integrationTest("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches");
	}

	@DisplayName("Third endpoint test")
	@Test
	public void testSuccessfulResponseCommits() {
		integrationTest("https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits");
	}
}