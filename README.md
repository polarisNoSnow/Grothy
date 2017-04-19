# Grothy
毕业设计
主题：
Fiat-Shamir签名方案和Guillou-Quisquater签名方案的编程实现

其中的杂凑函数选取的是MD5, 代码全java实现, 参考密码学和数论，历时两个月

注意：
1、MD5在最后的测试过程中，存在问题（和标准的MD5有差别），问题锁定到对ABCD缓冲区的16步迭代（答辩在即，也就没弄了）
有兴趣的朋友可以断点调试 和 标准的MD5对比（一步一步对比，需要耗费大量时间）
2、其中很多工具都是存在很大的优化空间，比如素数的检测，补位函数，位移函数（都是采用自己的逻辑，java会自带的很多工具，性能也更好）。


