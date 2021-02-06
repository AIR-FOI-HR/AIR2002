using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Trivia.ly_Services.Data;
using Trivia.ly_Services.Models;

namespace Trivia.ly_Services.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class Quiz_UserController : Controller
    {
        private readonly TrivialyContext _context;

        public Quiz_UserController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods

        // GET: api/Quiz_User
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Quiz_User>>> GetQuiz_User()
        {
            return await _context.Quiz_User.ToListAsync();
        }

        // GET: api/Quiz_User/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Quiz_User>> GetQuiz_User(int id)
        {
            var quiz_User = await _context.Quiz_User.FindAsync(id);

            if (quiz_User == null)
            {
                return NotFound();
            }

            return quiz_User;
        }

        // PUT: api/Quiz_User/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutQuiz_User(int id, Quiz_User quiz_User)
        {
            if (id != quiz_User.Id_Quiz)
            {
                return BadRequest();
            }

            _context.Entry(quiz_User).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!Quiz_UserExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Quiz_User
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Quiz_User>> PostQuiz_User(Quiz_User quiz_User)
        {
            _context.Quiz_User.Add(quiz_User);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (Quiz_UserExists(quiz_User.Id_Quiz))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetQuiz_User", new { id = quiz_User.Id_Quiz }, quiz_User);
        }

        // DELETE: api/Quiz_User/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Quiz_User>> DeleteQuiz_User(int id)
        {
            var quiz_User = await _context.Quiz_User.FindAsync(id);
            if (quiz_User == null)
            {
                return NotFound();
            }

            _context.Quiz_User.Remove(quiz_User);
            await _context.SaveChangesAsync();

            return quiz_User;
        }

        private bool Quiz_UserExists(int id)
        {
            return _context.Quiz_User.Any(e => e.Id_Quiz == id);
        }
        #endregion
    }
}
