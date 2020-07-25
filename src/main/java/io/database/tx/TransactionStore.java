package io.database.tx;

import io.database.engine.IsolationLevel;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 事务的状态保持
 *
 * @author xiaohei
 * @create 2020-07-24 下午12:13
 **/
public class TransactionStore {
    /**
     * 同一时间开启的最大事务数字
     */
    private static final int MAX_OPEN_TRANSACTIONS = 65535;
    /**
     * 事务ID生成器,因为关闭线程和开始线程需要是线程安全的时候，所以需要包装下
     */
    private AtomicReference<BitSet> txIdProducer = new AtomicReference<>(new BitSet(MAX_OPEN_TRANSACTIONS + 1));
    /**
     * 事务操作过程中还没有提交的记录，通过事务ID获取没有提交的记录
     */
    final Map<Long, Record<?, ?>>[] undoLogs = new HashMap[MAX_OPEN_TRANSACTIONS];

    /**
     * 所有开启的事务
     * 如果是我，会用ConcurrentHashMap 去保存事务的数据
     * 不知道这两者有什么优势劣势，后面可以按照情况修改。
     */
    private AtomicReferenceArray<Transaction> transactions = new AtomicReferenceArray<Transaction>(MAX_OPEN_TRANSACTIONS + 1);

    /**
     * 开启一个事务
     *
     * @return
     */
    public Transaction begin() {
        //获取没有被占用的下标
        boolean isSuccess = false;
        int txId = 0;
        while (!isSuccess) {
            txId = txIdProducer.get().nextClearBit(1);
            BitSet origin = txIdProducer.get();
            BitSet newBitSet = (BitSet) origin.clone();
            newBitSet.set(txId);
            isSuccess = txIdProducer.compareAndSet(origin, newBitSet);
        }
        Transaction transaction = new DefaultTransaction(txId, IsolationLevel.READ_COMMITTED);
        //设置undoLog日志手机
        undoLogs[txId] = new HashMap<>();
        transactions.set(txId, transaction);
        return transaction;
    }

    /**
     * 添加 undoLogs日志
     *
     * @param txId
     * @param logId
     * @param record
     */
    public void addUndoLogs(int txId, long logId, Record record) {
        undoLogs[txId].putIfAbsent(logId, record);
    }

    /**
     * 提交一个事务
     *
     * @param transaction
     */
    public void commit(Transaction transaction) {
        removeTransaction(transaction);
    }

    /**
     * 当事务结束，移除一个事务
     * @param transaction
     */
    private void removeTransaction(Transaction transaction) {
        //保证线程安全的操作
        boolean success = false;
        while (!success) {
            BitSet origin = txIdProducer.get();
            BitSet newBitSet = (BitSet) origin.clone();
            newBitSet.clear(transaction.getTxId());
            //这个是保证 AtomicReference 在当前线程改动的时候没有其它人改动
            success = txIdProducer.compareAndSet(origin, newBitSet);
        }
        //删除事务引用
        transactions.set(transaction.getTxId(), null);
        //TODO 删除 undo log日志
        undoLogs[transaction.getTxId()]=null;
    }


}
