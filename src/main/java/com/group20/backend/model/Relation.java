package com.group20.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * relation
 *
 * @author Ni Xiang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    /**
     * data type
     */
    public final static String DATA_TYPE = "com.group20.backend.model.Relation";
    /**
     * relation id
     */
    private Integer relationId;
    /**
     * parent id
     */
    private Integer parentId;
    /**
     * child id
     */
    private Integer childId;
    /**
     * create time
     */
    private LocalDateTime createTime;
    /**
     * update time
     */
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return relationId + "," + parentId + "," + childId + "," + createTime + "," + updateTime;
    }

    /**
     * relation
     * the constructor for dao
     *
     * @param s s
     * @author Ni Xiang
     */
    public Relation(List<String> s) {
        this.setRelationId(Integer.valueOf(s.get(0)));
        this.setParentId(Integer.valueOf(s.get(1)));
        this.setChildId(Integer.valueOf(s.get(2)));
        this.setCreateTime(LocalDateTime.parse(s.get(3)));
        this.setUpdateTime(LocalDateTime.parse(s.get(4)));
    }
}
