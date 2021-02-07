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

    public class GetUserPowerupsRequest
    {
        public int UserId { get; set; }
    }

    public class GetUserPowerupsResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<UserPowerups> UserPowerups { get; set; }
    }

    public class UserPowerups
    {
        public int PowerupId { get; set; }
        public string Name { get; set; }
        public int Amount { get; set; }
    }

    public class GetPowerupInfoResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public List<Powerup> Powerups { get; set; }
    }

    public class SetUserPowerupStatusRequest
    {
        public int UserId { get; set; }
        public int PowerupId { get; set; }
        public int Amount { get; set; }
    }

    public class SetUserPowerupStatusResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
    }
}
