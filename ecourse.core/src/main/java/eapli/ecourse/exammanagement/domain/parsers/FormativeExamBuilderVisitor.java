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
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequestBuilder;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequestBuilder;

public class FormativeExamBuilderVisitor extends FormativeExamBaseVisitor<FormativeExamRequestBuilder> {
  private FormativeExamRequestBuilder builder;
  private FormativeExamSectionRequest section;
  private FormativeExamSectionRequestBuilder sectionBuilder;
  private List<FormativeExamSectionRequest> sections;

  @Override
  public FormativeExamRequestBuilder visitStart(FormativeExamParser.StartContext ctx) {
    visit(ctx.exam());
    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitExam(FormativeExamParser.ExamContext ctx) {
    builder = new FormativeExamRequestBuilder();

    visit(ctx.startExam());
    visit(ctx.header());
    visit(ctx.sections());

    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitStartExam(FormativeExamParser.StartExamContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    ExamIdentifier identifier = ExamIdentifier.valueOf(str);
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
  public FormativeExamRequestBuilder visitHeader(FormativeExamParser.HeaderContext ctx) {
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
      };
      String[] forbiddenProps = {
          "feedback",
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
  public FormativeExamRequestBuilder visitSections(FormativeExamParser.SectionsContext ctx) {
    sections = new ArrayList<FormativeExamSectionRequest>();
    visitChildren(ctx);
    builder.withSections(sections);
    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitSection(FormativeExamParser.SectionContext ctx) {
    sectionBuilder = new FormativeExamSectionRequestBuilder();

    visit(ctx.startSection());
    visit(ctx.header());
    visit(ctx.numberOfQuestions());
    visit(ctx.questionsType());

    sectionBuilder.withNumberOfQuestions(section.numberOfQuestions());
    sectionBuilder.withQuestionsType(section.questionsType());

    sections.add(sectionBuilder.build());

    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitNumberOfQuestions(FormativeExamParser.NumberOfQuestionsContext ctx) {
    int numberOfQuestions = Integer.parseInt(ctx.NUMBER().getText());
    section.changeNumberOfQuestions(numberOfQuestions);
    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitQuestionsType(FormativeExamParser.QuestionsTypeContext ctx) {
    String str = ctx.getChild(1).getText();
    section.changeQuestionsType(str);
    sections.add(section);
    return builder;
  }

  @Override
  public FormativeExamRequestBuilder visitStartSection(FormativeExamParser.StartSectionContext ctx) {
    String str = ctx.IDENTIFIER().getText();
    SectionIdentifier identifier = SectionIdentifier.valueOf(str);
    section.changeIdentifier(identifier);
    return builder;
  }

  private void initializeExam(Map<String, Object> properties) {
    ExamTitle title = ExamTitle.valueOf(properties.get("title").toString());
    builder.withTitle(title);

    if (properties.containsKey("description")) {
      ExamDescription description = ExamDescription.valueOf(properties.get("description").toString());
      builder.withDescription(description);
    } else {
      builder.withDescription(ExamDescription.valueOf(""));
    }
  }

  private void initializeSection(Map<String, Object> properties) {
    SectionTitle title = SectionTitle.valueOf(properties.get("title").toString());
    section.changeTitle(title);

    if (properties.containsKey("description")) {
      SectionDescription description = SectionDescription.valueOf(properties.get("description").toString());
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
    throw new ParseException(lineNo, msg);
  }
}
