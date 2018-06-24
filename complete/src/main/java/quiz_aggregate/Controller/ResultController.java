package quiz_aggregate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Service.UserDataService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResultController {

    @Autowired
    private UserDataService userDataService;

    //@ModelAttribute：エンティティクラスのインスタンスを自動的に用意する。
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView index(
            ModelAndView modelAndView) {

        //集計
        List<UserData> allUserData = userDataService.getAll();

        modelAndView.setViewName("result");

        List<UserData> setUsers =
                allUserData.stream().filter((UserData userdata) -> {return userdata.getTotaltime() != 0;})
                .sorted((userdata1, userdata2) -> userdata1.getTotaltime().compareTo(userdata2.getTotaltime()))
                .limit(10)
                .collect(Collectors.toList());

        setUsers.forEach(user -> user.setRank(setUsers.indexOf(user) + 1));

        List<UserData> sortedUsers =
                setUsers.stream().filter((UserData userdata) -> {return userdata.getTotaltime() != 0;})
                        .sorted((userdata1, userdata2) -> userdata2.getTotaltime().compareTo(userdata1.getTotaltime()))
                        .collect(Collectors.toList());


        modelAndView.addObject("sortedUsers" , sortedUsers);

        return modelAndView;
    }
}
