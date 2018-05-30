package quiz_aggregate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz_aggregate.Model.Entity.UserData;

import java.util.Optional;

//@Repository：このクラスがデータアクセスのクラスであることを示す
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    //Optional：null値を許容する
    //findByIdとすることで、メソッドの中身が自動的に生成される(JpaRepositoryの)機能
    //JPQL：SQLのような命令文のテキストを元にデータベースにアクセス可能。
    //findById → select * from テーブル where id = 引数
    //他にもfindBy○○And△△　や　Or / Between / LessThan / GreaterThan / IsNull / Like / OrderBy / Not / In
    public Optional<UserData> findById(Long id);

    //Idで取得してきて、TotalTimeのAsc順で取得してくれるはず
    public Optional<UserData> findByIdOrderByTotaltimeAsc(Long id);
}
