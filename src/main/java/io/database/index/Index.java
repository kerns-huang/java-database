package io.database.index;

import io.database.result.Row;
import io.database.tx.Session;

public interface Index {
    /**
     * 在会话级别添加一行数据
     * @param session
     * @param row
     */
    void addRow(Session session, Row row);

    /**
     * 会话级别删除一行数据
     * @param session
     * @param row
     */
    void removeRow(Session session,Row row);

    /**
     * 更新一条数据
     * @param session
     * @param old
     * @param newRow
     */
    void updateRow(Session session,Row old,Row newRow);

    /**
     * 查找数据
     * @param session
     * @param start
     * @param end
     */
    void find(Session session, Long start, Long end);

}
