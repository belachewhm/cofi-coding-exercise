package io.github.belachewhm.cofi.coding.exercise;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {
	Application application;

	@Before
	public void setup() {
		application = new Application();
	}

	@Test
	public void testRun() {
		try {
			application.run();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@After
	public void teardown() {
		application = null;
	}
}
