package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

   private final QuestionRepository questionRepository;
   
   //질문 목록
   public List<Question> getList(){
      return this.questionRepository.findAll();
   }
   
   //질문 내용 보기
   public Question getQuestion(Integer id) {
      Optional<Question> question = this.questionRepository.findById(id);
      if(question.isPresent()) {
         return question.get();
      }else {
         throw new DataNotFoundException("question not found");
      }
   }
   
   //질문 등록하기 : create 메서드의 각 단계가 무엇을 하는지. 
   public void create(String subject, String content, SiteUser user) {
      Question q = new Question(); //객체 생성 뒤 q로 접근하겠다. 
      q.setSubject(subject); //q라는 객체의 subject 를 q.setSubject( {설정값} ) 으로 설정하겠음.
      q.setContent(content);
      q.setCreateDate(LocalDateTime.now());
      q.setAuthor(user);
      this.questionRepository.save(q);
   }
   
   //수정하기
   public void modify(Question question, String subject, String content) {
	      question.setSubject(subject);
	      question.setContent(content);
	      question.setModifyDate(LocalDateTime.now());
	      this.questionRepository.save(question);
	   }

}