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
    public class PowerupsController : Controller
    {
        private readonly TrivialyContext _context;

        public PowerupsController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods

        // GET: api/Powerups
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Powerup>>> GetPowerup()
        {
            return await _context.Powerup.ToListAsync();
        }

        // GET: api/Powerups/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Powerup>> GetPowerup(int id)
        {
            var powerup = await _context.Powerup.FindAsync(id);

            if (powerup == null)
            {
                return NotFound();
            }

            return powerup;
        }

        // PUT: api/Powerups/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPowerup(int id, Powerup powerup)
        {
            if (id != powerup.PowerupId)
            {
                return BadRequest();
            }

            _context.Entry(powerup).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PowerupExists(id))
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

        // POST: api/Powerups
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Powerup>> PostPowerup(Powerup powerup)
        {
            _context.Powerup.Add(powerup);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPowerup", new { id = powerup.PowerupId }, powerup);
        }

        // DELETE: api/Powerups/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Powerup>> DeletePowerup(int id)
        {
            var powerup = await _context.Powerup.FindAsync(id);
            if (powerup == null)
            {
                return NotFound();
            }

            _context.Powerup.Remove(powerup);
            await _context.SaveChangesAsync();

            return powerup;
        }

        private bool PowerupExists(int id)
        {
            return _context.Powerup.Any(e => e.PowerupId == id);
        }

        #endregion
    }
}
