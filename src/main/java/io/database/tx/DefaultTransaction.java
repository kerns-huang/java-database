package io.database.tx;

import io.database.engine.IsolationLevel;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 默认的事务处理
 *
 * @author xiaohei
 * @create 2020-07-24 下午2:25
 **/
public class DefaultTransaction implements Transaction {

    private int txId;
    /**
     * 事务的隔离级别
     */
    private IsolationLevel isolationLevel;
    /**
     * 回滚监听器
     */
    private RollbackListener rollbackListener;
    /**
     * 事务信息保存
     */
    private TransactionStore transactionStore;
    /**
     * 事务的状态
     */
    private int status;

    private AtomicLong  logIdProducer;

    DefaultTransaction(TransactionStore transactionStore,int txId, IsolationLevel isolationLevel,int status,RollbackListener listener) {
        this.txId = txId;
        this.isolationLevel = isolationLevel;
        this.status=status;
        this.rollbackListener=listener;
        this.transactionStore=transactionStore;
        logIdProducer =new AtomicLong();
    }

    public int getTxId() {
        return txId;
    }

    public IsolationLevel getIsolationLevel() {
        return isolationLevel;
    }

    public long getSavePoint() {
        return 0;
    }

    public void roleBackToSavePoint(long savePoint) {

    }

    public void commit() {
        transactionStore.commit(this);
    }
}
