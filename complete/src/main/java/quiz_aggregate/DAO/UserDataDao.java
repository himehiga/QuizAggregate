package quiz_aggregate.DAO;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


//Data Access Object
//文字通りデータベースにアクセスする手段を提供するためのオブジェクト
public interface UserDataDao <T> extends Serializable {
    public List<T> getAll();
    public T findById(long id);
    public List<T> findByName(String name);

}
