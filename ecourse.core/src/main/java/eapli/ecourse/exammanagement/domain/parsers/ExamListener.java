// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam copy/Exam.g4 by ANTLR 4.9.2
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
	 * Enter a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(ExamParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(ExamParser.QuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(ExamParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(ExamParser.PropertiesContext ctx);
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
	 * Enter a parse tree produced by {@link ExamParser#score}.
	 * @param ctx the parse tree
	 */
	void enterScore(ExamParser.ScoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#score}.
	 * @param ctx the parse tree
	 */
	void exitScore(ExamParser.ScoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#start_exam}.
	 * @param ctx the parse tree
	 */
	void enterStart_exam(ExamParser.Start_examContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#start_exam}.
	 * @param ctx the parse tree
	 */
	void exitStart_exam(ExamParser.Start_examContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#end_exam}.
	 * @param ctx the parse tree
	 */
	void enterEnd_exam(ExamParser.End_examContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#end_exam}.
	 * @param ctx the parse tree
	 */
	void exitEnd_exam(ExamParser.End_examContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#start_section}.
	 * @param ctx the parse tree
	 */
	void enterStart_section(ExamParser.Start_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#start_section}.
	 * @param ctx the parse tree
	 */
	void exitStart_section(ExamParser.Start_sectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamParser#end_section}.
	 * @param ctx the parse tree
	 */
	void enterEnd_section(ExamParser.End_sectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamParser#end_section}.
	 * @param ctx the parse tree
	 */
	void exitEnd_section(ExamParser.End_sectionContext ctx);
}