package com.mysite.sbb.question;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
   
   private final QuestionService questionService;
   
   private final UserService userService;
   
   //질문목록
   @GetMapping("/list")
   public String list(Model model) {
      List<Question> questionList = this.questionService.getList();
      //QuestionService로부터 가져온 questionList를 model에 questionList이름으로 추가.
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
   @PreAuthorize("isAuthenticated()") //로그인한 사용자만 접근
   @GetMapping("/create")
   public String questionCreate(QuestionForm questionForm) {
      return "question_form";
   }
   
   //질문 등록하기
   @PreAuthorize("isAuthenticated()") //로그아웃 상태에서 호출하면 로그인 페이지로 강제 이동
   @PostMapping("/create")
   public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
      if(bindingResult.hasErrors()) {
         return "question_form";
      }
      SiteUser siteUser = this.userService.getUser(principal.getName());
      this.questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
      return "redirect:/question/list";   // 질문 저장후 질문 목록으로 이동

   }
   
   //수정하기
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/modify/{id}")
   public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
      Question question = this.questionService.getQuestion(id);
      if (!question.getAuthor().getUsername().equals(principal.getName())) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
      }
      questionForm.setSubject(question.getSubject());
      questionForm.setContent(question.getContent());
      return "question_form"; //modifyForm 안만듦
   }

   
}