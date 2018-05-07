package quiz_aggregate.Controller;

import java.util.List;

import com.cloudant.client.api.CloudantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.cloudant.client.api.model.Response;
import quiz_aggregate.Model.UserData;

@RestController
@RequestMapping("/userdata")
public class UserDataRestController {

    @Autowired
    private Database user_answear;

    @Bean
    public Database user_answear(CloudantClient cloudant) {
        return cloudant.database("user_answear", true);
    }

    // Create a new review
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveUserData(@RequestBody UserData userdata) {
        System.out.println("Save UserData " + userdata);
        Response r = null;
        if (userdata != null) {
            r = user_answear.save(userdata);
        }
        return r.getId();
    }

    // Query reviews for all documents or by ItemId
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<UserData> getAll(@RequestParam(required=false) Integer itemId) {
        // Get all documents from socialreviewdb
        List<UserData> allDocs = null;
        try {
            if (itemId == null) {
                allDocs = user_answear.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
                        .getDocsAs(UserData.class);
            } else {
                // create Index
                // Here is create a design doc named designdoc
                // A view named querybyitemIdView
                // and an index named itemId
                user_answear.createIndex("querybyitemIdView","designdoc","json",
                        new IndexField[]{new IndexField("itemId",SortOrder.asc)});
                System.out.println("Successfully created index");
                allDocs = user_answear.findByIndex("{\"itemId\" : " + itemId + "}", UserData.class);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown : " + e.getMessage());
        }
        return allDocs;
    }
}

