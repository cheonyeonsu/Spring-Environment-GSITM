package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
    //열거하여 사용하는 자료형을 쓸 때 enum
	//대문자 표기 이유 : 상수 -> 변경하지 않겠다 -> setter사용하지 않음.
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
   
   UserRole(String value){
      this.value= value;
   }
   
   private String value;
}