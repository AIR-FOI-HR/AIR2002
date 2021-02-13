using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Question
    {
        [Key]
        public int QuestionId { get; set; }
        public int Id_Category { get; set; }
        public int Id_Difficulty { get; set; }
        public string Question_text { get; set; }
        public string Correct_answer { get; set; }
        public string Incorrect_answer { get; set; }
        public int Id_Question_Type { get; set; }
    }

    public class QuestionsRequest
    {
        public int NumberOfQuestions { get; set; }
        public string DifficultyName { get; set; }
        public string CategoryName { get; set; }
    }

    public class QuestionsResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<QuestionsListResponse> Questions { get; set; }
    }
    public class QuestionsListResponse
    {
        public string QuestionDifficulty { get; set; }
        public string QuestionCategory { get; set; }
        public string QuestionText { get; set; }
        public string CorrectAnswer { get; set; }
        public string IncorrectAnswers { get; set; }
        public string QuestionTypeName { get; set; }
    }

    public class GetQuestionByIdRequest
    {
        public int QuestionId { get; set; }
    }

    public class GetQuestionByIdResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public QuestionsListResponse Question { get; set; }
    }
}
