package eapli.ecourse.exammanagement.application;

import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.questionmanagement.domain.Question;

public class GenerateStructureFormativeExamService {
  private FormativeExam formativeExam;

  public GenerateStructureFormativeExamService(final FormativeExam formativeExam) {
    this.formativeExam = formativeExam;
  }

  public String generateStructureString() {
    StringBuilder sb = new StringBuilder();

    sb.append(buildStartExam());
    sb.append(buildDescriptionExam());
    // sb.append(buildFeedbackExam());
    // sb.append(buildGradeExam());
    sb.append(buildSections());
    sb.append(buildEndExam());

    return sb.toString();
  }

  // private String buildGradeExam() {
  // StringBuilder sb = new StringBuilder();

  // return sb.append("@grade on-submit").toString();
  // }

  // private String buildFeedbackExam() {
  // StringBuilder sb = new StringBuilder();

  // return sb.append("@feedback none;").toString();
  // }

  private String buildDescriptionExam() {
    return "@description \"" + formativeExam.description() + "\";";
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
    sb.append("@start-section " + section.identifier() + ";");
    sb.append("@title \"" + section.title() + "\";");
    sb.append("@description \"" + section.description() + "\";");
    sb.append(buildSectionQuestions(section));
    sb.append("@end-section;");
    return sb.toString();
  }

  private String buildSectionQuestions(FormativeExamSection section) {
    StringBuilder sb = new StringBuilder();

    for (Question question : section.questions()) {
      sb.append("@start-question");
      sb.append("@question-body \"" + question.body() + "\";");
      sb.append(buildCorrectAnswers(question));
      sb.append("@end-question;");

    }

    return sb.toString();
  }

  private String buildCorrectAnswers(Question question) {
    StringBuilder sb = new StringBuilder();
    sb.append(question.getQuestionStructure(question));
    return sb.toString();
  }

  private String buildStartExam() {
    StringBuilder sb = new StringBuilder();
    sb.append("@start-exam " + formativeExam.identity() + ";");
    return sb.toString();
  }

}
