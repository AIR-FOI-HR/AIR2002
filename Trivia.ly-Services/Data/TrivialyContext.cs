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
        }

        public DbSet<Category> Category { get; set; }
        public DbSet<Difficulty> Difficulty { get; set; }
        public DbSet<Question> Question { get; set; }
    }
}
