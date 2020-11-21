using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Difficulty
    {
        [Key]
        public int DifficultyId { get; set; }
        public string Name { get; set; }
    }
}
