package io.database.engine;

/**
 * 事务的隔离级别
 */
public enum IsolationLevel {
    /**
     * 读取未提交的数据
     */
    READ_UNCOMMITTED,
    /**
     * 读取提交的数据
     */
    READ_COMMITTED,
    /**
     * 重复读，都是同一个数据，比如 线程1 第一次读取到 1- 张三，如果这个时候线程2 把 1 改成了李四 ，线程1 第二次读取 还是 1- 张三
     */
    REPEATABLE_READ,
    /**
     * 读取备份
     */
    SNAPSHOT,
    /**
     * 序列化读
     */
    SERIALIZABLE;
}
