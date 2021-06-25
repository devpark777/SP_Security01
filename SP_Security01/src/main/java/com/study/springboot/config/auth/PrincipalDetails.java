package com.study.springboot.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.springboot.model.User;

//시큐리티가 /login 주소요청이 오면 낚아채서 로그인과정을 진행시킨다.
//  로그인 완료되면 => 시큐리티 session을 만들어 준다.(Security ContextHolder)
// Authentication 안에 User 정보가 있어야 함
// User객체타입 => UserDetails이어야 함
// Security Session 객체 안에 => Authentication 객체가 저장된고 => 이것안에 UserDetails 객체가 저장됨
public class PrincipalDetails  implements UserDetails{

	private User user; //콤포지션?
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override  // 해당 User의 권한을 리턴함
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		//우리 사이트에 1년동안 로그인 안하면 휴먼 계정으로 처리
		// 현재 시간 - 로긴시간 => 1년 초과하면 return false로 
		
		return true;
	}

}
