package quiz_aggregate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * 接続先DB:https://fea01fa8-c228-41f0-9e59-e7763dfb2820-bluemix.cloudant.com/user_answear/8902567215c0ff7bda1197d964647773
 */

@Controller
public class AnswerController {

    /*
     * answerページにアクセスされた時の処理
     */
    @RequestMapping(value = "/answer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(@RequestParam(required=false, name="userAccount")String userAccount, ModelAndView modelAndView) {
        modelAndView.setViewName("answer");

        modelAndView.addObject("userAccount", userAccount);
        return modelAndView;
    }


}
