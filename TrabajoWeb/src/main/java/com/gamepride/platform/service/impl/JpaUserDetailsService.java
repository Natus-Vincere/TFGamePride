package com.gamepride.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Gamer;
import com.gamepride.platform.model.Role;
import com.gamepride.platform.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Gamer gamer = userRepository.findByUsername(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : gamer.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return new User(gamer.getUsername(), gamer.getPassword(), true, true, true, true, authorities);
	}

}
