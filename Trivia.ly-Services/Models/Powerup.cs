using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class Powerup
    {
        [Key]
        public int PowerupId { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
    }
}
