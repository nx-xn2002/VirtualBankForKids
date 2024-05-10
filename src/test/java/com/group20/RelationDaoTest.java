package com.group20;

import com.group20.backend.dao.DaoManage;
import com.group20.backend.dao.RelationDao;
import com.group20.backend.model.Relation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * relation dao test
 *
 * @author Ni Xiang
 */
public class RelationDaoTest {
    @Test
    public void insertTest() {
        RelationDao relationDao = DaoManage.getRelationDaoInstance();
        List<Relation> relations = new ArrayList<>();
        relations.add(new Relation(0, 0, 1, LocalDateTime.now(), LocalDateTime.now()));
        relationDao.saveAll(relations);
    }
}
