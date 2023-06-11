// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
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
	 * Visit a parse tree produced by {@link QuestionParser#numericalQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalQuestion(QuestionParser.NumericalQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceQuestion(QuestionParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswerQuestion(QuestionParser.ShortAnswerQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalseQuestion(QuestionParser.TrueFalseQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingQuestion(QuestionParser.MatchingQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordsQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordsQuestion(QuestionParser.MissingWordsQuestionContext ctx);
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
	 * Visit a parse tree produced by {@link QuestionParser#score}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore(QuestionParser.ScoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#shortAnswerCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortAnswerCorrectAnswer(QuestionParser.ShortAnswerCorrectAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#multipleChoiceCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleChoiceCorrectAnswer(QuestionParser.MultipleChoiceCorrectAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numericalCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalCorrectAnswer(QuestionParser.NumericalCorrectAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#numericalAcceptedError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalAcceptedError(QuestionParser.NumericalAcceptedErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(QuestionParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordsOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordsOption(QuestionParser.MissingWordsOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(QuestionParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#matchingCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingCorrectAnswer(QuestionParser.MatchingCorrectAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#missingWordsCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissingWordsCorrectAnswer(QuestionParser.MissingWordsCorrectAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionParser#trueFalseCorrectAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueFalseCorrectAnswer(QuestionParser.TrueFalseCorrectAnswerContext ctx);
}