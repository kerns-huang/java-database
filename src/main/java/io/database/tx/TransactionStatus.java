package io.database.tx;

/**
 * 事务的状态
 *
 * @author xiaohei
 * @create 2020-07-25 下午4:48
 **/
public interface TransactionStatus {
    /**
     * 事务已经关闭
     */
    public static final int STATUS_CLOSED = 0;

    /**
     * 事务打开状态
     */
    public static final int STATUS_OPEN = 1;

    /**
     * The status of a prepared transaction.
     */
    public static final int STATUS_PREPARED = 2;

    /**
     * 事务已经提交
     */
    public static final int STATUS_COMMITTED = 3;

    /**
     * 正在回滚事务
     */
    static final int STATUS_ROLLING_BACK = 4;

    /**
     * 事务已经回滚成功
     */
    static final int STATUS_ROLLED_BACK = 5;
}
