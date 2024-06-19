package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Answer {

	@Id //기본키. 각 데이터들을 구분하는 유효한 값(중복 불가능)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //고유한 번호를 생성하는 방법
	private Integer id;

	@Column(columnDefinition = "TEXT") //텍스트를 열 데이터로 넣을 수 있다. 글자수를 제한할 수 없는 경우에 사용. 
	private String content;

	private LocalDateTime createDate;
	
	@ManyToOne//Question을 참조해야 하기 위해. 질문이 있어야 답변이 필요하므로. 
	private Question question; //content는 답변이기 때문에 필요없음. 
}
