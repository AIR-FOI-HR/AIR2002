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
    public class Question_TypeController : Controller
    {
        private readonly TrivialyContext _context;

        public Question_TypeController(TrivialyContext context)
        {
            _context = context;
        }

        #region Default methods

        // GET: api/Question_Type
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Question_Type>>> GetQuestion_Type()
        {
            return await _context.Question_Type.ToListAsync();
        }

        // GET: api/Question_Type/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Question_Type>> GetQuestion_Type(int id)
        {
            var question_Type = await _context.Question_Type.FindAsync(id);

            if (question_Type == null)
            {
                return NotFound();
            }

            return question_Type;
        }

        // PUT: api/Question_Type/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutQuestion_Type(int id, Question_Type question_Type)
        {
            if (id != question_Type.Question_TypeId)
            {
                return BadRequest();
            }

            _context.Entry(question_Type).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!Question_TypeExists(id))
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

        // POST: api/Question_Type
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Question_Type>> PostQuestion_Type(Question_Type question_Type)
        {
            _context.Question_Type.Add(question_Type);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetQuestion_Type", new { id = question_Type.Question_TypeId }, question_Type);
        }

        // DELETE: api/Question_Type/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Question_Type>> DeleteQuestion_Type(int id)
        {
            var question_Type = await _context.Question_Type.FindAsync(id);
            if (question_Type == null)
            {
                return NotFound();
            }

            _context.Question_Type.Remove(question_Type);
            await _context.SaveChangesAsync();

            return question_Type;
        }

        private bool Question_TypeExists(int id)
        {
            return _context.Question_Type.Any(e => e.Question_TypeId == id);
        }

        #endregion

        #region Trivialy methods

        [HttpPost("GetQuestionTypes")]
        public string GetQuestionTypes()
        {
            try
            {
                var questionTypeList = _context.Question_Type.Select(p => p).ToList();

                var response = new GetQuestionTypesResponse()
                {
                    Status = 1,
                    Question_Types = questionTypeList
                };
                return JsonConvert.SerializeObject(response);
            }
            catch (Exception e)
            {
                var response = new GetQuestionTypesResponse()
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
