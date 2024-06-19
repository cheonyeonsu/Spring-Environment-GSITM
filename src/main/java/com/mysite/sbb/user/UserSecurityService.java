package com.mysite.sbb.user;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

   private final UserRepository userRepository;

// 시큐리티 session(내부 Authentication(내부 UserDetails))
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<SiteUser> _siteUser = this.userRepository.findByUsername(username);
      if (_siteUser.isEmpty()) {
         throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
      }
      SiteUser siteUser = _siteUser.get(); //get으로 가지고 옴 
      //사용자의 권한 정보 처리문 
      //ArrayList 이용해서 authorities변수 만들어서 GrantedAuthority내용 LIST로 만들어서 adimin있는지 확인
      List<GrantedAuthority> authorities = new ArrayList<>(); 
      if ("admin".equals(username)) {
    	  //true이면 ADMIN의 getValue값 가지고 옴(UserRole에서)
         authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
      } else {
         authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
      }
      //user객체 생성, 이 객체는 스프링 시큐리티에서 사용하며 user생성자에는 id,pw,권한 리스트가 전달된다. 
      return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
   }
}