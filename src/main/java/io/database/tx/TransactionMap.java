package io.database.tx;


import java.util.AbstractMap;
import java.util.Set;

/**
 * 事务相关的map，根据事务的隔离级别，获取不通的镜像数据，比如读没有提交的数据
 *
 * @author xiaohei
 * @create 2020-07-24 下午3:14
 **/
public class TransactionMap<K,V> extends AbstractMap<K,V> {




   public V putIfAbsent(K key, V value) {
        //保存undo log 日志到 transactionStore里面去
        return value;
    }

    /**
     * 从镜像数据中获取数据
     * @param key
     * @return
     */
    V getFromSnapshot(K key){
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
