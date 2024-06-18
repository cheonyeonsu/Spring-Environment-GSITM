package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //시큐리티가 클래스를 인식할 수 있도록 함
@EnableWebSecurity   // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다. 웹시큐리티를 사용하겠다. 
public class SecurityConfig {

   @Bean 
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
      http.authorizeHttpRequests((authorizeHttpRequests) ->
      authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
      //접근
      .csrf(csrf -> csrf //ignoringRequestMatchers를 이용, h2-console/로 시작하는 모든 URL은 CSRF 검증을 하지 않겠다. 
              .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
      		  .headers(headers -> headers
              .addHeaderWriter(new XFrameOptionsHeaderWriter(
                 XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));

      return http.build();
   }

}