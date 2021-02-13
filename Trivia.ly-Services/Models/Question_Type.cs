using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Question_Type
    {
        [Key]
        public int Question_TypeId { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
    }

    public class GetQuestionTypesResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<Question_Type> Question_Types { get; set; }
    }
}
