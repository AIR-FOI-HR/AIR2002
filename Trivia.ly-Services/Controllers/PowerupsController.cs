using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
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

        #region Trivialy methods

        [HttpPost ("GetUserPowerups")]
        public string GetUserPowerups([FromBody] GetUserPowerupsRequest body)
        {
            try
            {
                var user = _context.User.Where(u => u.Username == body.Username).FirstOrDefault();
                if(user != null)
                {
                    var pupsId = _context.User_Powerup.Where(up => up.Id_User == user.UserId).ToList();
                    var powerupsList = new List<UserPowerups>();

                    foreach (var pup in pupsId)
                    {
                        var powerup = _context.Powerup.Where(p => p.PowerupId == pup.Id_Powerup).FirstOrDefault();
                        powerupsList.Add(new UserPowerups()
                        {
                            PowerupId = powerup.PowerupId,
                            Name = powerup.Name,
                            Amount = pup.Amount
                        });
                    }

                    var response = new GetUserPowerupsResponse()
                    {
                        Status = 1,
                        UserPowerups = powerupsList
                    };
                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetUserPowerupsResponse()
                    {
                        Status = -1,
                        Text = "User not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new GetUserPowerupsResponse()
                {
                    Status = -9,
                    Text = e.InnerException.ToString()
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        [HttpPost ("SetUserPowerupStatus")]
        public string SetUserPowerupStatus([FromBody] SetUserPowerupStatusRequest body)
        {
            try
            {
                var user = _context.User.Where(u => u.Username == body.Username).FirstOrDefault();
                var powerup = _context.Powerup.Where(p => p.PowerupId == body.PowerupId).FirstOrDefault();
                if(user != null)
                {
                    if(powerup != null)
                    {
                        var usrpup = _context.User_Powerup.Where(up => up.Id_Powerup == powerup.PowerupId && up.Id_User == user.UserId).FirstOrDefault();
                        if(usrpup != null)
                        {
                            usrpup.Amount = body.Amount;
                            _context.User_Powerup.Update(usrpup);
                            _context.SaveChanges();

                            var response = new SetUserPowerupStatusResponse()
                            {
                                Status = 1
                            };
                            return JsonConvert.SerializeObject(response);
                        }
                        else
                        {
                            _context.User_Powerup.Add(new User_Powerup()
                            {
                                Id_Powerup = powerup.PowerupId,
                                Id_User = user.UserId,
                                Amount = body.Amount
                            });
                            _context.SaveChanges();

                            var response = new SetUserPowerupStatusResponse()
                            {
                                Status = 1
                            };
                            return JsonConvert.SerializeObject(response);
                        }
                    }
                    else
                    {
                        var response = new SetUserPowerupStatusResponse()
                        {
                            Status = -2,
                            Text = "Powerup not found"
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                }
                else
                {
                    var response = new SetUserPowerupStatusResponse()
                    {
                        Status = -1,
                        Text = "User not found"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch(Exception e)
            {
                var response = new SetUserPowerupStatusResponse()
                {
                    Status = -9,
                    Text = e.InnerException.ToString()
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        [HttpPost ("GetPowerupInfo")]
        public string GetPowerupInfo()
        {
            try
            {
                var powerups = _context.Powerup.Select(x => x).ToList();
                var response = new GetPowerupInfoResponse()
                {
                    Status = 1,
                    Powerups = powerups
                };

                return JsonConvert.SerializeObject(response);
            }
            catch(Exception e)
            {
                var response = new GetPowerupInfoResponse()
                {
                    Status = -9,
                    Text = e.InnerException.ToString()
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        #endregion
    }
}
