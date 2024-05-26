package com.group20.backend.service;

import com.group20.Response;
import com.group20.backend.dao.AccountDao;
import com.group20.backend.dao.DaoManage;
import com.group20.backend.model.Account;
import com.group20.utils.ResultUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * account service impl
 *
 * @author Ni Xiang
 */
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = DaoManage.getAccountDaoInstance();

    @Override
    public Response<Boolean> createAccount(Account account) {
        if (account.getUserId() == null) {
            return ResultUtil.fail("User no found");
        }
        if (account.getType() == null || account.getType().equals((short) -1)) {
            return ResultUtil.fail("Type is null");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        HashSet<Integer> existsAccountId = new HashSet<>();
        for (Account accountExist : allAccount) {
            if (accountExist.getUserId().equals(account.getUserId()) && accountExist.getType().equals(account.getType())) {
                return ResultUtil.fail("You already have this type of account!");
            }
            existsAccountId.add(accountExist.getAccountId());
        }
        Integer accountId = genAccountId();
        int count = 0;
        while (existsAccountId.contains(accountId)) {
            count++;
            accountId = genAccountId();
            if (count >= 100000) {
                return ResultUtil.fail("System Error");
            }
        }
        account.setAccountId(accountId);
        account.setBalance(0F);
        account.setCreateTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        allAccount.add(account);
        accountDao.saveAll(allAccount);
        return ResultUtil.success();
    }

    @Override
    public Response<List<Account>> selectAccountByUserId(Integer userId) {
        if (userId == null) {
            return ResultUtil.fail("Null UserId");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        List<Account> userAccount = new ArrayList<>();
        for (Account account : allAccount) {
            if (account.getUserId().equals(userId)) {
                userAccount.add(account);
            }
        }
        return ResultUtil.success(userAccount);
    }

    @Override
    public Response<Boolean> removeAccount(Account account) {
        if (account.getUserId() == null) {
            return ResultUtil.fail("User no found");
        }
        if (account.getType() == null || account.getType().equals((short) -1)) {
            return ResultUtil.fail("Type is null");
        }
        List<Account> allAccount = accountDao.getAllAccount();
        for (int i = 0; i < allAccount.size(); i++) {
            Account accountExist = allAccount.get(i);
            if (accountExist.getUserId().equals(account.getUserId()) && accountExist.getType().equals(account.getType())) {
                allAccount.remove(i);
                break;
            }
        }
        accountDao.saveAll(allAccount);
        return ResultUtil.success();
    }

    private Integer genAccountId() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        return 10000000 + random.nextInt(90000000);
    }
}
