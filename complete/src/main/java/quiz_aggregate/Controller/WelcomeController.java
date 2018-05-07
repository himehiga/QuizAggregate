package quiz_aggregate.Controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String index() {
        return "welcome";
    }}
