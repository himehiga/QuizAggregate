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
        modelAndView.addObject("title", "Find Page");
        modelAndView.addObject("msg", "this is sample content.");

        return modelAndView;
    }

    private setTotalTime
}
