package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//CRUD함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 IOC(Inversion Of Controller)된다. 이유는 JpaRepository를 상속했기 때문
//Question을 Integer타입으로 상속받겠다. 
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	//findBy~ 
	//select*from question where subject = ?
	Question findBySubject(String subject); //Jpa에서 제공하는 Query methods
	
	Question findBySubjectAndContent(String subject, String content);
	
	 List<Question> findBySubjectLike(String subject);

	
}
