package eapli.ecourse.exammanagement.domain.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import eapli.ecourse.answermanagement.domain.ExamAnswerBuilder;
import eapli.ecourse.exammanagement.application.ExamPrinter;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamBuilder;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSection;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSectionBuilder;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.questionmanagement.domain.Question;

public class TakeEvaluationExamVisitor extends ExamBaseVisitor<ExamAnswerBuilder> {
  private ExamAnswerBuilder answerBuilder;
  private EvaluationExamBuilder examBuilder;
  private EvaluationExamSectionBuilder sectionBuilder;
  private List<EvaluationExamSection> sections;
  private List<Question> questions;

  private ExamPrinter printer;

  public TakeEvaluationExamVisitor(final ExamPrinter printer) {
    this.printer = printer;
  }

  @Override
  public ExamAnswerBuilder visitStart(ExamParser.StartContext ctx) {
    visit(ctx.exam());
    return answerBuilder;
  }

  @Override
  public ExamAnswerBuilder visitExam(ExamParser.ExamContext ctx) {
    answerBuilder = new ExamAnswerBuilder();
    examBuilder = new EvaluationExamBuilder();

    visit(ctx.start_exam());
    visit(ctx.header());
    visit(ctx.sections());

    return answerBuilder;
  }

  @Override
  public ExamAnswerBuilder visitStart_exam(ExamParser.Start_examContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    ExamIdentifier identifier = ExamIdentifier.valueOf(str);
    examBuilder.withIdentifier(identifier);

    return answerBuilder;
  }

  /**
   * Visits the header of a Exam or Section.
   *
   * Maps all attributes, making sure that there are no duplicates, and that all
   * required attributes are present (and that no forbidden attributes are
   * present).
   */
  @Override
  public ExamAnswerBuilder visitHeader(ExamParser.HeaderContext ctx) {
    Map<String, Object> properties = new HashMap<>();
    ctx.properties().forEach(p -> {
      if (p.title() != null) {
        if (properties.containsKey("title"))
          raiseError(p, "Title already defined.");

        properties.put("title", extractString(p.title().STRING().getText()));
      }
      if (p.description() != null) {
        if (properties.containsKey("description"))
          raiseError(p, "Description already defined.");

        properties.put("description", extractString(p.description().STRING().getText()));
      }
      if (p.feedback_header() != null) {
        if (properties.containsKey("feedback"))
          raiseError(p, "Feedback already defined.");

        properties.put("feedback", p.feedback_header().FDB_GRD_TYPE().getText());
      }
      if (p.grade() != null) {
        if (properties.containsKey("grade"))
          raiseError(p, "Grade already defined.");

        properties.put("grade", p.grade().FDB_GRD_TYPE().getText());
      }
      if (p.course() != null) {
        if (properties.containsKey("course-code"))
          raiseError(p, "Course code already defined.");

        properties.put("course-code", extractString(p.course().STRING().getText()));
      }
    });

    if (ctx.getParent().getStart().getText().equals("@start-exam")) {
      String[] requiredProps = {
          "title",
          "feedback",
          "grade",
          "course-code"
      };

      Arrays.asList(requiredProps).forEach(p -> {
        if (!properties.containsKey(p))
          raiseError(ctx,
              "The exam does not contain the " + p + " attribute. Specify a " + p + " using the @" + p + " tag.");
      });

      initializeExam(properties);
    } else {
      String[] requiredProps = {
          "title"
      };
      String[] forbiddenProps = {
          "feedback",
          "grade",
          "course-code"
      };

      Arrays.asList(requiredProps).forEach(p -> {
        if (!properties.containsKey(p))
          raiseError(ctx,
              "The section does not contain the " + p + " attribute. Specify a " + p + " using the @" + p + " tag.");
      });

      Arrays.asList(forbiddenProps).forEach(p -> {
        if (properties.containsKey(p))
          raiseError(ctx, "A section cannot contain the " + p + " attribute. Remove the @" + p + " tag.");
      });

      initializeSection(properties);
    }

    return answerBuilder;
  }

  @Override
  public ExamAnswerBuilder visitSections(ExamParser.SectionsContext ctx) {
    sections = new ArrayList<EvaluationExamSection>();
    visitChildren(ctx);
    examBuilder.withSections(sections);
    return answerBuilder;
  }

  @Override
  public ExamAnswerBuilder visitSection(ExamParser.SectionContext ctx) {
    sectionBuilder = new EvaluationExamSectionBuilder();
    questions = new ArrayList<Question>();

    visit(ctx.start_section());
    visit(ctx.header());
    visit(ctx.questions());

    sectionBuilder.withQuestions(questions);
    sections.add(sectionBuilder.build());

    return answerBuilder;
  }

  @Override
  public ExamAnswerBuilder visitStart_section(ExamParser.Start_sectionContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    SectionIdentifier identifier = SectionIdentifier.valueOf(str);
    sectionBuilder.withIdentifier(identifier);

    return answerBuilder;
  }

  private void initializeExam(Map<String, Object> properties) {
    ExamTitle title = ExamTitle.valueOf(properties.get("title").toString());
    examBuilder.withTitle(title);
    ExamInfo feedbackInfo = ExamInfo.convert(properties.get("feedback").toString());
    examBuilder.withFeedbackInfo(feedbackInfo);
    ExamInfo gradeInfo = ExamInfo.convert(properties.get("grade").toString());
    examBuilder.withGradeInfo(gradeInfo);
    ExamScore score = ExamScore.valueOf(Integer.parseInt(properties.get("score").toString()));
    examBuilder.withScore(score);

    if (properties.containsKey("description")) {
      ExamDescription description = ExamDescription.valueOf(properties.get("description").toString());
      examBuilder.withDescription(description);

      printer.printExamHeader(title.toString(), description.toString());
    } else {
      examBuilder.withDescription(ExamDescription.valueOf(""));

      printer.printExamHeader(title.toString(), "");
    }
  }

  private void initializeSection(Map<String, Object> properties) {
    SectionTitle title = SectionTitle.valueOf(properties.get("title").toString());
    sectionBuilder.withTitle(title);
    ExamScore score = ExamScore.valueOf(Integer.parseInt(properties.get("score").toString()));
    sectionBuilder.withScore(score);

    if (properties.containsKey("description")) {
      SectionDescription description = SectionDescription.valueOf(properties.get("description").toString());
      sectionBuilder.withDescription(description);

      printer.printSectionHeader(title.toString(), description.toString());
    } else {
      sectionBuilder.withDescription(SectionDescription.valueOf(""));

      printer.printSectionHeader(title.toString(), "");
    }
  }

  private String extractString(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < s.length() - 1; i++)
      sb.append(s.charAt(i));
    return sb.toString();
  }

  private void raiseError(ParserRuleContext ctx, String msg) {
    Token token = ctx.getStart();
    int lineNo = token.getLine();
    throw new ParseException(lineNo, msg);
  }
}
