package org.hfutec.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.hfutec.nlp.model.Corpus;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库查询类
 * Created by DuFei on 2017/2/22.
 */
public class DataQueryByDB {

  private DataSource ds;
  public QueryRunner qr;

  public DataQueryByDB(DataSource ds){

    this.ds = ds;

    qr = new QueryRunner(ds);

  }

  //获取单列的值
  public List<?> getColumnListInfoBySQL ( String sql ){


    List<?> list = null;
    try {
      list = (List) qr.query(sql, new ColumnListHandler());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;

  }

  //查询List Bean信息
  public <T> List<T> getBeanListInfoBySQL ( String sql, Class<T> type ){


    List<T> list = null;
    try {
      list = qr.query(sql, new BeanListHandler<T>(type));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;

  }

  //query scalar
  public Object getScalarBySQL ( String sql ){

    Object obj = new Object();
    ScalarHandler<Object> scalar = new ScalarHandler<Object>();

    try {
      obj = qr.query(sql, scalar);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return obj;
  }

  public static void main(String[] args) {

    DataSource ds = DataSouceUtil.getDataSource("");
    DataQueryByDB db = new DataQueryByDB(ds);

    String sql = "select * from corpus";
    List<Corpus> corpus = db.getBeanListInfoBySQL(sql, Corpus.class);

  }

}
