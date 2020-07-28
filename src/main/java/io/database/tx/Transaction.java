package io.database.tx;

import io.database.engine.IsolationLevel;

/**
 * 事务
 *
 * @author xiaohei
 * @create 2020-07-24 上午11:30
 **/
public interface Transaction {
    /**
     * 获取事务ID
     *
     * @return
     */
    int getTxId();

    /**
     * 获取隔离级别
     * @return
     */
    IsolationLevel getIsolationLevel();

    /**
     * 获取保存点
     *
     * @return
     */
    long getSavePoint();

    /**
     * 回滚到保存点
     *
     * @param savePoint
     */
    void roleBackToSavePoint(long savePoint);

    /**
     * 事务提交
     */
    void commit();
}
