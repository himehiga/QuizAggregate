package quiz_aggregate.Controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quiz_aggregate.DAO.UserDataDaoImpl;
import quiz_aggregate.Model.Entity.TimeManageData;
import quiz_aggregate.Model.Entity.UserData;
import quiz_aggregate.Repositories.UserDataRepository;
import quiz_aggregate.Service.TimeManageDataService;
import quiz_aggregate.Service.UserDataService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "quiz_aggregate.Repositories")
@Controller
public class WelcomeController {

    //@Autowired：Beanオブジェクト(SpringMVCにより自動的にインスタンスが作成され、アプリケーション内で利用可能になったもの)に関連付けを行う
    //１：アプリ起動時に@RepositoryをIFを検索し、自動的にその実装クラスが作成される
    //２：そのインスタンスがアプリにBeanとして登録される
    //３：クラスがロードされる際に、@Autowiredが指定されているフィールドに対し、登録済みのBeanから同じクラスを検索
    //４：自動的にそのフィールドにインスタンスを割り当てる
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    private UserDataService userDataService;

    //EntityManagerのBeanを取得してフィールドに設定する。SpringBootの場合、起動時に自動的にBeanとしてインスタンスが登録されている
    //1クラスにつき1インスタンスしか用意されない
//    @PersistenceContext
//    EntityManager entityManager;

//    UserDataDaoImpl userDataDao;

    @PostConstruct
    public void init() {
        long answerTime = System.currentTimeMillis();

//        UserData userData4 = new UserData();
//        String uuid4 = UUID.randomUUID().toString();
//        userData4.setName("mia");
//        userData4.setUuid(uuid4);
//        userData4.setTotaltime(answerTime + 10000);
//        userDataRepository.saveAndFlush(userData4);
//
//        UserData userData = new UserData();
//        String uuid = UUID.randomUUID().toString();
//        userData.setName("kenjinak");
//        userData.setUuid(uuid);
//        userData.setTotaltime(answerTime);
//        userDataRepository.saveAndFlush(userData);
//
//        UserData userData2 = new UserData();
//        String uuid2 = UUID.randomUUID().toString();
//        userData2.setName("nakamura");
//        userData2.setUuid(uuid2);
//        userData2.setTotaltime(answerTime + 100);
//        userDataRepository.saveAndFlush(userData2);
//
//        UserData userData3 = new UserData();
//        String uuid3 = UUID.randomUUID().toString();
//        userData3.setName("john");
//        userData3.setUuid(uuid3);
//        userData3.setTotaltime(answerTime + 1000);
//        userDataRepository.saveAndFlush(userData3);
    }

    //@ModelAttribute：エンティティクラスのインスタンスを自動的に用意する。
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") UserData userData,
            ModelAndView modelAndView) {
        modelAndView.setViewName("welcome");
        modelAndView.addObject("title", "Find Page");
        modelAndView.addObject("msg", "this is sample content.");


        //ここはいらんデータ
        List<UserData> list = userDataService.getAll();
        modelAndView.addObject("datalist", list);

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView form(
            @ModelAttribute("formModel") @Validated UserData userData,
            RedirectAttributes redirectAttributes,
            BindingResult result,
            HttpServletResponse response,
            ModelAndView modelAndView) {
        ModelAndView res = null;
        if (!userData.getName().isEmpty()) {

            String uuid = UUID.randomUUID().toString();
            userData.setUuid(uuid);
            userData.setTotaltime(0);
            //引数のエンティティを永続化
            userDataService.saveUserData(userData);

            //Cookieに保存
            Cookie cookie = new Cookie("UUID", uuid);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);

            //redirectでデータを渡す
            redirectAttributes.addFlashAttribute("userData", userData);
            res = new ModelAndView("redirect:/prefirst");
        }else {
            modelAndView.setViewName("welcome");
            modelAndView.addObject("msg", "Error is Occuerdやわ");
//            Iterable<UserData> datalist = userDataService.getAll();
//            modelAndView.addObject("datalist", datalist);
            res = modelAndView;
        }
        return res;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView modelAndView) {
        modelAndView.setViewName("find");
        modelAndView.addObject("title", "Find Page");
        modelAndView.addObject("msg", "MyData Sample");
        modelAndView.addObject("value","");
        List<UserData> list = userDataService.getAll();
        modelAndView.addObject("datalist", list);
        return modelAndView;
    }


//    //@Transactional：仕組みメソッドないで実行されるデータベースアクセスが一括して実行される
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    @Transactional(readOnly=false)
//    public ModelAndView form(
//            //@Validate：Entityの各値を自動的にチェックする
//            //BindingResult：Validatedの結果をresultに入れている
//            @ModelAttribute("formModel") @Validated UserData userData,
//            BindingResult result,
//            ModelAndView modelAndView ){
//        ModelAndView res = null;
//        if (!result.hasErrors()) {
//            //引数のエンティティを永続化
//            userDataRepository.saveAndFlush(userData);
//            res = new ModelAndView("redirect:/");
//        }else {
//            modelAndView.setViewName("welcome");
//            modelAndView.addObject("msg", "Error is Occuerdやわ");
//            Iterable<UserData> datalist = userDataRepository.findAll();
//            modelAndView.addObject("datalist", datalist);
//            res = modelAndView;
//        }
//
//        return res;
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(
            @ModelAttribute UserData userData,
            @PathVariable int id,
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("edit");
        modelAndView.addObject("title", "edit data");
        Optional<UserData> data = userDataRepository.findById((long) id);
        modelAndView.addObject("formModel", data.get());
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute UserData userData,
                                ModelAndView modelAndView
    ) {
                userDataRepository.saveAndFlush(userData);
                return new ModelAndView("redirect:/");
    }
//    @Bean
//    public Database user_answear(CloudantClient cloudant) {
//        return cloudant.database("user_answear", true);
//    }

//    @RequestMapping("/welcome")
//    public String index() {
//
//
//        String userdata = "kenji";
//
//        Response r = null;
//        if (userdata != null) {
//            r = user_answear.save(userdata);
//        }
//        return r.getId();
//    }
//
//    @RequestMapping("/get")
//    public List<UserData> index(@RequestParam(required = false) Integer itemId) {
//// Get all documents from socialreviewdb
//        List<UserData> allDocs = null;
//        try {
//            if (itemId == null) {
//                allDocs = user_answear.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
//                        .getDocsAs(UserData.class);
//            } else {
//                // create Index
//                // Here is create a design doc named designdoc
//                // A view named querybyitemIdView
//                // and an index named itemId
//                user_answear.createIndex("querybyitemIdView", "designdoc", "json",
//                        new IndexField[]{new IndexField("itemId", IndexField.SortOrder.asc)});
//                System.out.println("Successfully created index");
//                allDocs = user_answear.findByIndex("{\"itemId\" : " + itemId + "}", UserData.class);
//            }
//        } catch (Exception e) {
//            System.out.println("Exception thrown : " + e.getMessage());
//        }
//        return allDocs;
//    }
}
