// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormativeExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormativeExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FormativeExamParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam(FormativeExamParser.ExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#sections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSections(FormativeExamParser.SectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(FormativeExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(FormativeExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(FormativeExamParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#numberOfQuestions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberOfQuestions(FormativeExamParser.NumberOfQuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#questionsType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionsType(FormativeExamParser.QuestionsTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(FormativeExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(FormativeExamParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(FormativeExamParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#startExam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartExam(FormativeExamParser.StartExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#endExam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndExam(FormativeExamParser.EndExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#startSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartSection(FormativeExamParser.StartSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormativeExamParser#endSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndSection(FormativeExamParser.EndSectionContext ctx);
}