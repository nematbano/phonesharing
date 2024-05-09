package com.sample.phonesharing;

import com.sample.phonesharing.controller.PhoneSharingController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PhonesharingApplicationTests {
	@Autowired
	private PhoneSharingController controller;
	@Test
	void contextLoads() throws Exception  {
		assertThat(controller).isNotNull();
	}

}
