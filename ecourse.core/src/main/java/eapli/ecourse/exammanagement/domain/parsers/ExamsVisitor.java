package eapli.ecourse.exammanagement.domain.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import eapli.ecourse.exammanagement.domain.Description;
import eapli.ecourse.exammanagement.domain.EvaluationExam;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.Score;
import eapli.ecourse.exammanagement.domain.Section;
import eapli.ecourse.exammanagement.domain.Title;
import eapli.ecourse.questionmanagement.domain.Identifier;

public class ExamsVisitor extends ExamBaseVisitor<EvaluationExam> {
  private EvaluationExam exam;
  private Section section;
  private List<Section> sections;

  @Override
  public EvaluationExam visitStart(ExamParser.StartContext ctx) {
    visit(ctx.exam());
    return exam;
  }

  @Override
  public EvaluationExam visitExam(ExamParser.ExamContext ctx) {
    exam = new EvaluationExam();

    visit(ctx.start_exam());
    visit(ctx.header());
    visit(ctx.sections());

    return exam;
  }

  @Override
  public EvaluationExam visitStart_exam(ExamParser.Start_examContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    Identifier identifier = Identifier.valueOf(str);
    exam.changeIdentifier(identifier);
    return exam;
  }

  /**
   * Visits the header of a Exam or Section.
   *
   * Maps all attributes, making sure that there are no duplicates, and that all
   * required attributes are present (and that no forbidden attributes are
   * present).
   */
  @Override
  public EvaluationExam visitHeader(ExamParser.HeaderContext ctx) {
    Map<String, Object> properties = new HashMap<>();

    ctx.properties().forEach(p -> {
      if (p.title() != null) {
        if (properties.containsKey("title"))
          raiseError(p, "Title already defined");

        properties.put("title", extractString(p.title().STRING().getText()));
      }
      if (p.description() != null) {
        if (properties.containsKey("description"))
          raiseError(p, "Description already defined");

        properties.put("description", extractString(p.description().STRING().getText()));
      }
      if (p.feedback() != null) {
        if (properties.containsKey("feedback"))
          raiseError(p, "Feedback already defined");

        properties.put("feedback", p.feedback().FDB_GRD_TYPE().getText());
      }
      if (p.grade() != null) {
        if (properties.containsKey("grade"))
          raiseError(p, "Grade already defined");

        properties.put("grade", p.grade().FDB_GRD_TYPE().getText());
      }
      if (p.score() != null) {
        if (properties.containsKey("score"))
          raiseError(p, "Score already defined");

        properties.put("score", Integer.parseInt(p.score().NUMBER().getText()));
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

    return exam;
  }

  @Override
  public EvaluationExam visitSections(ExamParser.SectionsContext ctx) {
    sections = new ArrayList<>();
    visitChildren(ctx);
    return exam;
  }

  @Override
  public EvaluationExam visitSection(ExamParser.SectionContext ctx) {
    section = new Section();

    visit(ctx.start_section());
    visit(ctx.header());
    // visit(ctx.questions());

    return exam;
  }

  @Override
  public EvaluationExam visitStart_section(ExamParser.Start_sectionContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    Identifier identifier = Identifier.valueOf(str);
    section.changeIdentifier(identifier);
    return exam;
  }

  private void initializeExam(Map<String, Object> properties) {
    Title title = Title.valueOf(properties.get("title").toString());
    exam.changeTitle(title);
    ExamInfo feedbackInfo = ExamInfo.convert(properties.get("feedback").toString());
    exam.changeFeedbackInfo(feedbackInfo);
    ExamInfo gradeInfo = ExamInfo.convert(properties.get("grade").toString());
    exam.changeGradeInfo(gradeInfo);
    Score score = Score.valueOf(Integer.parseInt(properties.get("score").toString()));
    exam.changeScore(score);
    if (properties.containsKey("description")) {
      Description description = Description.valueOf(properties.get("description").toString());
      exam.changeDescription(description);
    }
  }

  private void initializeSection(Map<String, Object> properties) {
    Title title = Title.valueOf(properties.get("title").toString());
    section.changeTitle(title);
    Score score = Score.valueOf(Integer.parseInt(properties.get("score").toString()));
    section.changeScore(score);
    if (properties.containsKey("description")) {
      Description description = Description.valueOf(properties.get("description").toString());
      section.changeDescription(description);
    }
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
    // throw new ParseException(lineNo, msg);
    System.out.println("Error in line " + lineNo + ": " + msg);
  }
}
