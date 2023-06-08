package eapli.ecourse.exammanagement.application;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.exammanagement.service.WriteToFile;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;

public class GenerateStructureFormativeExamService {
  private FormativeExam formativeExam;

  public GenerateStructureFormativeExamService(final FormativeExam formativeExam) {
    this.formativeExam = formativeExam;
  }

  public void generateStructureFile() {
    StringBuilder sb = new StringBuilder();
    sb.append(buildStartExam());
    sb.append(buildDescriptionExam());
    sb.append(buildFeedbackExam());
    sb.append(buildCourseCodeExam());
    sb.append(buildSections());
    sb.append(buildEndExam());

    try {
      WriteToFile.writeToFile(sb.toString());
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      System.out.println("Could not write to file");
      e.printStackTrace();
    }

  }

  private String buildCourseCodeExam() {
    StringBuilder sb = new StringBuilder();
    return sb.append("@course-code" + formativeExam.course().code() + ";").toString();
  }

  private String buildFeedbackExam() {
    StringBuilder sb = new StringBuilder();
    return sb.append("@feedback none;").toString();
  }

  private String buildDescriptionExam() {
    return "@description" + formativeExam.description() + ";";
  }

  private String buildEndExam() {
    StringBuilder sb = new StringBuilder();
    return sb.append("@end-exam;").toString();
  }

  private String buildSections() {
    StringBuilder sb = new StringBuilder();

    for (FormativeExamSection section : formativeExam.sections()) {
      sb.append(buildSection(section));
    }

    return sb.toString();
  }

  private String buildSection(FormativeExamSection section) {
    StringBuilder sb = new StringBuilder();
    sb.append("@start-section" + section.identifier() + ";");
    sb.append("@title" + section.title() + ";");
    sb.append("@description" + section.description() + ";");
    sb.append(buildSectionQuestions(section));
    sb.append("@end-section;");
    return sb.toString();
  }

  private String buildSectionQuestions(FormativeExamSection section) {
    StringBuilder sb = new StringBuilder();
    for (Question question : section.questions()) {
      sb.append("@start-question;");
      sb.append("@type" + question.type() + ";");
      sb.append("@score" + question.score() + ";");
      sb.append("@question-body" + question.body() + ";");
      sb.append(buildCorrectAnswers(question));
      sb.append("@end-question;");

    }

    return sb.toString();
  }

  private String buildCorrectAnswers(Question question) {
    StringBuilder sb = new StringBuilder();
    ShortAnswerQuestion shortAnswerQuestion = (ShortAnswerQuestion) question;

    for (String correctAnswer : shortAnswerQuestion.correctAnswers().keySet()) {
      sb.append("@correct-answer" + correctAnswer + shortAnswerQuestion.correctAnswers().get(correctAnswer) + ";");
    }

    return sb.toString();
  }

  private String buildStartExam() {
    StringBuilder sb = new StringBuilder();
    sb.append("@start-exam" + formativeExam.title());
    return sb.toString();
  }

}
