package main.java.dataType;

import java.io.Serializable;
import java.util.*;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/13 16:28
 */
//
public class EnhancedTestCase implements Serializable {

    String name;
    Map<Integer, Statement> statements;
    String path;

    public EnhancedTestCase(String testcaseName, String path) {
        setName(testcaseName);
        this.statements = new LinkedHashMap<>();
        setPath(path);
    }

    public EnhancedTestCase() {
        this.statements = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Statement> getStatements() {
        return statements;
    }

    public void setStatements(Map<Integer, Statement> statements) {
        this.statements = statements;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addStatementAtPosition(Integer i, Statement st) {
        if(!statements.containsKey(i)) {
            statements.put(i, st);
            st.setLine(i);

            // 将测试语句按照行号排序
            Map<Integer, Statement> newMap = new LinkedHashMap<>();
            List<Integer> keys = new ArrayList<>(statements.keySet());
            Collections.sort(keys);
            for (int tempLine : keys) {
                newMap.put(tempLine, statements.get(tempLine));
            }
            statements = newMap;
        } else {
            Map<Integer, Statement> newMap = new LinkedHashMap<>();

            // 在位置 i 插入测试语句 st, 接续的语句往后挪一个位置
            for (Statement statement : statements.values()) {
                if (statement.getLine() < i) {
                    newMap.put(statement.getLine(), statement);
                } else if (statement.getLine() == i) {
                    newMap.put(i, st);
                    statement.setLine(1 + statement.getLine());
                    newMap.put(statement.getLine(), statement);
                } else if (statement.getLine() > i) {
                    statement.setLine(1 + statement.getLine());
                    newMap.put(statement.getLine(), statement);
                }
            }
            statements = newMap;
        }
    }

    public void addAndReplaceStatement(Integer i, Statement st) {
        statements.put(i, st);
    }

    @Override
    public String toString() {
        return "TestCase [name=" + name + ", statements" + statements + "]";
    }
}
