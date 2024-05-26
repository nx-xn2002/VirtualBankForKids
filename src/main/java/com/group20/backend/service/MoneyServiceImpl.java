package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.dao.AccountDao;
import com.group20.backend.dao.DaoManage;
import com.group20.backend.dao.MoneyDao;
import com.group20.backend.model.Account;
import com.group20.backend.model.Money;
import com.group20.utils.ResultUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * money service impl
 *
 * @author Ni Xiang
 */
public class MoneyServiceImpl implements MoneyService {
    private final AccountDao accountDao = DaoManage.getAccountDaoInstance();
    private final MoneyDao moneyDao = DaoManage.getMoneyDaoInstance();

    @Override
    public Response<Boolean> change(Money money) {
        Short type = money.getType();
        if (type == null) {
            return ResultUtil.fail("Money Type is null");
        } else if (type.equals(Money.WITHDRAW)) {
            return withdraw(money);
        } else if (type.equals(Money.SAVE)) {
            return save(money);
        } else if (type.equals(Money.TRANSFER)) {
            return transfer(money);
        }
        return ResultUtil.fail("");
    }

    private Response<Boolean> transfer(Money money) {
        if (money.getAccountIdA() == null || money.getAccountIdB() == null) {
            return ResultUtil.fail("Account ID is null");
        }
        if (money.getAccountIdA().equals(money.getAccountIdB())) {
            return ResultUtil.fail("Account IDs are equal");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        Account accountA = null, accountB = null;
        int indexA = -1, indexB = -1;
        for (int i = 0; i < allAccount.size(); i++) {
            Account account = allAccount.get(i);
            if (account.getAccountId().equals(money.getAccountIdA())) {
                accountA = account;
                indexA = i;
            }
            if (account.getAccountId().equals(money.getAccountIdB())) {
                accountB = account;
                indexB = i;
            }
        }
        if (accountA == null || accountB == null) {
            return ResultUtil.fail("No such account");
        }
        if (accountA.getBalance() < money.getAmount()) {
            return ResultUtil.fail("Your balance is not enough, your balance is" + accountA.getBalance());
        }
        Money moneyA = new Money();
        Money moneyB = new Money();
        moneyA.setType(Money.TRANSFER);
        moneyA.setAmount(-money.getAmount());
        moneyA.setAccountIdA(money.getAccountIdA());
        moneyA.setAccountIdB(money.getAccountIdB());
        moneyA.setCreateTime(LocalDateTime.now());

        moneyB.setType(Money.TRANSFER);
        moneyB.setAmount(money.getAmount());
        moneyB.setAccountIdA(money.getAccountIdB());
        moneyB.setAccountIdB(money.getAccountIdA());
        moneyB.setCreateTime(LocalDateTime.now());
        moneyDao.add(moneyA);
        moneyDao.add(moneyB);
        accountA.setUpdateTime(LocalDateTime.now());
        accountA.setBalance(accountA.getBalance() - money.getAmount());
        accountB.setBalance(accountB.getBalance() + money.getAmount());
        allAccount.set(indexA, accountA);
        allAccount.set(indexB, accountB);
        return ResultUtil.success();
    }

    private Response<Boolean> save(Money money) {
        if (money.getAccountIdA() == null) {
            return ResultUtil.fail("Account Id is null");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        for (int i = 0; i < allAccount.size(); i++) {
            Account account = allAccount.get(i);
            if (account.getAccountId().equals(money.getAccountIdA())) {
                if (account.getType().equals(Account.SAVING)) {
                    return ResultUtil.fail("This type of account is not allowed to perform this operation.");
                }
                account.setBalance(account.getBalance() + money.getAmount());
                account.setUpdateTime(LocalDateTime.now());
                allAccount.set(i, account);
                accountDao.saveAll(allAccount);
                moneyDao.add(money);
                return ResultUtil.success(true);
            }
        }
        return ResultUtil.fail("No such Account");
    }

    private Response<Boolean> withdraw(Money money) {
        if (money.getAccountIdA() == null) {
            return ResultUtil.fail("Account id is null");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        for (int i = 0; i < allAccount.size(); i++) {
            Account account = allAccount.get(i);
            if (account.getAccountId().equals(money.getAccountIdA())) {
                if (account.getType().equals(Account.SAVING)) {
                    return ResultUtil.fail("This type of account is not allowed to perform this operation.");
                }
                if (account.getBalance() < money.getAmount()) {
                    return ResultUtil.fail("Your balance is not enough, your balance is" + account.getBalance());
                }
                account.setBalance(account.getBalance() - money.getAmount());
                account.setUpdateTime(LocalDateTime.now());
                allAccount.set(i, account);
                accountDao.saveAll(allAccount);
                moneyDao.add(money);
                return ResultUtil.success(true);
            }
        }
        return ResultUtil.fail("No such Account");
    }
}
