package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequestBuilder;

public class ANTLR4FormativeExamParser implements GrammarParser<FormativeExamRequestBuilder> {
  public FormativeExamRequestBuilder parseFromFile(String filePath) throws IOException, ParseException {
    FormativeExamLexer lexer = new FormativeExamLexer(CharStreams.fromFileName(filePath));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    FormativeExamParser parser = new FormativeExamParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    FormativeExamBuilderVisitor eval = new FormativeExamBuilderVisitor();
    return (FormativeExamRequestBuilder) eval.visit(tree);
  }

  public FormativeExamRequestBuilder parseFromString(String str) throws ParseException {
    FormativeExamLexer lexer = new FormativeExamLexer(CharStreams.fromString(str));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    FormativeExamParser parser = new FormativeExamParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    FormativeExamBuilderVisitor eval = new FormativeExamBuilderVisitor();
    return (FormativeExamRequestBuilder) eval.visit(tree);
  }
}
