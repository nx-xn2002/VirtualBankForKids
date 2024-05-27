# Virtual Bank For Kids

## Base Requirement

- Java monolithic project + simple GUI, requires offline operation, **web applications are prohibited**.
- use files like `.csv` for data storage, but don't use a database

## Table Design

user Table:

- userId auto increment Integer
- userName
- password
- age
- role 0 - child, 1 - parent
- phone
- email
- createTime
- updateTime

account Table:

- accountId
- userId
- type 0 - current accounts, 1 - saving accounts
- balance 账户余额
- createTime
- updateTime

relation Table:

- relationId
- parentId
- childId
- createTime
- updateTime

money Table:

- moneyId
- type 0 - Withdraw取款,1 - Save存钱, 2 - Transfer转账
- accountIdA
- accountIdB
- amount
- createTime

task Table:

- taskId
- description
- isFinished
- parentId
- childId
- createTime
- updateTime