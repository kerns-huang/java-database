package io.database.session;

import io.database.tx.Transaction;

/**
 * @author xiaohei
 * @create 2020-07-24 上午11:30
 **/
public interface Session {

    Transaction getTransaction();

}
