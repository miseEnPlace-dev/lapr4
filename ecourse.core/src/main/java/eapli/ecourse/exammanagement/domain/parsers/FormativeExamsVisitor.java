package eapli.ecourse.exammanagement.domain.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamScore;
import eapli.ecourse.exammanagement.domain.ExamSection;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.FormativeExamBuilder;
import eapli.ecourse.exammanagement.domain.SectionBuilder;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;

public class FormativeExamsVisitor extends FormativeExamBaseVisitor<FormativeExamBuilder> {
  private FormativeExamBuilder builder;
  private SectionBuilder section;
  private List<ExamSection> sections;
  int examScore = 0;
  int sectionsScore = 0;

  @Override
  public FormativeExamBuilder visitStart(FormativeExamParser.StartContext ctx) {
    visit(ctx.exam());
    return builder;
  }

  @Override
  public FormativeExamBuilder visitExam(FormativeExamParser.ExamContext ctx) {
    builder = new FormativeExamBuilder();

    visit(ctx.startExam());
    visit(ctx.header());
    visit(ctx.sections());

    if (examScore != sectionsScore) {
      raiseError(ctx, "The sum of the sections' scores must be equal to the exam's score.");
    }

    return builder;
  }

  @Override
  public FormativeExamBuilder visitStartExam(FormativeExamParser.StartExamContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    QuestionIdentifier identifier = QuestionIdentifier.valueOf(str);
    builder.withIdentifier(identifier);
    return builder;
  }

  /**
   * Visits the header of a Exam or Section.
   *
   * Maps all attributes, making sure that there are no duplicates, and that all
   * required attributes are present (and that no forbidden attributes are
   * present).
   */
  @Override
  public FormativeExamBuilder visitHeader(FormativeExamParser.HeaderContext ctx) {
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
      if (p.feedback() != null) {
        if (properties.containsKey("feedback"))
          raiseError(p, "Feedback already defined.");

        properties.put("feedback", p.feedback().FDB_GRD_TYPE().getText());
      }
    });

    if (ctx.getParent().getStart().getText().equals("@start-exam")) {
      String[] requiredProps = {
          "title",
          "feedback",
          "grade",
          "score"
      };

      Arrays.asList(requiredProps).forEach(p -> {
        if (!properties.containsKey(p))
          raiseError(ctx,
              "The exam does not contain the " + p + " attribute. Specify a " + p + " using the @" + p + " tag.");
      });

      initializeExam(properties);
    } else {
      String[] requiredProps = {
          "title",
          "score"
      };
      String[] forbiddenProps = {
          "feedback",
          "grade"
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

    return builder;
  }

  @Override
  public FormativeExamBuilder visitSections(FormativeExamParser.SectionsContext ctx) {
    sections = new ArrayList<ExamSection>();
    visitChildren(ctx);
    builder.withSections(sections);
    return builder;
  }

  @Override
  public FormativeExamBuilder visitSection(FormativeExamParser.SectionContext ctx) {
    section = new SectionBuilder();

    visit(ctx.start_section());
    visit(ctx.header());
    // visit(ctx.questions());

    return builder;
  }

  @Override
  public FormativeExamBuilder visitStart_section(FormativeExamParser.Start_sectionContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    QuestionIdentifier identifier = QuestionIdentifier.valueOf(str);
    section.withIdentifier(identifier);
    return builder;
  }

  private void initializeExam(Map<String, Object> properties) {
    ExamTitle title = ExamTitle.valueOf(properties.get("title").toString());
    builder.withTitle(title);
    examScore = Integer.parseInt(properties.get("score").toString());
    if (properties.containsKey("description")) {
      ExamDescription description = ExamDescription.valueOf(properties.get("description").toString());
      builder.withDescription(description);
    } else {
      builder.withDescription(ExamDescription.valueOf(""));
    }
  }

  private void initializeSection(Map<String, Object> properties) {
    ExamTitle title = ExamTitle.valueOf(properties.get("title").toString());
    section.withTitle(title);
    ExamScore score = ExamScore.valueOf(Integer.parseInt(properties.get("score").toString()));
    section.withScore(score);
    sectionsScore += Integer.parseInt(properties.get("score").toString());
    if (properties.containsKey("description")) {
      ExamDescription description = ExamDescription.valueOf(properties.get("description").toString());
      section.withDescription(description);
    } else {
      section.withDescription(ExamDescription.valueOf(""));
    }
    section.withQuestions(new ArrayList<>());
    ExamSection section = this.section.build();
    sections.add(section);
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
