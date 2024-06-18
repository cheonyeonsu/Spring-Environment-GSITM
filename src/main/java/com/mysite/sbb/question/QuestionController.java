package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
   
   private final QuestionService questionService;
   
   //질문목록
   @GetMapping("/list")
   public String list(Model model) {
      List<Question> questionList = this.questionService.getList();
      model.addAttribute("questionList", questionList);
      return "question_list";
   }
   
   //질문 내용 보기
   @GetMapping(value = "/detail/{id}")
   public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
      Question question = this.questionService.getQuestion(id);
      model.addAttribute("question",question);
      return "question_detail";
   }
   
   //질문 등록 화면. 
   //get방식에서도 question_form템플릿에 QuestionForm객체 전달된다. 따로 모델을 쓰지 않아도 전달됨. 
   @GetMapping("/create")
   public String questionCreate(QuestionForm questionForm) {
      return "question_form";
   }
   
   //질문 등록하기
   @PostMapping("/create")
   public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
      if(bindingResult.hasErrors()) {
         return "question_form";
      }
      this.questionService.create(questionForm.getSubject(), questionForm.getContent());
      return "redirect:/question/list";   // 질문 저장후 질문 목록으로 이동

   }
}