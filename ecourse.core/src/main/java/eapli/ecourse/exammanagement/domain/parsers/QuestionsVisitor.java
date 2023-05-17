package eapli.ecourse.exammanagement.domain.parsers;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.questionmanagement.domain.Feedback;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.questionmanagement.domain.MatchingQuestion;
import eapli.ecourse.questionmanagement.domain.MissingWordsQuestion;
import eapli.ecourse.questionmanagement.domain.MultipleChoiceQuestion;
import eapli.ecourse.questionmanagement.domain.NumericalQuestion;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;
import eapli.ecourse.questionmanagement.domain.TrueFalseQuestion;

public class QuestionsVisitor extends QuestionBaseVisitor<List<Question>> {
  List<Question> questions = new ArrayList<>();
  private Question question;
  private double real;
  private boolean bool;

  private String extractString(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < s.length() - 1; i++)
      sb.append(s.charAt(i));
    return sb.toString();
  }

  /*
   * private void raiseError(ParserRuleContext ctx, String msg) {
   * Token token = ctx.getStart();
   * int lineNo = token.getLine();
   * throw new ParseException(lineNo, msg);
   * }
   */

  @Override
  public List<Question> visitStart(QuestionParser.StartContext ctx) {
    visitChildren(ctx);
    return questions;
  }

  @Override
  public List<Question> visitMatchingQuestion(QuestionParser.MatchingQuestionContext ctx) {
    this.question = new MatchingQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    MatchingQuestion q = (MatchingQuestion) this.question;

    ctx.matchingCorrectAnswer().forEach(a -> {
      String matchIdentifier = a.NUMBER(0).getText();
      String optionIdentifier = a.NUMBER(1).getText();

      q.addCorrectMatch(matchIdentifier, optionIdentifier);
    });

    ctx.option().forEach(o -> {
      String id = o.NUMBER().getText();
      Identifier identifier = Identifier.valueOf(id);

      String body = extractString(o.STRING(0).getText());

      if (o.STRING().size() > 1) {
        String feedback = extractString(o.STRING(1).getText());
        Feedback f = Feedback.valueOf(feedback);
        q.addFeedback(identifier, f);
      }

      q.addOption(identifier, body);
    });

    ctx.match().forEach(m -> {
      String id = m.NUMBER().getText();
      Identifier identifier = Identifier.valueOf(id);

      String body = extractString(m.STRING().getText());

      q.addMatch(identifier, body);
    });

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitNumericalQuestion(QuestionParser.NumericalQuestionContext ctx) {
    this.question = new NumericalQuestion(QuestionType.FORMATIVE);

    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    NumericalQuestion q = (NumericalQuestion) this.question;

    visit(ctx.numericalCorrectAnswer());
    q.changeCorrectAnswer(real);
    visit(ctx.numericalAcceptedError());
    q.changeAcceptedError(real);

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitMultipleChoiceQuestion(QuestionParser.MultipleChoiceQuestionContext ctx) {
    this.question = new MultipleChoiceQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    MultipleChoiceQuestion q = (MultipleChoiceQuestion) this.question;

    ctx.multipleChoiceCorrectAnswer().forEach(a -> {
      String id = a.NUMBER().getText();

      double grade = 0;
      if (a.REAL_NUMBER() == null)
        grade = 1;
      else
        grade = Double.parseDouble(a.REAL_NUMBER().getText());

      Identifier identifier = Identifier.valueOf(id);

      q.addCorrectAnswer(identifier, grade);
    });

    ctx.option().forEach(o -> {
      String id = o.NUMBER().getText();
      Identifier identifier = Identifier.valueOf(id);

      String body = extractString(o.STRING(0).getText());

      if (o.STRING().size() > 1) {
        String feedback = extractString(o.STRING(1).getText());
        Feedback f = Feedback.valueOf(feedback);
        q.addFeedback(identifier, f);
      }

      q.addOption(identifier, body);
    });

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitShortAnswerQuestion(QuestionParser.ShortAnswerQuestionContext ctx) {
    this.question = new ShortAnswerQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    ShortAnswerQuestion q = (ShortAnswerQuestion) this.question;

    ctx.shortAnswerCorrectAnswer().forEach(a -> {
      String answer = extractString(a.STRING().getText());
      double grade = Double.parseDouble(a.REAL_NUMBER().getText());

      q.addCorrectAnswer(answer, grade);
    });

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitTrueFalseQuestion(QuestionParser.TrueFalseQuestionContext ctx) {
    this.question = new TrueFalseQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    TrueFalseQuestion q = (TrueFalseQuestion) this.question;

    visit(ctx.trueFalseCorrectAnswer());
    q.changeCorrectAnswer(bool);

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitMissingWordsQuestion(QuestionParser.MissingWordsQuestionContext ctx) {
    this.question = new MissingWordsQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());
    if (ctx.feedback() != null)
      visit(ctx.feedback());

    MissingWordsQuestion q = (MissingWordsQuestion) this.question;

    ctx.missingWordsCorrectAnswer().STRING().forEach(s -> {
      String missingWord = extractString(s.getText());
      q.addMissingWord(missingWord);
    });

    questions.add(q);
    return questions;
  }

  @Override
  public List<Question> visitBody(QuestionParser.BodyContext ctx) {
    String body = extractString(ctx.STRING().getText());

    QuestionBody b = new QuestionBody(body);
    this.question.changeBody(b);
    return null;
  }

  @Override
  public List<Question> visitFeedback(QuestionParser.FeedbackContext ctx) {
    String feedback = extractString(ctx.STRING().getText());

    Feedback f = Feedback.valueOf(feedback);
    this.question.changeFeedback(f);
    return null;
  }

  @Override
  public List<Question> visitNumericalCorrectAnswer(QuestionParser.NumericalCorrectAnswerContext ctx) {
    this.real = Double.parseDouble(ctx.REAL_NUMBER().getText());
    return null;
  }

  @Override
  public List<Question> visitNumericalAcceptedError(QuestionParser.NumericalAcceptedErrorContext ctx) {
    this.real = Double.parseDouble(ctx.REAL_NUMBER().getText());
    return null;
  }

  @Override
  public List<Question> visitTrueFalseCorrectAnswer(QuestionParser.TrueFalseCorrectAnswerContext ctx) {
    if (ctx.TRUE() != null)
      this.bool = true;
    else
      this.bool = false;

    return null;
  }
}
