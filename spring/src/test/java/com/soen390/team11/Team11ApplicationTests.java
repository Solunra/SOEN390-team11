package com.soen390.team11;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@FlywayTest
class Team11ApplicationTests {

	@Test
	void contextLoads() {
	}

}
