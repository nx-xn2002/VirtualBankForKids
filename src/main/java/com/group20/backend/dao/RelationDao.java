package com.group20.backend.dao;

import com.group20.backend.model.Relation;
import com.group20.backend.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * relation dao
 *
 * @author Ni Xiang
 */
public class RelationDao extends BaseDao<Relation> {
    public RelationDao(String path) {
        super(Relation.DATA_TYPE, path);
    }
    public List<Relation> getAllRelation() {
        List<Object> relationObjects = super.getAll();
        List<Relation> relations = new ArrayList<>();
        for (Object relationObject : relationObjects) {
            relations.add((Relation) relationObject);
        }
        return relations;
    }
}
