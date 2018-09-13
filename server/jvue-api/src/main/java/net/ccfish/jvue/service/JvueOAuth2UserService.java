package net.ccfish.jvue.service;

import net.ccfish.jvue.security.JwtUserDetails;

public interface JvueOAuth2UserService {

	JwtUserDetails updateUser(String accountType, String account);
}
