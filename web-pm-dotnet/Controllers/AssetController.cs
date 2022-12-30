using Microsoft.AspNetCore.Mvc;
using Dto;

namespace web_pm_dotnet.Controllers;

[ApiController]
[Route("asset")]
public class AssetController : ControllerBase
{
    private static readonly Asset ast1 = new Asset() { Id = 1, Name = "asset001", Desc = "测试资产001" };

    private static readonly Asset ast2 = new Asset() { Id = 2, Name = "asset002", Desc = "测试资产002" };

    private static readonly Dictionary<string, Asset> astMp = new Dictionary<string, Asset>() {
        { "1", ast1 },
        { "2", ast2 }
    };

    private static readonly List<Asset> assets = new List<Asset>() { ast1, ast2 };

    public AssetController()
    {
    }

    [HttpGet("list")]
    public IEnumerable<Asset> List()
    {
        return assets;
    }

    [HttpGet("query")]
    public Asset Query(string id)
    {
        return astMp[id];
    }

    [HttpPost("modify")]
    public ResponseMsg Modify(Asset asset)
    {
        Console.WriteLine("修改目标：{0}", asset.ToString());
        return new ResponseMsg()
        {
            ResCd = "1",
            ResMsg = asset.ToString() ?? ""
        };
    }
}
