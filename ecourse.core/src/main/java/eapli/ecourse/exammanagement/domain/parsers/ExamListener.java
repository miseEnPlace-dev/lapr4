// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamParser}.
 */
public interface ExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExamParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExamParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void enterExam(ExamParser.ExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void exitExam(ExamParser.ExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#exam_header}.
	 * @param ctx the parse tree
	 */
	void enterExam_header(ExamParser.Exam_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#exam_header}.
	 * @param ctx the parse tree
	 */
	void exitExam_header(ExamParser.Exam_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(ExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(ExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(ExamParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(ExamParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 */
	void enterGrade(ExamParser.GradeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 */
	void exitGrade(ExamParser.GradeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#sections}.
	 * @param ctx the parse tree
	 */
	void enterSections(ExamParser.SectionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#sections}.
	 * @param ctx the parse tree
	 */
	void exitSections(ExamParser.SectionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#section_header}.
	 * @param ctx the parse tree
	 */
	void enterSection_header(ExamParser.Section_headerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#section_header}.
	 * @param ctx the parse tree
	 */
	void exitSection_header(ExamParser.Section_headerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(ExamParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(ExamParser.QuestionsContext ctx);
}