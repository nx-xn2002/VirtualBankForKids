package com.group20.backend.dao;

import com.group20.backend.model.Relation;

/**
 * relation dao
 *
 * @author Ni Xiang
 */
public class RelationDao extends BaseDao<Relation> {
    public RelationDao(String path) {
        super(Relation.DATA_TYPE, path);
    }
}
