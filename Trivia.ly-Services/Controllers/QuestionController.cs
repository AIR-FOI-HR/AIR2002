using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Trivia.ly_Services.Models;

namespace Trivia.ly_Services.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class QuestionController : ControllerBase
    {

        [HttpGet]
        public Question Get()
        {
            return GetQuestions();
        }

        public Question GetQuestions()
        {
            var response = new WebClient().DownloadString("https://opentdb.com/api.php?amount=10&type=multiple");

            return JsonSerializer.Deserialize<Question>(response);
        }
    }
}