package quiz_aggregate.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quiz_aggregate.Model.Entity.TimeManageData;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Service.TimeManageDataService;
import quiz_aggregate.Service.UserDataService;
import sun.util.resources.LocaleData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


//問題
//userDataをいれた後に、画面遷移
//戻ってくると、userDataにnullがはいっている
//
//Controllerにメンバを持たない方がよい
//memcachedに持たせるのがよいか


//cookieにIDを保存して、IDを使ってEntityから取り出し、値を保存していくのがいいんやろなー
@Controller
public class AnswerController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private TimeManageDataService timeManageDataService;


    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    /*
     * answerページにアクセスされた時の処理
     */
    @RequestMapping(value = "/answer/first", method = RequestMethod.GET)
    public ModelAndView first(ModelAndView modelAndView,
                              @ModelAttribute("userData")UserData userData,
                              HttpServletResponse response) {
        //answer画面表示
        modelAndView.setViewName("answer");
        return modelAndView;
    }


    @RequestMapping(value = "/answer/edit", method = RequestMethod.POST, params = "answerA")
    public String answearA(
            ModelAndView modelAndView,
            RedirectAttributes redirectAttributes,
            @CookieValue(value = "UUID", required = false) String uuid) {
        //Aと回答しました

        LocalDateTime answerTime = LocalDateTime.now();

        logger.error("cookie uuid={}",uuid);

//        UserData userData = userDataService.findById(Integer.valueOf(userId));
        UserData userData = userDataService.findByUUID(uuid);
//        List<LocalDateTime> answerTimeList = userData.getAnswerTimeList();

        logger.error("userName={}",userData.getName());

        TimeManageData timeManageData = timeManageDataService.getData();
        Boolean isFirstAnswearFlag = timeManageData.getIsAnswearFirst();
        Boolean isSecondAnswearFlag = timeManageData.getAnswearSecond();
        Boolean isThirdAnswearFlag = timeManageData.getAnswearThird();
        Boolean isEndAnswerFlag = timeManageData.getAnswerEnd();

        if (isFirstAnswearFlag) {
            //1問目時間保存
            userData.setAnswerTime(answerTime);
            //１問目完了フラグを待機画面に渡して遷移
            redirectAttributes.addFlashAttribute("finished", "FinishedFirst");
        } else if (isSecondAnswearFlag) {
            //2問目時間保存
            //2問目完了フラグを待機画面に渡して遷移
            userData.setAnswerSecondTime(answerTime);
            redirectAttributes.addFlashAttribute("finished", "FinishedSecond");
        } else if (isThirdAnswearFlag) {
            //3問目時間保存
            //3問目完了フラグを待機画面に渡して遷移
            redirectAttributes.addFlashAttribute("finished", "FinishedThird");
        } else if (isEndAnswerFlag) {

        }

        userDataService.saveUserData(userData);

        //回答を保存して、保存成功したら、待機画面へ移動
        //失敗したら、ボタンを無効にしない
        return "redirect:/prefirst";
    }

    @RequestMapping(value = "/answer/edit", method = RequestMethod.POST, params = "answerB")
    public Void answearB(ModelAndView modelAndView) {
        //Bと回答しました
        //不正解
        int a = 1;
        //回答を保存して、保存成功したら、待機画面へ移動
        //失敗したら、ボタンを無効にしない
        return null;
    }

    @RequestMapping(value = "/answer/first", method = RequestMethod.POST, params = "answerA")
    public Void firstAnswear(ModelAndView modelAndView) {

        //View側にfirstであることを渡す
        //post時にViewにデータを渡す
        return null;
    }

    @RequestMapping(value = "/answer/second", method = RequestMethod.GET)
    public ModelAndView second(ModelAndView modelAndView) {
        modelAndView.setViewName("answer");

        return modelAndView;
    }

    @RequestMapping(value = "/answer/third", method = RequestMethod.GET)
    public ModelAndView third(ModelAndView modelAndView) {
        modelAndView.setViewName("answer");

        return modelAndView;
    }
}
