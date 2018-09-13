package net.ccfish.jvue.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import net.ccfish.common.acl.CurrentUser;
import net.ccfish.jvue.security.JwtUserDetails;

@Controller
public class VueController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping(path = { "", "/", "/admin", "/admin/**" }, produces = {MediaType.TEXT_HTML_VALUE})
	public ModelAndView home(@CurrentUser JwtUserDetails jwtUser) {
		logger.debug("vue home {}", jwtUser);
		ModelAndView view = new ModelAndView("/index");
		return view;
	}
}
