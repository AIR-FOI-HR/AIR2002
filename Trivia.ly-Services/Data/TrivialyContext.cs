using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Trivia.ly_Services.Models;

namespace Trivia.ly_Services.Data
{
    public class TrivialyContext : DbContext
    {
        public TrivialyContext(DbContextOptions options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            builder.Entity<Quiz_User>().HasKey(c => new { c.Id_Quiz, c.Id_User });
            builder.Entity<User_Powerup>().HasKey(c => new { c.Id_User, c.Id_Powerup });
        }

        public DbSet<Category> Category { get; set; }
        public DbSet<Difficulty> Difficulty { get; set; }
        public DbSet<Question> Question { get; set; }
        public DbSet<User> User { get; set; }
        public DbSet<Powerup> Powerup { get; set; }
        public DbSet<Quiz_User> Quiz_User { get; set; }
        public DbSet<User_Powerup> User_Powerup { get; set; }
        public DbSet<Quiz> Quiz { get; set; }
        public DbSet<Question_Type> Question_Type { get; set; }
    }
}
