@GeneratedValue
![](img/001.png)
@GeneratedValue注解的主要作用是:
声明主键的生成策略. 很自然的, 它需要和@Id结合使用

TABLE：使用一个特定的数据库表格存放主键。
SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列.（Oracle）
IDENTITY：主键有数据库自动生成（主要是自动增长类型）. （MySQL）
AUTO：主键由程序控制. （默认）
