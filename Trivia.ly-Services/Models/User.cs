using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Trivia.ly_Services.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string PasswordHash { get; set; }
        public string PasswordSalt { get; set; }
        public string PasswordTmp { get; set; }
        public int Score { get; set; }
        public int Life { get; set; }
    }

    public class LoginRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

    public class LoginResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public int Score { get; set; }
        public int Life { get; set; }
    }

    public class RegisterRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }

    }

    public class RegisterResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
    }

    public class UpdateUserRequest
    {
        public string Username { get; set; }
        public int? Life { get; set; }
        public int? Score { get; set; }
    }

    public class UpdateUserResponse
    {
        public int Status { get; set; }
        public string Text { get; set; }
        public string Username { get; set; }
        public int Life { get; set; }
        public int Score { get; set; }
    }
}
