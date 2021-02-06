using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class User_Powerup
    {
        [Key]
        public int Id_User { get; set; }
        [Key]
        public int Id_Powerup { get; set; }
        public int Amount { get; set; }
    }
}
