// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuestionParser}.
 */
public interface QuestionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuestionParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(QuestionParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(QuestionParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QuestionParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numericalQuestion}.
	 * @param ctx the parse tree
	 */
	void enterNumericalQuestion(QuestionParser.NumericalQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numericalQuestion}.
	 * @param ctx the parse tree
	 */
	void exitNumericalQuestion(QuestionParser.NumericalQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceQuestion(QuestionParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoiceQuestion}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceQuestion(QuestionParser.MultipleChoiceQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswerQuestion(QuestionParser.ShortAnswerQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#shortAnswerQuestion}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswerQuestion(QuestionParser.ShortAnswerQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalseQuestion(QuestionParser.TrueFalseQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#trueFalseQuestion}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalseQuestion(QuestionParser.TrueFalseQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingQuestion}.
	 * @param ctx the parse tree
	 */
	void enterMatchingQuestion(QuestionParser.MatchingQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingQuestion}.
	 * @param ctx the parse tree
	 */
	void exitMatchingQuestion(QuestionParser.MatchingQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordsQuestion}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordsQuestion(QuestionParser.MissingWordsQuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordsQuestion}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordsQuestion(QuestionParser.MissingWordsQuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(QuestionParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(QuestionParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#feedback}.
	 * @param ctx the parse tree
	 */
	void enterFeedback(QuestionParser.FeedbackContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#feedback}.
	 * @param ctx the parse tree
	 */
	void exitFeedback(QuestionParser.FeedbackContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#shortAnswerCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterShortAnswerCorrectAnswer(QuestionParser.ShortAnswerCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#shortAnswerCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitShortAnswerCorrectAnswer(QuestionParser.ShortAnswerCorrectAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multipleChoiceCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMultipleChoiceCorrectAnswer(QuestionParser.MultipleChoiceCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multipleChoiceCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMultipleChoiceCorrectAnswer(QuestionParser.MultipleChoiceCorrectAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numericalCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterNumericalCorrectAnswer(QuestionParser.NumericalCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numericalCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitNumericalCorrectAnswer(QuestionParser.NumericalCorrectAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#numericalAcceptedError}.
	 * @param ctx the parse tree
	 */
	void enterNumericalAcceptedError(QuestionParser.NumericalAcceptedErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numericalAcceptedError}.
	 * @param ctx the parse tree
	 */
	void exitNumericalAcceptedError(QuestionParser.NumericalAcceptedErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(QuestionParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(QuestionParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(QuestionParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(QuestionParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matchingCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMatchingCorrectAnswer(QuestionParser.MatchingCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matchingCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMatchingCorrectAnswer(QuestionParser.MatchingCorrectAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missingWordsCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterMissingWordsCorrectAnswer(QuestionParser.MissingWordsCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missingWordsCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitMissingWordsCorrectAnswer(QuestionParser.MissingWordsCorrectAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#trueFalseCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void enterTrueFalseCorrectAnswer(QuestionParser.TrueFalseCorrectAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#trueFalseCorrectAnswer}.
	 * @param ctx the parse tree
	 */
	void exitTrueFalseCorrectAnswer(QuestionParser.TrueFalseCorrectAnswerContext ctx);
}