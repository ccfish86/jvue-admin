package net.ccfish.jvue.rest.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.ccfish.jvue.JvueApiApplication;
import net.ccfish.jvue.config.WebSecurityConfig;
import net.ccfish.jvue.security.JwtUserDetails;
import net.ccfish.jvue.security.mock.MockCurrentUserArgumentResolver;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JvueApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(profiles = "test")
public class AuthControllerTest {

	@Autowired
	private WebApplicationContext context;

	protected MockMvc mockMvc;

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

//	/**
//	 * 未登录 测试
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void testGetPage() throws Exception {
//		
//		// 未登录
//		setAnon();
//		
//		RequestBuilder request = MockMvcRequestBuilders.get("/auth/page");
//		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isUnauthorized())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	/**
//	 * 已登录用户
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void testGetPageAuthed() throws Exception {
//
//		// 登录用户
//		setAdmin();
//
//		RequestBuilder request = MockMvcRequestBuilders.get("/auth/page");
//		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//
//	}
	
	protected void setAnon() {
		MockCurrentUserArgumentResolver.jwtUser = null;
	}
	protected void setAdmin() {
		Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
		JwtUserDetails jwtUser = new JwtUserDetails(1L, "mock", "mock",
				1, "mock user", "",
				authorities, new ArrayList<>());
		MockCurrentUserArgumentResolver.jwtUser = jwtUser;
	}

}
