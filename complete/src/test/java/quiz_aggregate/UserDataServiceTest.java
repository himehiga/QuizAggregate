package quiz_aggregate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Service.UserDataService;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDataServiceTest {
    private UserDataService userDataService;

    @Before
    public void setUp() {
        userDataService = new UserDataService();
    }

    @Test
    public void uuidで取得できるかテスト() {
        UserData result = new UserData();
        String uuid = UUID.randomUUID().toString();

        assertThat(userDataService.findByUUID(uuid));
    }

    @Test
    public void nameで取得できるかテスト() {
        UserData result = new UserData();
        assertThat(userDataService.findByName("something"));
    }
}
