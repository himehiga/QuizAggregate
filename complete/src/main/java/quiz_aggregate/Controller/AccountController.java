package quiz_aggregate.Controller;

import com.oracle.tools.packager.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@Controller
public class AccountController {

    /*
     * accountページにアクセスされた時の処理
     */
    @RequestMapping(value = "/account")
    @ResponseBody
    public ModelAndView account(ModelAndView modelAndView) {
        modelAndView.setViewName("account");
        modelAndView.addObject("message","サンプルメッセージ！");
        return modelAndView;
    }

    /*
     *
     */
    @RequestMapping(value = "/setAccount", method = RequestMethod.POST)
    public String send(@RequestParam(required=false, name="userAccount")String userAccount, RedirectAttributes redirectAttributes)
    {
        //str="エラーがわからん";
        //Account登録処理
        //acccount.htmlからフォームでPOSTされたアカウント登録(これはいらないかも)
        //次のページへ遷移させる

        redirectAttributes.addAttribute("userAccount", userAccount);
        //ModelAndView modelAndView = new ModelAndView("redirect:/answer");
        return "redirect:/answer";
    }
}
