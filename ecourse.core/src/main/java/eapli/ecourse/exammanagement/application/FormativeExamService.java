package eapli.ecourse.exammanagement.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      int randomIndex = Math.toIntExact(Math.round(Math.random() * (questionsFromType.size() - 1)));
      questions.add(questionsFromType.get(randomIndex));
      questionsFromType.remove(randomIndex);
    }

    return questions;
  }

  private String sanitizeType(String type) {
    // remove -, use camel case
    StringBuilder sb = new StringBuilder();
    String[] words = type.split("-");
    for (String word : words) {
      sb.append(word.substring(0, 1).toUpperCase());
      sb.append(word.substring(1));
    }
    return sb.toString() + "Question";
  }

  private Collection<Question> buildSectionQuestions(int numberOfQuestions, String questionsType,
      Collection<Question> questionsFromType) {

    if (questionsFromType.size() < numberOfQuestions)
      throw new IllegalArgumentException(
          "Not enough questions of type " + questionsType);

    return getRandomQuestions(numberOfQuestions, (List<Question>) questionsFromType);
  }

  public Collection<FormativeExamSection> buildSections(FormativeExamRequest request, CourseDTO course) {
    Map<String, Collection<Question>> questionsByType = new HashMap<>();

    List<FormativeExamSection> sections = new ArrayList<>();

    Collection<Question> questionsFromType;

    for (FormativeExamSectionRequest sectionRequest : request.sections()) {
      final String sanitizedType = sanitizeType(sectionRequest.questionsType());
      questionsFromType = (Collection<Question>) questionRepository.findWithTypeFromCourse(sanitizedType,
          course.getCode());

      if (questionsByType.get(sanitizedType) == null)
        questionsByType.put(sanitizedType, questionsFromType);

    }

    for (FormativeExamSectionRequest sectionRequest : request.sections()) {
      final Collection<Question> sectionQuestions = buildSectionQuestions(sectionRequest.numberOfQuestions(),
          sectionRequest.questionsType(), questionsByType.get(sanitizeType(sectionRequest.questionsType())));

      sections.add(new FormativeExamSection(sectionRequest.identifier(), sectionRequest.title(),
          sectionRequest.description(), sectionQuestions));
    }

    return sections;
  }
}
