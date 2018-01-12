package io.github.belachewhm.cofi.coding.exercise.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

@RunWith(MockitoJUnitRunner.class)
public class Swagger2ConfigTest {
	@Mock
	private Environment environment;

	@InjectMocks
	private Swagger2Config swagger2Config;

	@Test
	public void testApi() {
		Docket docket = swagger2Config.api();
		Assert.assertNotNull(docket);
	}

	@Test
	public void testApiInfo() {
		String TEST_DESCRIPTION = RandomStringUtils.random(10, true, true);
		String TEST_VERSION = RandomStringUtils.random(10, true, true);
		Mockito.when(environment.getProperty("project.description")).thenReturn(TEST_DESCRIPTION);
		Mockito.when(environment.getProperty("project.version")).thenReturn(TEST_VERSION);
		ApiInfo apiInfo = swagger2Config.apiInfo();
		Assert.assertEquals(TEST_DESCRIPTION, apiInfo.getDescription());
		Assert.assertEquals(TEST_VERSION, apiInfo.getVersion());
	}
}
