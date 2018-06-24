package quiz_aggregate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import quiz_aggregate.Model.Entity.TimeManageData;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Repositories.TimeManageDataRepository;
import quiz_aggregate.Service.TimeManageDataService;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import java.util.List;

@EnableJpaRepositories(basePackages = "quiz_aggregate.Repositories")
@Controller
public class TimeManageController {

    //TimeManageDataをひとつだけもつ
    //ここの中でフラグをそのTimeManageDataにセットして、それをDBへセット
    //んで、ユーザが操作するコントローラから、DBを取得してきて、フラグや、計算の基準となる時間をセットする

    @Autowired
    TimeManageDataRepository timeManageDataRepository;

    @Autowired
    private TimeManageDataService timeManageDataService;

    @PostConstruct
    public void init() {
        TimeManageData timeManageData = new TimeManageData();
        timeManageData.setAnswearFirst(false);
        timeManageData.setAnswearSecond(false);
        timeManageData.setAnswearThird(false);
        timeManageData.setAnswerEnd(false);
        timeManageDataRepository.saveAndFlush(timeManageData);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @Transactional
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST, params = "baseTime")
    @Transactional
    public ModelAndView baseTime(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        TimeManageData timeManageData = timeManageDataService.getData();
        if (timeManageData != null) {
            modelAndView.addObject("timeManageData", timeManageData);
        }

        long baseTime = System.currentTimeMillis();

        timeManageData.setBaseTime(baseTime);
        timeManageDataService.saveTimeManageData(timeManageData);
        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST, params = "first")
    @Transactional
    public ModelAndView first(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        TimeManageData timeManageData = timeManageDataService.getData();
        if (timeManageData != null) {
            modelAndView.addObject("timeManageData", timeManageData);
        }

        timeManageData.setAnswearFirst(true);
        timeManageDataService.saveTimeManageData(timeManageData);
        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST, params = "second")
    @Transactional
    public ModelAndView second(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        TimeManageData timeManageData = timeManageDataService.getData();
        if (timeManageData != null) {
            modelAndView.addObject("timeManageData", timeManageData);
        }

        timeManageData.setAnswearSecond(true);
        timeManageDataService.saveTimeManageData(timeManageData);
        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST, params = "third")
    @Transactional
    public ModelAndView third(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        TimeManageData timeManageData = timeManageDataService.getData();
        if (timeManageData != null) {
            modelAndView.addObject("timeManageData", timeManageData);
        }

        timeManageData.setAnswearThird(true);
        timeManageDataService.saveTimeManageData(timeManageData);
        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST, params = "result")
    @Transactional
    public ModelAndView result(ModelAndView modelAndView) {
        modelAndView.setViewName("timemanage");
        modelAndView.addObject("title", "Manage Page");
        modelAndView.addObject("msg", "this is sample content.");

        TimeManageData timeManageData = timeManageDataService.getData();
        if (timeManageData != null) {
            modelAndView.addObject("timeManageData", timeManageData);
        }

        timeManageData.setAnswerEnd(true);
        timeManageDataService.saveTimeManageData(timeManageData);
        return modelAndView;
    }
}
