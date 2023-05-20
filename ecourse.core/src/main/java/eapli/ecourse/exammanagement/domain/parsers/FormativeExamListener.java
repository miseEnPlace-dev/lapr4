// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormativeExamParser}.
 */
public interface FormativeExamListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(FormativeExamParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(FormativeExamParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void enterExam(FormativeExamParser.ExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#exam}.
	 * @param ctx the parse tree
	 */
	void exitExam(FormativeExamParser.ExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#sections}.
	 * @param ctx the parse tree
	 */
	void enterSections(FormativeExamParser.SectionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#sections}.
	 * @param ctx the parse tree
	 */
	void exitSections(FormativeExamParser.SectionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(FormativeExamParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(FormativeExamParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#numberOfQuestions}.
	 * @param ctx the parse tree
	 */
	void enterNumberOfQuestions(FormativeExamParser.NumberOfQuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#numberOfQuestions}.
	 * @param ctx the parse tree
	 */
	void exitNumberOfQuestions(FormativeExamParser.NumberOfQuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#questionsType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionsType(FormativeExamParser.QuestionsTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#questionsType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionsType(FormativeExamParser.QuestionsTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(FormativeExamParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(FormativeExamParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(FormativeExamParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(FormativeExamParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#startExam}.
	 * @param ctx the parse tree
	 */
	void enterStartExam(FormativeExamParser.StartExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#startExam}.
	 * @param ctx the parse tree
	 */
	void exitStartExam(FormativeExamParser.StartExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#endExam}.
	 * @param ctx the parse tree
	 */
	void enterEndExam(FormativeExamParser.EndExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#endExam}.
	 * @param ctx the parse tree
	 */
	void exitEndExam(FormativeExamParser.EndExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#startSection}.
	 * @param ctx the parse tree
	 */
	void enterStartSection(FormativeExamParser.StartSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#startSection}.
	 * @param ctx the parse tree
	 */
	void exitStartSection(FormativeExamParser.StartSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormativeExamParser#endSection}.
	 * @param ctx the parse tree
	 */
	void enterEndSection(FormativeExamParser.EndSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormativeExamParser#endSection}.
	 * @param ctx the parse tree
	 */
	void exitEndSection(FormativeExamParser.EndSectionContext ctx);
}