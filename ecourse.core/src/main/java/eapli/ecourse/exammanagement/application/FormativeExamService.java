package eapli.ecourse.exammanagement.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;

public class FormativeExamService {
  private QuestionRepository questionRepository;

  public FormativeExamService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  private Collection<Question> getRandomQuestions(int numberOfQuestions, List<Question> questionsFromType) {
    List<Question> questions = new ArrayList<>();

    for (int i = 0; i < numberOfQuestions; i++) {
      int randomIndex = (int) (Math.random() * questionsFromType.size());
      questions.add(questionsFromType.get(randomIndex));
      questionsFromType.remove(randomIndex);
    }

    return questions;
  }

  public Collection<Question> buildSection(int numberOfQuestions, String questionsType, CourseDTO course) {
    final Collection<Question> questionsFromType = (Collection<Question>) questionRepository.findWithTypeFromCourse(
        questionsType,
        course.getCode());

    return getRandomQuestions(numberOfQuestions, (List<Question>) questionsFromType);
  }

  public Collection<FormativeExamSection> buildSections(FormativeExamRequest request, CourseDTO course) {
    List<FormativeExamSection> sections = new ArrayList<>();
    for (FormativeExamSectionRequest sectionRequest : request.sections()) {
      Collection<Question> questions = buildSection(sectionRequest.numberOfQuestions(),
          sectionRequest.questionsType(),
          course);

      final FormativeExamSection section = new FormativeExamSection(sectionRequest.identifier(), sectionRequest.title(),
          sectionRequest.description(),
          questions);

      sections.add(section);
    }

    return sections;
  }
}
