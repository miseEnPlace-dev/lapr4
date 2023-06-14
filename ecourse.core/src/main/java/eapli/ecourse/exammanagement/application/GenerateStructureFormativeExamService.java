package eapli.ecourse.exammanagement.application;

import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.questionmanagement.domain.Question;

public class GenerateStructureFormativeExamService {
  private FormativeExam formativeExam;
  private ExamScore examScore;

  public GenerateStructureFormativeExamService(final FormativeExam formativeExam,
      final ExamScore examScore) {
    this.formativeExam = formativeExam;
    this.examScore = examScore;
  }

  public String generateStructureString() {
    StringBuilder sb = new StringBuilder();

    sb.append(buildStartExam());
    sb.append(buildHeaderExam());

    double score = getScorePerQuestion();

    sb.append(buildSections(score));
    sb.append(buildEndExam());

    return sb.toString();
  }

  private String buildGradeExam() {
    StringBuilder sb = new StringBuilder();

    return sb.append("@grade on-submit;").toString();
  }

  private String buildFeedbackExam() {
    StringBuilder sb = new StringBuilder();

    return sb.append("@feedback on-submit;").toString();
  }

  private Object buildHeaderExam() {
    StringBuilder sb = new StringBuilder();

    sb.append(buildTitleExam());
    sb.append(buildDescriptionExam());
    sb.append(buildFeedbackExam());
    sb.append(buildGradeExam());

    return sb.toString();
  }

  private String buildTitleExam() {
    return "@title \"" + formativeExam.title() + "\";";
  }

  private String buildDescriptionExam() {
    return "@description \"" + formativeExam.description() + "\";";
  }

  private String buildEndExam() {
    StringBuilder sb = new StringBuilder();
    return sb.append("@end-exam;").toString();
  }

  private String buildSections(Double score) {
    StringBuilder sb = new StringBuilder();

    for (FormativeExamSection section : formativeExam.sections()) {
      sb.append(buildSection(section, score));
    }

    return sb.toString();
  }

  private String buildSection(FormativeExamSection section, Double score) {
    StringBuilder sb = new StringBuilder();
    sb.append("@start-section " + section.identifier() + ";");
    sb.append("@title \"" + section.title() + "\";");
    sb.append("@description \"" + section.description() + "\";");
    sb.append(buildSectionQuestions(section, score));
    sb.append("@end-section;");
    return sb.toString();
  }

  private String buildSectionQuestions(FormativeExamSection section, Double score) {
    StringBuilder sb = new StringBuilder();

    for (Question question : section.questions()) {
      sb.append("@start-question");
      sb.append("@type " + question.typeQuestion() + ";");
      sb.append("@score " + score + ";");
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

  private double getScorePerQuestion() {
    int nQuestions = 0;

    for (FormativeExamSection section : formativeExam.sections())
      while (section.questions().iterator().hasNext()) {
        nQuestions++;
      }

    double result = Math.round((this.examScore.value() / nQuestions) * 100);

    return result / 100;
  }

}
