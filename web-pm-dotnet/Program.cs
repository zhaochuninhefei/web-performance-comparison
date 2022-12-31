using Accounts.Models;
using Dto;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
var dbConfig = builder.Configuration.GetSection("Database").Get<DatabaseConfig>();
var serverVersion = new MySqlServerVersion(new Version(dbConfig.BigVersion, dbConfig.MidVersion, dbConfig.SmallVersion));
builder.Services.AddDbContext<AccountsContext>(opt =>
    opt.UseMySql(dbConfig.ConnectionString, serverVersion)
                // The following three options help with debugging, but should
                // be changed or removed for production.
                .LogTo(Console.WriteLine, LogLevel.Warning)
                .EnableSensitiveDataLogging()
                .EnableDetailedErrors()
);

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    // app.UseDeveloperExceptionPage();
    app.UseSwagger();
    app.UseSwaggerUI();
}

// app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
