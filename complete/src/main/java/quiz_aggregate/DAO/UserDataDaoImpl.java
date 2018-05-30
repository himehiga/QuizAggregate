package quiz_aggregate.DAO;

import org.springframework.stereotype.Repository;
import quiz_aggregate.Model.Entity.UserData;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDataDaoImpl implements UserDataDao<UserData> {
    private static final long serialVersionUID = 1L;

    //エンティティを利用するために必要な機能を提供する
    private EntityManager entityManager;

    public UserDataDaoImpl() {
        super();
    }

    public UserDataDaoImpl(EntityManager manager) {
        this();
        entityManager = manager;
    }

    @Override
    public List<UserData> getAll() {
        //ceateQuery：引数にJPQLによるクエリー文を指定して呼び出す
        //from UserData → select * from userdata
        Query query = entityManager.createQuery("from UserData");
        //@SuppressWarnings：ビルド時に警告が出ないようにするため
        @SuppressWarnings("unchecked")
        List<UserData> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public UserData findById(long id) {
        return (UserData)entityManager.createQuery("from UserData where id = " + id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserData> findByName(String name) {
        return (List<UserData>)entityManager.createQuery("from userData where name = " + name).getResultList();
    }
}
