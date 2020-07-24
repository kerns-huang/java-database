package io.database.index;

import io.database.result.Row;

public interface Index {
    /**
     * 添加一行数据
     * @param row
     */
    void addRow(Row row);
}
