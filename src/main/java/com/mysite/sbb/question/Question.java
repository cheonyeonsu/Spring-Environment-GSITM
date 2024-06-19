package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Question {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임
                             //GenerationType : 번호가 1씩 증가. 
   private Integer id; //Integer: 정수형을 객체로 쓸 때. 

   @Column(length = 200) //열로 인식
   private String subject;

   @Column(columnDefinition = "TEXT") //열의 데이터의 성격이 TEXT이다. 
   private String content;

   private LocalDateTime createDate;
   
   
   //하나의 질문에 답변이 여러개 달릴 수 있기 때문에 @OneToMany. 질문 : 답변 
   @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
   private List<Answer> answerList;
   
   //사용자와 질문간의 관계
   @ManyToOne // 질문 : 사용자
   private SiteUser author;
   
   private LocalDateTime modifyDate;
   
   
}