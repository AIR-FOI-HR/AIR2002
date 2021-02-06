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
    public class User_PowerupController : Controller
    {
        private readonly TrivialyContext _context;

        public User_PowerupController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods


        // GET: api/User_Powerup
        [HttpGet]
        public async Task<ActionResult<IEnumerable<User_Powerup>>> GetUser_Powerup()
        {
            return await _context.User_Powerup.ToListAsync();
        }

        // GET: api/User_Powerup/5
        [HttpGet("{id}")]
        public async Task<ActionResult<User_Powerup>> GetUser_Powerup(int id)
        {
            var user_Powerup = await _context.User_Powerup.FindAsync(id);

            if (user_Powerup == null)
            {
                return NotFound();
            }

            return user_Powerup;
        }

        // PUT: api/User_Powerup/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutUser_Powerup(int id, User_Powerup user_Powerup)
        {
            if (id != user_Powerup.Id_User)
            {
                return BadRequest();
            }

            _context.Entry(user_Powerup).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!User_PowerupExists(id))
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

        // POST: api/User_Powerup
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<User_Powerup>> PostUser_Powerup(User_Powerup user_Powerup)
        {
            _context.User_Powerup.Add(user_Powerup);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (User_PowerupExists(user_Powerup.Id_User))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetUser_Powerup", new { id = user_Powerup.Id_User }, user_Powerup);
        }

        // DELETE: api/User_Powerup/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<User_Powerup>> DeleteUser_Powerup(int id)
        {
            var user_Powerup = await _context.User_Powerup.FindAsync(id);
            if (user_Powerup == null)
            {
                return NotFound();
            }

            _context.User_Powerup.Remove(user_Powerup);
            await _context.SaveChangesAsync();

            return user_Powerup;
        }

        private bool User_PowerupExists(int id)
        {
            return _context.User_Powerup.Any(e => e.Id_User == id);
        }

        #endregion
    }
}
