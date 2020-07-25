package io.database.index;

import io.database.result.Row;
import io.database.tx.Session;
import io.database.tx.TransactionMap;

import java.util.Map;

/**
 * 主键索引
 *
 * @author xiaohei
 * @create 2020-07-24 上午11:09
 **/
public class PrimaryIndex implements Index {

    private TransactionMap<Long,Row> data;

    public PrimaryIndex(){
        data=new TransactionMap<>();
    }

    public void addRow(Session session, Row row) {
      Object older=  data.putIfAbsent(row.getKey(),row);
      if(older!=null){
          //抛出异常，不能重复添加
      }
    }

    public void removeRow(Session session, Row row) {
       Object older=  data.remove(row.getKey());
       if(older==null){
           //TODO 抛出异常，不能删除不存在的数据，有必要吗
       }

    }

    public void updateRow(Session session, Row old, Row newRow) {

    }

    public void find(Session session, Long start, Long end) {
        //TODO 根据不同的隔离级别，获取镜像数据，封装成对象。
    }
}
