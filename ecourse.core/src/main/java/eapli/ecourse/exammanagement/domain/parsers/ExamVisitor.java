// Generated from /home/russo/isep/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ExamParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam(ExamParser.ExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#exam_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam_header(ExamParser.Exam_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(ExamParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(ExamParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#feedback}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeedback(ExamParser.FeedbackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#grade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrade(ExamParser.GradeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#sections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSections(ExamParser.SectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ExamParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#section_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_header(ExamParser.Section_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(ExamParser.QuestionsContext ctx);
}