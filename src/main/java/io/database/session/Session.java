package io.database.session;

import io.database.tx.Transaction;

/**
 * 会话，代表的一个数据库连接，在连接中可以开启多个事务，比如事务的嵌套。
 * @author xiaohei
 * @create 2020-07-24 上午11:30
 **/
public interface Session {

    Transaction getTransaction();
}
