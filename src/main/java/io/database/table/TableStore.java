package io.database.table;

import java.util.HashMap;
import java.util.Map;

/**
 * 表元数据存贮
 *
 * @author xiaohei
 * @create 2020-07-29 下午1:53
 **/
public class TableStore {

    Map<String, Table> map = new HashMap<>();

    /**
     * 创建数据库表名
     *
     * @param table
     */
    public void createTable(Table table) {
        map.put(table.getTableName(), table);
    }


}
