package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;

	//									 content가 있어야 리파지토리에 담김
	public void create(Question question, String content,SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question); //질문에 대한 답변이므로, 해당 외래키 업데이트.
		answer.setAuthor(author);
		this.answerRepository.save(answer); //이 모든 내용을 리파지토리에 저장
	}

	//답변 조회
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if (answer.isPresent()) {
			return answer.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}

	//답변 조회가 되어야 수정을 할 수 있음
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}

	//답변 삭제
	  public void delete(Answer answer) {
	      this.answerRepository.delete(answer);
	   }

}