package net.ccfish.jvue.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.ccfish.jvue.domain.dao.JvueExUserMapper;
import net.ccfish.jvue.domain.model.JvueExUser;
import net.ccfish.jvue.security.JwtUserDetails;


/**
 * 用户验证方法
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private JvueExUserMapper userMapper;

    @Autowired
    public JwtUserDetailsServiceImpl(JvueExUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Cacheable(value = "JwtUserDetailsService", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final JvueExUser user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("No user found with username '%s'.", username));
        } else {
            
            List<Integer> roles;
            if (user.getRoles() == null) {
                roles = new ArrayList<>();
            }
            else {
                roles= user.getRoles().stream().map(role->role.getId()).collect(Collectors.toList());
            }
            return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(),
                    user.getSuperUser(), user.getNickname(), user.getEmail(), user.getRoles(), roles);
        }
    }

}
