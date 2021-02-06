using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Quiz
    {
        [Key]
        public int QuizId { get; set; }
        public string Name { get; set; }
        public DateTime Start_Date { get; set; }
        public int Id_Category { get; set; }
        public string QuestionIds { get; set; }
    }

    public class GetAvaliableQuizesRequest
    {
        public int CategoryId { get; set; }
    }

    public class GetAvaliableQuizesResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<Quiz> QuizList { get; set; }
    }

    public class SetUserToQuizRequest
    {
        public int QuizId { get; set; }
        public string Username { get; set; }
        public int? Score { get; set; }
    }

    public class SetUserToQuizResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
    }

    public class GetUsersOnQuizRequest
    {
        public int QuizId { get; set; }
    }

    public class GetUsersOnQuizResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<User> Users { get; set; }
    }

    public class GetQuizScoreboardRequest
    {
        public int QuizId { get; set; }
    }

    public class GetQuizScoreboardResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<Scoreboard> Scoreboard { get; set; }
    }

    public class Scoreboard
    {
        public string Username { get; set; }
        public int Score { get; set; }
    }

    public class GetUserQuizRequest
    {
        public int UserId { get; set; }
    }

    public class GetUserQuizResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<Quiz> QuizList { get; set; }
    }
}
