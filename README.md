# Virtual Bank For Kids

基本要求：
- Java 单体项目 + 简单 GUI，要求可离线运行，禁止使用 web 应用
- 可以使用 csv等文件进行数据存储，不能使用数据库

库表设计：

user表:
- userId 自增Integer
- userName
- password
- age
- role 0 - 孩子, 1 - 家长
- phone
- email
- createTime
- updateTime



account表:
- accountId
- userId
- type 0 - current accounts, 1 - saving accounts
- balance 账户余额
- createTime
- updateTime


relation表:
- relationId
- parentId
- childId
- createTime
- updateTime


money表:
- moneyId
- type 0 - Withdraw取款,1 - Save存钱, 2 - Transfer转账
- accountIdA
- accountIdB
- amount
- createTime

