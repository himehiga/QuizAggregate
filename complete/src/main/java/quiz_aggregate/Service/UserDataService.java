package quiz_aggregate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Repositories.UserDataRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

//@Service：このクラスをサービスとして登録するためのアノテーション。サービスのクラスに付与する
@Service
//@ScopeをつけることでSingleTonじゃなく生成されるから、各ユーザーごとに値をもてる？？
public class UserDataService {

    //EntityManagerのBeanを取得してフィールドに設定する。SpringBootの場合、起動時に自動的にBeanとしてインスタンスが登録されている
    //1クラスにつき1インスタンスしか用意されない
    //@PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Autowired
    UserDataRepository userDataRepository;

    @SuppressWarnings("unchecked")
    public List<UserData> getAll() {
        return (List<UserData>) entityManager
                .createQuery("from UserData")
                .getResultList();
    }

    public UserData findByUUID(String uuid) {
        //Stringの検索には''これをいれとかないといけない
        uuid = "'" + uuid + "'";
        return  (UserData)entityManager
                .createQuery("from UserData where uuid = " + uuid)
                .getSingleResult();
    }

    public UserData findById(int id) {
        return  (UserData)entityManager
                .createQuery("from UserData where id = " + id)
                .getSingleResult();
    }

    public UserData findByName(String name) {
        return (UserData)entityManager
                .createQuery("from UserData where name = " + name)
                .getSingleResult();
    }

    @Transactional(readOnly = false)
    public void saveUserData(UserData userData) {
        entityManager.persist(userData);
    }}
