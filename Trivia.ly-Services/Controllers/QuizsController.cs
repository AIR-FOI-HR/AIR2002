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
    public class QuizsController : Controller
    {
        private readonly TrivialyContext _context;

        public QuizsController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods

        // GET: api/Quizs
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Quiz>>> GetQuiz()
        {
            return await _context.Quiz.ToListAsync();
        }

        // GET: api/Quizs/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Quiz>> GetQuiz(int id)
        {
            var quiz = await _context.Quiz.FindAsync(id);

            if (quiz == null)
            {
                return NotFound();
            }

            return quiz;
        }

        // PUT: api/Quizs/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutQuiz(int id, Quiz quiz)
        {
            if (id != quiz.QuizId)
            {
                return BadRequest();
            }

            _context.Entry(quiz).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!QuizExists(id))
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

        // POST: api/Quizs
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Quiz>> PostQuiz(Quiz quiz)
        {
            _context.Quiz.Add(quiz);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetQuiz", new { id = quiz.QuizId }, quiz);
        }

        // DELETE: api/Quizs/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Quiz>> DeleteQuiz(int id)
        {
            var quiz = await _context.Quiz.FindAsync(id);
            if (quiz == null)
            {
                return NotFound();
            }

            _context.Quiz.Remove(quiz);
            await _context.SaveChangesAsync();

            return quiz;
        }

        private bool QuizExists(int id)
        {
            return _context.Quiz.Any(e => e.QuizId == id);
        }

        #endregion

        #region Trivialy methods

        [HttpPost("GetAvaliableQuizes")]
        public string GetAvaliableQuizes([FromBody] GetAvaliableQuizesRequest body)
        {
            try
            {
                var quizes = _context.Quiz.Where(c => c.Id_Category == body.CategoryId && c.Start_Date < DateTime.Now).OrderByDescending(c => c.QuestionIds).ToList();
                if (quizes.Count > 0)
                {
                    var response = new GetAvaliableQuizesResponse()
                    {
                        Status = 1,
                        QuizList = quizes
                    };
                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetAvaliableQuizesResponse()
                    {
                        Status = -1,
                        Text = "No avaliable quizes"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new GetAvaliableQuizesResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }

        }

        [HttpPost("SetUserToQuiz")]
        public string SetUserToQuiz([FromBody] SetUserToQuizRequest body)
        {
            try
            {
                int quizId = body.QuizId;
                string username = body.Username;
                int score = body.Score == null ? 0 : body.Score.Value;

                var user = _context.User.Where(u => u.Username == username).FirstOrDefault();
                var quiz = _context.Quiz.Where(q => q.QuizId == quizId).FirstOrDefault();
                if (user != null)
                {
                    if (quiz != null)
                    {
                        _context.Quiz_User.Add(new Quiz_User()
                        {
                            Id_Quiz = quiz.QuizId,
                            Id_User = user.UserId,
                            Score = score
                        });
                        _context.SaveChanges();

                        var response = new SetUserToQuizResponse()
                        {
                            Status = 1
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                    else
                    {
                        var response = new SetUserToQuizResponse()
                        {
                            Status = -1,
                            Text = "Quiz not found!"
                        };
                        return JsonConvert.SerializeObject(response);
                    }
                }
                else
                {
                    var response = new SetUserToQuizResponse()
                    {
                        Status = -2,
                        Text = "User not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new SetUserToQuizResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        [HttpPost("GetUsersOnQuiz")]
        public string GetUsersOnQuiz([FromBody] GetUsersOnQuizRequest body)
        {
            try
            {
                var quiz = _context.Quiz.Where(q => q.QuizId == body.QuizId).FirstOrDefault();

                if (quiz != null)
                {
                    var userIds = _context.Quiz_User.Where(qu => qu.Id_Quiz == quiz.QuizId).Select(qu => qu.Id_User).ToList();
                    var userList = new List<User>();

                    foreach (var userId in userIds)
                    {
                        userList.Add(_context.User.Where(u => u.UserId == userId).FirstOrDefault());
                    }

                    var response = new GetUsersOnQuizResponse()
                    {
                        Status = 1,
                        Users = userList
                    };
                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetUsersOnQuizResponse()
                    {
                        Status = -1,
                        Text = "Quiz not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new GetUsersOnQuizResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        [HttpPost("GetQuizScoreboard")]
        public string GetQuizScoreboard([FromBody] GetQuizScoreboardRequest body)
        {
            try
            {
                var quiz = _context.Quiz.Where(q => q.QuizId == body.QuizId).FirstOrDefault();
                if (quiz != null)
                {
                    var userIds = _context.Quiz_User.Where(q => q.Id_Quiz == quiz.QuizId).OrderByDescending(q => q.Score).ToList();
                    var scoreboard = new List<Scoreboard>();
                    foreach (var userId in userIds)
                    {
                        var usr = _context.User.Where(u => u.UserId == userId.Id_User).FirstOrDefault();
                        scoreboard.Add(new Scoreboard()
                        {
                            Score = userId.Score,
                            Username = usr.Username
                        });
                    }

                    var response = new GetQuizScoreboardResponse()
                    {
                        Status = 1,
                        Scoreboard = scoreboard
                    };

                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetQuizScoreboardResponse()
                    {
                        Status = -1,
                        Text = "Quiz not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new GetQuizScoreboardResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        [HttpPost("GetUserQuiz")]
        public string GetUserQuiz([FromBody]GetUserQuizRequest body)
        {
            try
            {
                var user = _context.User.Where(u => u.UserId == body.UserId).FirstOrDefault();
                if (user != null)
                {
                    var quizIds = _context.Quiz_User.Where(qu => qu.Id_User == user.UserId).Select(qu => qu.Id_Quiz).ToList();
                    var quizList = new List<Quiz>();
                    foreach (var quizId in quizIds)
                    {
                        var quiz = _context.Quiz.Where(q => q.QuizId == quizId).FirstOrDefault();
                        quizList.Add(quiz);
                    }

                    var response = new GetUserQuizResponse()
                    {
                        Status = 1,
                        QuizList = quizList
                    };
                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetUserQuizResponse()
                    {
                        Status = -1,
                        Text = "User not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new GetUserQuizResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }
        }

        #endregion
    }
}
