package io.database.tx;

/**
 * @author xiaohei
 * @create 2020-07-24 上午11:24
 **/
public class Record<K, V> {

    private K key;

    private V oldValue;

    private V newValue;

}
