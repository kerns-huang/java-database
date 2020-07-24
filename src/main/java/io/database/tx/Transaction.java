package io.database.tx;

/**
 * 事务
 *
 * @author xiaohei
 * @create 2020-07-24 上午11:30
 **/
public interface Transaction {
    /**
     * 获取事务ID
     * @return
     */
    long getTxId();
    /**
     * 获取保存点
     * @return
     */
    long getSavePoint();

    /**
     * 回滚到保存点
     * @param savePoint
     */
    void roleBackToSavePoint(long savePoint);

    /**
     * 事务提交
     */
    void commit();
}
