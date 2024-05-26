package com.group20.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RelationTest {

    @Test
    public void testRelationDefaultConstructor() {
        Relation relation = new Relation();
        Assertions.assertNotNull(relation, "Relation object should not be null");
        Assertions.assertNull(relation.getRelationId(), "Relation ID should be null for default constructor");
        Assertions.assertNull(relation.getParentId(), "Parent ID should be null for default constructor");
        Assertions.assertNull(relation.getChildId(), "Child ID should be null for default constructor");
        Assertions.assertNull(relation.getCreateTime(), "Create time should be null for default constructor");
        Assertions.assertNull(relation.getUpdateTime(), "Update time should be null for default constructor");
    }

    @Test
    public void testRelationConstructorWithList() {
        List<String> inputList = new ArrayList<>();
        inputList.add("100");
        inputList.add("200");
        inputList.add("300");
        String createTimeStr = "2023-05-01T10:00:00";
        inputList.add(createTimeStr);
        String updateTimeStr = "2023-05-02T11:00:00";
        inputList.add(updateTimeStr);

        Relation relation = new Relation(inputList);

        Assertions.assertNotNull(relation, "Relation object should not be null");
        Assertions.assertEquals(100, relation.getRelationId(), "Relation ID should match the input value");
        Assertions.assertEquals(200, relation.getParentId(), "Parent ID should match the input value");
        Assertions.assertEquals(300, relation.getChildId(), "Child ID should match the input value");

        LocalDateTime expectedCreateTime = LocalDateTime.parse(createTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Assertions.assertEquals(expectedCreateTime, relation.getCreateTime(), "Create time should match the input value");

        LocalDateTime expectedUpdateTime = LocalDateTime.parse(updateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Assertions.assertEquals(expectedUpdateTime, relation.getUpdateTime(), "Update time should match the input value");
    }

    @Test
    public void testRelationToString() {
        List<String> inputList = new ArrayList<>();
        inputList.add("101");
        inputList.add("201");
        inputList.add("301");
        //origin format like 2023-05-03T12:00:00 will fail
        String createTimeStr = "2023-05-03T12:00";
        inputList.add(createTimeStr);
        String updateTimeStr = "2023-05-04T13:00";
        inputList.add(updateTimeStr);

        Relation relation = new Relation(inputList);

        String expectedToString = "101,201,301," + createTimeStr + "," + updateTimeStr;
        Assertions.assertEquals(expectedToString, relation.toString(), "toString() output should match the expected format");
    }
}