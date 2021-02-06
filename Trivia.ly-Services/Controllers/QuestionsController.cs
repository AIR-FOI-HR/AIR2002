using System;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
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
    public class QuestionsController : Controller
    {
        private readonly TrivialyContext _context;

        public QuestionsController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods

        // GET: api/Questions
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Question>>> GetQuestion()
        {
            return await _context.Question.ToListAsync();
        }

        // GET: api/Questions/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Question>> GetQuestion(int id)
        {
            var question = await _context.Question.FindAsync(id);

            if (question == null)
            {
                return NotFound();
            }

            return question;
        }

        // PUT: api/Questions/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutQuestion(int id, Question question)
        {
            if (id != question.QuestionId)
            {
                return BadRequest();
            }

            _context.Entry(question).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!QuestionExists(id))
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

        // POST: api/Questions
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Question>> PostQuestion(Question question)
        {
            _context.Question.Add(question);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetQuestion", new { id = question.QuestionId }, question);
        }

        // DELETE: api/Questions/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Question>> DeleteQuestion(int id)
        {
            var question = await _context.Question.FindAsync(id);
            if (question == null)
            {
                return NotFound();
            }

            _context.Question.Remove(question);
            await _context.SaveChangesAsync();

            return question;
        }

        private bool QuestionExists(int id)
        {
            return _context.Question.Any(e => e.QuestionId == id);
        }

        #endregion

        #region Trivialy methods

        [HttpPost("GetQuestions")]
        public string GetQuestionsByCategoryAndDifficulty([FromBody] QuestionsRequest body)
        {
            /*
            Ako je Category any dohvati random kategorije pitanaj
            Ako je Difficulty 0/Any dohvati random difficulty pitanja
            Broj pitanja po defaultu će biti 10

            TODO implementirati
                Random rand = new Random();
                int toSkip = rand.Next(0, context.Quotes.Count);

                context.Quotes.Skip(toSkip).Take(1).First();
             */



            try
            {
                Difficulty difficulty = _context.Difficulty
                    .Where(d => d.Name == body.DifficultyName).FirstOrDefault();

                Category category = _context.Category
                    .Where(c => c.Name == body.CategoryName).FirstOrDefault();

                int numberOfQuestions = body.NumberOfQuestions == 0 ? 10 : body.NumberOfQuestions;
                
                List<QuestionsListResponse> questionList = new List<QuestionsListResponse>();

                if (category != null)
                {
                    if (category.Name != "Any Category")
                    {

                        List<Question> questions = _context.Question
                            .Take(numberOfQuestions)
                            .Where(q => q.Id_Category == category.CategoryId && q.Id_Difficulty == difficulty.DifficultyId).ToList();


                        foreach (var question in questions)
                        {
                            questionList.Add(new QuestionsListResponse()
                            {
                                QuestionCategory = category.Name,
                                QuestionDifficulty = difficulty.Name,
                                QuestionText = question.Question_text,
                                CorrectAnswer = question.Correct_answer,
                                IncorrectAnswers = question.Incorrect_answer
                            });
                        }

                        var response = new QuestionsResponse()
                        {
                            Status = 1,
                            Text = "",
                            Questions = questionList
                        };

                        return JsonConvert.SerializeObject(response);
                    }
                    else
                    {
                        for (int i = 0; i < numberOfQuestions; i++)
                        {
                            Random rand = new Random();
                            int toSkip = rand.Next(0, _context.Question.Count());

                            var question = _context.Question.Skip(toSkip).Take(1).First();
                            var categoryName = _context.Category.Where(q => q.CategoryId == question.Id_Category).FirstOrDefault().Name;
                            questionList.Add(new QuestionsListResponse()
                            {
                                QuestionCategory = categoryName,
                                QuestionDifficulty = difficulty.Name,
                                QuestionText = question.Question_text,
                                CorrectAnswer = question.Correct_answer,
                                IncorrectAnswers = question.Incorrect_answer
                            });
                        }

                        var response = new QuestionsResponse()
                        {
                            Status = 1,
                            Text = "",
                            Questions = questionList
                        };

                        return JsonConvert.SerializeObject(response);

                    }
                }
                else
                {
                    var response = new QuestionsResponse()
                    {
                        Status = -1,
                        Text = "Category name invalid"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch (Exception e)
            {
                var response = new QuestionsResponse()
                {
                    Status = -9,
                    Text = e.InnerException.Message
                };
                return JsonConvert.SerializeObject(response);
            }

        }
    
        [HttpPost("GetQuestionById")]
        public string GetQuestionById([FromBody]GetQuestionByIdRequest body)
        {
            try
            {
                var question = _context.Question.Where(q => q.QuestionId == body.QuestionId).FirstOrDefault();
                if(question != null)
                {
                    var response = new GetQuestionByIdResponse()
                    {
                        Status = 1,
                        Question = question
                    };
                    return JsonConvert.SerializeObject(response);
                }
                else
                {
                    var response = new GetQuestionByIdResponse()
                    {
                        Status = -1,
                        Text = "Question not found!"
                    };
                    return JsonConvert.SerializeObject(response);
                }
            }
            catch(Exception e)
            {
                var response = new GetQuestionByIdResponse()
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
