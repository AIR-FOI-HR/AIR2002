using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Linq;
using System.Text.Json;
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
    public class UsersController : Controller
    {
        private readonly TrivialyContext _context;

        public UsersController(TrivialyContext context)
        {
            _context = context;
        }

        // GET: api/Users
        [HttpGet]
        public async Task<ActionResult<IEnumerable<User>>> GetUser()
        {
            return await _context.User.ToListAsync();
        }

        // GET: api/Users/5
        [HttpGet("{id}")]
        public async Task<ActionResult<User>> GetUser(int id)
        {
            var user = await _context.User.FindAsync(id);

            if (user == null)
            {
                return NotFound();
            }

            return user;
        }

        // PUT: api/Users/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutUser(int id, User user)
        {
            if (id != user.UserId)
            {
                return BadRequest();
            }

            _context.Entry(user).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UserExists(id))
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

        // POST: api/Users
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<User>> PostUser(User user)
        {
            _context.User.Add(user);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetUser", new { id = user.UserId }, user);
        }

        // DELETE: api/Users/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<User>> DeleteUser(int id)
        {
            var user = await _context.User.FindAsync(id);
            if (user == null)
            {
                return NotFound();
            }

            _context.User.Remove(user);
            await _context.SaveChangesAsync();

            return user;
        }

        private bool UserExists(int id)
        {
            return _context.User.Any(e => e.UserId == id);
        }

        // /api/Users/Login
        [HttpPost("Login")]
        public string Login([FromBody] LoginRequest body)
        {

            //TODO : Password hashing

            var username = body.Username;
            var password = body.Password;
            var user = _context.User
                .Where(u => u.Username == username)
                .FirstOrDefault<User>();
            try
            {
                if (user != null)
                {
                    if (user.PasswordTmp == password)
                    {
                        var response = new LoginResponse()
                        {
                            Status = 1,
                            Text = "",
                            Username = user.Username,
                            Email = user.Email,
                            Firstname = user.FirstName,
                            Lastname = user.LastName,
                            Score = user.Score,
                            Life = user.Life
                        };

                        return JsonConvert.SerializeObject(response);
                    }
                    else
                    {
                        var response = new LoginResponse()
                        {
                            Status = -1,
                            Text = "Incorrect password."
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                }
                else
                {
                    var response = new LoginResponse()
                    {
                        Status = -2,
                        Text = "User not found."
                    };
                    return JsonConvert.SerializeObject(response); ;
                }
            }
            catch (Exception e)
            {
                var response = new LoginResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }

        }

        [HttpPost("Register")]
        public string RegisterUser([FromBody] RegisterRequest body)
        {

            //TODO : password hashing
            var username = body.Username;
            var email = body.Email;

            var firstName = body.FirstName;
            var lastName = body.LastName;
            var password = body.Password;

            var lives = 5;
            try
            {
                if (!_context.User.Any(u => u.Email == email))
                {
                    if (!_context.User.Any(u => u.Username == body.Username))
                    {
                        var newUser = new User()
                        {
                            Username = username,
                            FirstName = firstName,
                            LastName = lastName,
                            Email = email,
                            Life = lives,
                            PasswordTmp = password,
                            PasswordHash = "",
                            PasswordSalt = "",
                            Score = 0
                        };

                        _context.User.Add(newUser);
                        _context.SaveChanges();

                        var response = new RegisterResponse()
                        {
                            Status = 1
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                    else
                    {
                        var response = new RegisterResponse()
                        {
                            Status = -1,
                            Text = "Username is taken."
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                }
                else
                {
                    var response = new RegisterResponse()
                    {
                        Status = -2,
                        Text = "E-mail is taken."
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new RegisterResponse()
                {
                    Status = -3,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }

        }

    }
}
