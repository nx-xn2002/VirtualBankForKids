package com.group20;

import com.group20.backend.dao.BaseDao;
import com.group20.backend.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoTest {
    @Test
    public void UserTest() {

    }

    @Test
    public void BaseDaoTest() {
        BaseDao baseDao = new BaseDao(User.DATA_TYPE, "src/main/resources/testdata.txt");
//        List<Object> all = baseDao.getAll();
//        for (Object o : all) {
//            System.out.println(o);
//        }
        String str = "123 nixiang 22 0 12345678 123@qq.com 2024-04-17T21:33:46.328981100 2024-04-17T21:33:46.328981100 0";
        List<String> list = Arrays.stream(str.split(" ")).toList();
        List<Object> listToSave = new ArrayList<>();
        for(int i=0;i<5;i++){
            listToSave.add(new User(list));
        }
        baseDao.saveAll(listToSave);
    }
}
