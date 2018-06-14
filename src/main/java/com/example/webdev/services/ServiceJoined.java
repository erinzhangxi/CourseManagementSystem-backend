package com.example.webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev.model.EssayExamQuestion;
import com.example.webdev.model.Exam;
import com.example.webdev.model.FillInTheBlanksExamQuestion;
import com.example.webdev.model.MultipleChoiceQuestion;
import com.example.webdev.model.Question;
import com.example.webdev.model.TrueFalseQuestion;
import com.example.webdev.repositories.ExamRepository;
import com.example.webdev.repositories.EssayExamRepositoryJoined;
import com.example.webdev.repositories.FillInTheBlankQuestionRepositoryJoined;
import com.example.webdev.repositories.MultiChoiceRepositoryJoined;
import com.example.webdev.repositories.BaseQuestionRepository;
import com.example.webdev.repositories.TrueOrFalseQuestionRepositoryJoined;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServiceJoined {
  @Autowired
  BaseQuestionRepository baseRepo;
 
  @Autowired
  FillInTheBlankQuestionRepositoryJoined fillRepo;
  @Autowired
  TrueOrFalseQuestionRepositoryJoined trueRepo;
  @Autowired
  EssayExamRepositoryJoined essayRepo;
  @Autowired 
  ExamRepository examRepo;
  @Autowired
  MultiChoiceRepositoryJoined multiRepo;
  
  @PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion addEssay(@PathVariable("examId") int id, 
			@RequestBody EssayExamQuestion essay) {
		Optional<Exam> data = examRepo.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			essay.setExam(exam);
			return essayRepo.save(essay);
		}
		return null;
	}
//  @GetMapping("/api/exam/{examId}/essays")
//	public EssayExamQuestion getEssays(@PathVariable("examId") int id) {
//		Optional<Exam> data = examRepo.findById(id);
//		if(data.isPresent()) {
//			
//			List<Question> essays = data.getQuestions();
//			exam.getEssay(exam);
//			return essayRepo.save(essay);
//		}
//		return null;
//	}
  
  @PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion addBlanks(@PathVariable("examId") int id, 
			@RequestBody FillInTheBlanksExamQuestion blanks) {
		Optional<Exam> data = examRepo.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			blanks.setExam(exam);
			return fillRepo.save(blanks);
		}
		return null;
	}
  
  @PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceQuestion addChoice(@PathVariable("examId") int id, 
			@RequestBody MultipleChoiceQuestion multipleChoice) {
		Optional<Exam> data = examRepo.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			multipleChoice.setExam(exam);
			return multiRepo.save(multipleChoice);
		}
		return null;
	}
  

	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueFalseQuestion addTrueFalse(@PathVariable("examId") int id, 
			@RequestBody TrueFalseQuestion truefalse) {
		Optional<Exam> data = examRepo.findById(id);
		if(data.isPresent()) {
			Exam exam = data.get();
			truefalse.setExam(exam);
			return trueRepo.save(truefalse);
		}
		return null;
	}
  
}
