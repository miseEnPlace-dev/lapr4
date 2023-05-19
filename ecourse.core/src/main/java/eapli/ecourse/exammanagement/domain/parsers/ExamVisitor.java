// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam copy/Exam.g4 by ANTLR 4.9.2
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
	 * Visit a parse tree produced by {@link ExamParser#questions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(ExamParser.QuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(ExamParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(ExamParser.PropertiesContext ctx);
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
	 * Visit a parse tree produced by {@link ExamParser#score}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore(ExamParser.ScoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#start_exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_exam(ExamParser.Start_examContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#end_exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd_exam(ExamParser.End_examContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#start_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart_section(ExamParser.Start_sectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamParser#end_section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd_section(ExamParser.End_sectionContext ctx);
}