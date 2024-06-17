package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Question {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   //GenerationType : 번호가 1씩 증가. 
   private Integer id;

   @Column(length = 200) //열로 인식
   private String subject;

   @Column(columnDefinition = "TEXT") //열의 데이터의 성격이 TEXT이다. 
   private String content;

   private LocalDateTime createDate;
   
   @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //부모가 삭제되면 자식도 삭제해라. 
   private List<Answer> answerList;
   
   
}