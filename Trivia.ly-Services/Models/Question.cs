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
        public string Question_text { get; set; }
        public string Correct_answer { get; set; }
        public string Incorrect_answer { get; set; }

    }
}
