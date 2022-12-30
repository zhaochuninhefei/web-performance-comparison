using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;
using Util;

namespace Accounts.Models
{
    public class AccountsItem
    {
        [Column("id")]
        public long? Id { get; set; }

        [Column("created_at")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd HH:mm:ss}", ApplyFormatInEditMode = true)]
        [JsonConverter(typeof(DateTimeJsonConverter))]
        public DateTime? CreatedAt { get; set; }

        [Column("updated_at")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd HH:mm:ss}", ApplyFormatInEditMode = true)]
        [JsonConverter(typeof(DateTimeJsonConverter))]
        public DateTime? UpdatedAt { get; set; }

        [Column("deleted_at")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd HH:mm:ss}", ApplyFormatInEditMode = true)]
        [JsonConverter(typeof(DateTimeJsonConverter))]
        public DateTime? DeletedAt { get; set; }

        [Column("act_name")]
        public string ActName { get; set; } = "";

        [Column("act_pwd")]
        public string ActPwd { get; set; } = "";

        [Column("act_nick_name")]
        public string? ActNickName { get; set; }

        [Column("act_introduction")]
        public string? ActIntroduction { get; set; }

        [Column("Act_status")]
        public int ActStatus { get; set; }

        [Column("act_register_date")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd HH:mm:ss}", ApplyFormatInEditMode = true)]
        [JsonConverter(typeof(DateTimeJsonConverter))]
        public DateTime? ActRegisterDate { get; set; }
    }
}