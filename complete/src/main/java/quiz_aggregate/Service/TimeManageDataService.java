package quiz_aggregate.Service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz_aggregate.Model.Entity.TimeManageData;
import quiz_aggregate.Repositories.TimeManageDataRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TimeManageDataService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TimeManageDataRepository timeManageDataRepository;

    @SuppressWarnings("unchecked")
    public TimeManageData getData() {
        return (TimeManageData) entityManager
                .createQuery("from TimeManageData")
                .getSingleResult();
    }

    public TimeManageData findById(Long id) {
        return  (TimeManageData)entityManager
                .createQuery("from TimeManageData where id = " + id)
                .getSingleResult();
    }

    public void saveTimeManageData(TimeManageData timeManageData) {
        entityManager.persist(timeManageData);
    }
}
