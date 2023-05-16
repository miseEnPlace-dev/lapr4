package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.exammanagement.domain.Exam;

public class ExamsMain {

  public static Exam parseWithVisitor(String file) {
    try {
      ExamLexer lexer = new ExamLexer(CharStreams.fromFileName(file));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      ExamParser parser = new ExamParser(tokens);
      ParseTree tree = parser.start();
      ExamsVisitor eval = new ExamsVisitor();

      return (Exam) eval.visit(tree);
    } catch (IOException e) {
      System.out.println("\nFile not found!");
    }

    return null;
  }

  public static void main(String[] args) {
    parseWithVisitor("exam.txt");
  }
}
