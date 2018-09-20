package net.ccfish.jvue.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ccfish.common.enums.YesOrNoEnum;
import net.ccfish.jvue.autogen.dao.JvueUserMapper;
import net.ccfish.jvue.autogen.model.JvueUser;
import net.ccfish.jvue.domain.dao.JvueExUserMapper;
import net.ccfish.jvue.domain.dao.JvueOauthUserMapper;
import net.ccfish.jvue.domain.model.JvueExRole;
import net.ccfish.jvue.domain.model.JvueOauthUser;
import net.ccfish.jvue.security.JwtUserDetails;
import net.ccfish.jvue.service.JvueOAuth2UserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

@Service
@Transactional
public class JvueOAuth2UserServiceImpl implements JvueOAuth2UserService {

	@Autowired
	private JvueOauthUserMapper oauthUserMapper;
	@Autowired
	private JvueUserMapper userMapper;
	@Autowired
	private JvueExUserMapper exUserMapper;

	@Override
	public JwtUserDetails updateUser(String accountType, String account) {

		Example example = new Example.Builder(JvueOauthUser.class)
				.where(WeekendSqls.<JvueOauthUser>custom().andEqualTo(JvueOauthUser::getAccountType, accountType)
						.andEqualTo(JvueOauthUser::getAccountId, account))
				.build();

		JvueOauthUser oauthUser = oauthUserMapper.selectOneByExample(example);
		JvueUser user;

		if (oauthUser == null) {
			user = new JvueUser();
			user.setUsername(account + "#" + accountType);
			user.setSuperUser(YesOrNoEnum.No.ordinal());
			user.setStatus(1);
			userMapper.insertSelective(user);
			
			oauthUser = new JvueOauthUser();
			oauthUser.setUserId(user.getId());
			oauthUser.setAccountId(account);
			oauthUser.setAccountType(accountType);

			oauthUserMapper.insertSelective(oauthUser);
		} else {
			user = userMapper.selectByPrimaryKey(oauthUser.getUserId());
		}

		List<JvueExRole> userRoles = exUserMapper.selectRoles(user.getId());
		List<Integer> roles;
        if (userRoles.isEmpty()) {
            roles = new ArrayList<>();
        } else {
            roles = userRoles.stream().map(role->role.getId()).collect(Collectors.toList());
        }
        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(),
                user.getSuperUser(), user.getNickname(), user.getEmail(), userRoles, roles);
	}

}
