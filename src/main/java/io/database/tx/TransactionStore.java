package io.database.tx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 事务的状态保持
 *
 * @author xiaohei
 * @create 2020-07-24 下午12:13
 **/
public class TransactionStore {
    /**
     * 统一时间开启的最大事务数字
     */
    private static final int MAX_OPEN_TRANSACTIONS = 65535;
    /**
     * 事务操作过程中还没有提交的记录，通过事务ID获取没有提交的记录
     */
    final Map<Long, Record<?, ?>>[] undoLogs = new HashMap[MAX_OPEN_TRANSACTIONS];

    /**
     * 所有开启的事务
     * 如果是我，会用ConcurrentHashMap 去保存事务的数据
     */
    private AtomicReferenceArray<Transaction> transactions = new AtomicReferenceArray<Transaction>(MAX_OPEN_TRANSACTIONS + 1);

    /**
     * 开始一个事务
     *
     * @return
     */
    public Transaction begin() {
        return null;
    }

    public void registerTransaction() {

    }

    /**
     * 添加一个 事务
     *
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {

    }

    /**
     * 删除一个事务
     *
     * @param transaction
     */
    public void removeTransaction(Transaction transaction) {

    }


}
