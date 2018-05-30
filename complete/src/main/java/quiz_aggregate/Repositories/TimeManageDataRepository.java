package quiz_aggregate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz_aggregate.Model.Entity.TimeManageData;

//@Repository：このクラスがデータアクセスのクラスであることを示す
@Repository
public interface TimeManageDataRepository  extends JpaRepository<TimeManageData, Long>{

}
