using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Quiz_User
    {
        [Key]
        public int Id_Quiz { get; set; }
        [Key]
        public int Id_User { get; set; }
        public int Score { get; set; }
    }
}
