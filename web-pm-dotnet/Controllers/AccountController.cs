using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Accounts.Models;
using Dto;

namespace web_pm_dotnet.Controllers
{
    [Route("account")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly AccountsContext _context;

        public AccountController(AccountsContext context)
        {
            _context = context;
        }

        // GET: api/TodoItems
        [HttpGet("list")]
        public async Task<ActionResult<IEnumerable<AccountsItem>>> List()
        {
            return await _context.AccountsItems.ToListAsync();
        }

        // GET: api/TodoItems/5
        [HttpGet("query")]
        public async Task<ActionResult<AccountsItem>> Query(long id)
        {
            var accountItem = await _context.AccountsItems.FindAsync(id);

            if (accountItem == null)
            {
                return NotFound();
            }

            return accountItem;
        }

        // PUT: api/TodoItems/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost("add")]
        public async Task<ActionResult<ResponseMsg>> Add(AccountsItem accountsItem)
        {
            _context.Add(accountsItem);

            await _context.SaveChangesAsync();

            _context.Entry(accountsItem).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                throw;
            }

            return new ResponseMsg() { ResCd = "1", ResMsg = "新增账户ID:" + accountsItem.Id };
        }
    }
}
