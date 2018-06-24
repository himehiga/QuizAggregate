package quiz_aggregate.Controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quiz_aggregate.Model.Entity.TimeManageData;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Service.TimeManageDataService;
import quiz_aggregate.Service.UserDataService;

import javax.jws.WebParam;

@Controller
public class PreFirstQController {

    @Autowired
    private TimeManageDataService timeManageDataService;

    @Autowired
    private UserDataService userDataService;

    private UserData userData;

    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @RequestMapping(value = "/prefirst", method = RequestMethod.GET)
    public ModelAndView prefirst(
            ModelAndView modelAndView,
            @ModelAttribute("userData")UserData userData,
            @ModelAttribute("finished")String finished){
        modelAndView.setViewName("prefirst");

        TimeManageData timeManageData = timeManageDataService.getData();
        Boolean isEndFlag = timeManageData.getAnswerEnd();
        if(isEndFlag) {
            modelAndView.addObject("title", "WaitResult");
            modelAndView.addObject("msg", "集計中だよ。ちょっと待ってね");
        } else {
            modelAndView.addObject("title", "PreFirst");
            modelAndView.addObject("msg", "押すのまってね");
        }

        this.userData = userData;

        return modelAndView;
    }

    //formのtypeがbuttonやとここにとんできてくれなかった
    @RequestMapping(value = "/prefirst", method = RequestMethod.POST)
    public String goToFirstAnswear(
            ModelAndView modelAndView,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("finished")String finished,
            @CookieValue(value = "UUID", required = false) String uuid){

        ModelAndView res = new ModelAndView();

        TimeManageData timeManageData = timeManageDataService.getData();
        Boolean isSetFirstAnswearFlag = timeManageData.getIsAnswearFirst();
        Boolean isSecondAnswearFlag = timeManageData.getAnswearSecond();
        Boolean isThirdAnswearFlag = timeManageData.getAnswearThird();
        Boolean isEndFlag = timeManageData.getAnswerEnd();
        redirectAttributes.addFlashAttribute("userData", this.userData);

        logger.error("cookie uuid={}",uuid);

        //各ユーザに回答した問題フラグをセット
        UserData userData = userDataService.findByUUID(uuid);
        Boolean isAnsweredFirst = userData.getAnseredFirst();
        Boolean isAnsweredSecond = userData.getAnseredSecond();
        Boolean isAnsweredThird = userData.getAnseredThird();

        modelAndView.setViewName("prefirst");

        //1問目回答画面へGO
        if(isSetFirstAnswearFlag && !isSecondAnswearFlag && !isThirdAnswearFlag && !isEndFlag && !isAnsweredFirst) {
            modelAndView.addObject("msg", "回答できるよ");
            return "redirect:/answer/first";
        }
        //2問目回答画面へGO
        if(isSetFirstAnswearFlag && isSecondAnswearFlag && !isThirdAnswearFlag && !isEndFlag && !isAnsweredSecond) {
            modelAndView.addObject("msg", "回答できるよ");
            return "redirect:/answer/first";
        }
        //3問目回答画面へGO
        else if(isSetFirstAnswearFlag && isSecondAnswearFlag && isThirdAnswearFlag && !isEndFlag && !isAnsweredThird) {
            modelAndView.addObject("msg", "回答できるよ");
            return "redirect:/answer/first";
        } else if (isEndFlag) {
            setTotalTime();

            return "redirect:/result";
        }
        else {
            return "redirect:/prefirst";
        }
    }

    private void setTotalTime(){

    }

}
