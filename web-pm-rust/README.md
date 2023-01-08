### 1、安装rust
```bash
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

### 2、打包（为了下载依赖包的速度，参考[这个文章](https://blog.csdn.net/nnsword/article/details/123889818)修改配置）
在`$HOME/.cargo/`目录下创建`config`文件，输入以下内容:
```
[source.crates-io]
registry = "https://github.com/rust-lang/crates.io-index"

#replace-with = 'tuna'
replace-with = 'ustc'
#replace-with = "rustcc"

[source.tuna]
#清华提供
registry = "https://mirrors.tuna.tsinghua.edu.cn/git/crates.io-index.git"

[source.sjtu]
# 上海交通大学
registry = "https://mirrors.sjtug.sjtu.edu.cn/git/crates.io-index"

[source.ustc]
# 中国科技大提供
registry = "https://mirrors.ustc.edu.cn/crates.io-index"
#registry = "git://mirrors.ustc.edu.cn/crates.io-index"

[source.rustcc]
# rust中文社区提供
registry = "https://code.aliyun.com/rustcc/crates.io-index.git"
#registry = "git://crates.rustcc.cn/crates.io-index"

[net]
git-fetch-with-cli = true
```

然后编译:
```bash
# 第一次build要下载依赖包比较慢
cargo build --release
```

### 3、配置文件
根目录下的.env，只提供了4个参数：数据库连接字符串、数据库连接池最大连接数、数据库连接池最小连接数、http端口

### 4、运行
```bash
./target/release/web-pm-rust
```

### 5、一些说明
关于rust与mysql的字段类型匹配，参考:
[Module sqlx::mysql::types](https://docs.rs/sqlx/latest/sqlx/mysql/types/index.html)
