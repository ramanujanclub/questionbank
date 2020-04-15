package com.rc.questionbankservice.service;

import com.rc.questionbankservice.domain.ApproveQuestionRequest;
import com.rc.questionbankservice.domain.ParentQuestion;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.VerifyQuestionRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionBankService {

    Question persistQuestion(Question mgQuestionBank, MultipartFile questionContentFile, MultipartFile scannedQuestionFile);
    List<Question> getAllQuestions();
    Question findQuestionById(final String questionId);
    ParentQuestion persistNewParentQuestion(ParentQuestion parentQuestion);
    void markQuestionAsVerified(final String questionId, VerifyQuestionRequest verifyQuestionRequest);
    void markQuestionAsApproved(final String questionId, ApproveQuestionRequest approveQuestionRequest);
    void saveQuestionImages( String questionId, MultipartFile questionContentFile, MultipartFile scannedQuestionFile);
}
