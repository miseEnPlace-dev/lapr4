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
	 * Enter a parse tree produced by {@link QuestionParser#numerical_question}.
	 * @param ctx the parse tree
	 */
	void enterNumerical_question(QuestionParser.Numerical_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#numerical_question}.
	 * @param ctx the parse tree
	 */
	void exitNumerical_question(QuestionParser.Numerical_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multiple_choice_question}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice_question(QuestionParser.Multiple_choice_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multiple_choice_question}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice_question(QuestionParser.Multiple_choice_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#short_answer_question}.
	 * @param ctx the parse tree
	 */
	void enterShort_answer_question(QuestionParser.Short_answer_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#short_answer_question}.
	 * @param ctx the parse tree
	 */
	void exitShort_answer_question(QuestionParser.Short_answer_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#true_false_question}.
	 * @param ctx the parse tree
	 */
	void enterTrue_false_question(QuestionParser.True_false_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#true_false_question}.
	 * @param ctx the parse tree
	 */
	void exitTrue_false_question(QuestionParser.True_false_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#matching_question}.
	 * @param ctx the parse tree
	 */
	void enterMatching_question(QuestionParser.Matching_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matching_question}.
	 * @param ctx the parse tree
	 */
	void exitMatching_question(QuestionParser.Matching_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missing_words_question}.
	 * @param ctx the parse tree
	 */
	void enterMissing_words_question(QuestionParser.Missing_words_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missing_words_question}.
	 * @param ctx the parse tree
	 */
	void exitMissing_words_question(QuestionParser.Missing_words_questionContext ctx);
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
	 * Enter a parse tree produced by {@link QuestionParser#short_answer_correct_answer}.
	 * @param ctx the parse tree
	 */
	void enterShort_answer_correct_answer(QuestionParser.Short_answer_correct_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#short_answer_correct_answer}.
	 * @param ctx the parse tree
	 */
	void exitShort_answer_correct_answer(QuestionParser.Short_answer_correct_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#multiple_choice_correct_answer}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice_correct_answer(QuestionParser.Multiple_choice_correct_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#multiple_choice_correct_answer}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice_correct_answer(QuestionParser.Multiple_choice_correct_answerContext ctx);
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
	 * Enter a parse tree produced by {@link QuestionParser#matching_correct_answer}.
	 * @param ctx the parse tree
	 */
	void enterMatching_correct_answer(QuestionParser.Matching_correct_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#matching_correct_answer}.
	 * @param ctx the parse tree
	 */
	void exitMatching_correct_answer(QuestionParser.Matching_correct_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionParser#missing_words_correct_answer}.
	 * @param ctx the parse tree
	 */
	void enterMissing_words_correct_answer(QuestionParser.Missing_words_correct_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionParser#missing_words_correct_answer}.
	 * @param ctx the parse tree
	 */
	void exitMissing_words_correct_answer(QuestionParser.Missing_words_correct_answerContext ctx);
}