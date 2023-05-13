// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QuestionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QuestionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QuestionParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(QuestionParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numerical_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical_question(QuestionParser.Numerical_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multiple_choice_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice_question(QuestionParser.Multiple_choice_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#short_answer_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_answer_question(QuestionParser.Short_answer_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#true_false_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue_false_question(QuestionParser.True_false_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matching_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching_question(QuestionParser.Matching_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missing_words_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissing_words_question(QuestionParser.Missing_words_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(QuestionParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(QuestionParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#short_answer_correct_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_answer_correct_answer(QuestionParser.Short_answer_correct_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multiple_choice_correct_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice_correct_answer(QuestionParser.Multiple_choice_correct_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(QuestionParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(QuestionParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matching_correct_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching_correct_answer(QuestionParser.Matching_correct_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missing_words_correct_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissing_words_correct_answer(QuestionParser.Missing_words_correct_answerContext ctx);
}