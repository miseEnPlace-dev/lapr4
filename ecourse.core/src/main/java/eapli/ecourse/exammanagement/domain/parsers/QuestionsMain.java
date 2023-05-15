package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.questionmanagement.domain.Question;

public class QuestionsMain {
  public static List<Question> parseWithVisitor(String file) {
    try {
      QuestionLexer lexer = new QuestionLexer(CharStreams.fromFileName(file));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      QuestionParser parser = new QuestionParser(tokens);
      ParseTree tree = parser.start();
      QuestionsVisitor eval = new QuestionsVisitor();

      return (List<Question>) eval.visit(tree);
    } catch (IOException e) {
      System.out.println("\nFile not found!");
    } catch (Exception e) {
      System.out.println("\nThe specified questions don't follow the required format.");
      System.out.println(e);
    }

    return null;
  }

  public static void main(String[] args) {
    parseWithVisitor("questions.txt");
  }
}
