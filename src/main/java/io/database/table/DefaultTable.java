package io.database.table;

import io.database.index.Index;
import io.database.result.Row;
import io.database.session.Session;
import io.database.tx.Transaction;

import java.util.List;

/**
 * 默认的table实现类，对应 h2 的 MVTable
 *
 * @author xiaohei
 * @create 2020-07-24 上午11:09
 **/
public class DefaultTable implements Table {

    private List<Index> indexList;

    public void addRow(Session session, Row row) {
        //TODO 添加行的时候需要锁表或者是锁库
        Transaction transaction= session.getTransaction();
        long savePointId=transaction.getSavePoint();
        //开启会话，
        try {
            for (Index index : indexList) {
                index.addRow(session,row);
            }
        } catch (Exception e) {
            //TODO 回滚会话
            transaction.roleBackToSavePoint(savePointId);
        }
    }

    @Override
    public String getTableName() {
        return null;
    }
}
