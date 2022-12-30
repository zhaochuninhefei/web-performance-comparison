namespace Dto
{
    public class DatabaseConfig
    {
        public string? ConnectionString { get; set; }
        public int BigVersion { get; set; }
        public int MidVersion { get; set; }
        public int SmallVersion { get; set; }
    }
}