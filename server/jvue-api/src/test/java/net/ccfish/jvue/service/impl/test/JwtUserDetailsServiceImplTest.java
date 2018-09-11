package net.ccfish.jvue.service.impl.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.ccfish.jvue.JvueApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JvueApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(profiles = "test")
public class JwtUserDetailsServiceImplTest {

	@Autowired 
	UserDetailsService userDetailsService;
	
	@Test
	public void testLoadUserByUsername() {
		
		UserDetails user = userDetailsService.loadUserByUsername("admin");
		Assert.assertEquals("admin", user.getUsername());
	}
}
