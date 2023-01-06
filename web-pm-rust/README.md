### 1、安装rust
```bash
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

### 2、打包（为了下载依赖包的速度，参考[这个文章](https://blog.csdn.net/nnsword/article/details/123889818)修改配置）
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