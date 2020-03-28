package com.rc.questionbankservice.service;

import com.rc.questionbankservice.domain.Question;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface QuestionBankService {

    Question persistQuestion(Question mgQuestionBank, MultipartFile questionContentfile, MultipartFile scannedQuestionFile) throws IOException;
    List<Question> getAllQuestions();
    Question findQuestionById(final String questionId);

}
