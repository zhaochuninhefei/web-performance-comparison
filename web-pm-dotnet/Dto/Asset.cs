namespace Dto
{
    public record Asset
    {
        public int Id { get; set; }
        public string Name { get; set; } = "";
        public string Desc { get; set; } = "";
    }
}