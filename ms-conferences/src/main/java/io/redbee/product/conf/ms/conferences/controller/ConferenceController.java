package io.redbee.product.conf.ms.conferences.controller;
import io.redbee.product.conf.ms.conferences.Dao.ConferenceDao;
import io.redbee.product.conf.ms.conferences.models.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/conference")
public class ConferenceController {

   // @Autowired
    ConferenceDao conferenceDao;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createConference(@RequestBody Conference conference) {  // recibo a travès de requestBody
        conferenceDao.save(conference);
    }

    // ver si puede ser útil devolver una Conference o con un CREATED alcanza

}








