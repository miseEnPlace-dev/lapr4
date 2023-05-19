package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.questionmanagement.domain.Question;

public class QuestionsParser {
  public static List<Question> parseWithVisitor(String file) throws IOException, ParseException {
    QuestionLexer lexer = new QuestionLexer(CharStreams.fromFileName(file));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    QuestionParser parser = new QuestionParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    QuestionsVisitor eval = new QuestionsVisitor();
    return (List<Question>) eval.visit(tree);
  }
}
