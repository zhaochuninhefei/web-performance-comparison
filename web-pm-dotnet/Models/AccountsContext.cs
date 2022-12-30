using Microsoft.EntityFrameworkCore;

namespace Accounts.Models
{
    public class AccountsContext : DbContext
    {
        public AccountsContext(DbContextOptions<AccountsContext> options)
            : base(options)
        {
        }

        public DbSet<AccountsItem> AccountsItems { get; set; } = null!;

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<AccountsItem>().ToTable("accounts");
        }
    }
}