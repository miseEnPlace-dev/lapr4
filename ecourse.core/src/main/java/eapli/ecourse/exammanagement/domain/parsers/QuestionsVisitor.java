package eapli.ecourse.exammanagement.domain.parsers;

import eapli.ecourse.questionmanagement.domain.MatchingQuestion;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;

public class QuestionsVisitor extends QuestionBaseVisitor<Question> {
  private Question question;

  @Override
  public Question visitStart(QuestionParser.StartContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Question visitMatching_question(QuestionParser.Matching_questionContext ctx) {
    this.question = new MatchingQuestion(QuestionType.FORMATIVE);
    visit(ctx.body());

    return question;
  }

  @Override
  public Question visitBody(QuestionParser.BodyContext ctx) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ctx.STRING().getText().length(); i++)
      sb.append(ctx.STRING().getText().charAt(i));

    QuestionBody b = new QuestionBody(sb.toString());
    this.question.changeBody(b);
    return null;
  }
}
