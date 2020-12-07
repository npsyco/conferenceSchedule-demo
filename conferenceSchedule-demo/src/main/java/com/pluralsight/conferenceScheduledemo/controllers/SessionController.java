package com.pluralsight.conferenceScheduledemo.controllers;

import com.pluralsight.conferenceScheduledemo.models.Session;
import com.pluralsight.conferenceScheduledemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//tells the router what the mapping url looks like
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;
    
    // GET endpoint
    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }
    
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }
    
    @PostMapping
                        //spring mvc takes all attributes in json payload
                        //and auto marshall them into a session object 
                        //auto pass into session repo 
    public Session create(@RequestBody final Session session){
                        //saveAndFlush: using jpa and entities, objects can be saved
                        //as they are worked with, but doesn't get committed into the db.
                        //until it is flushed.
        return sessionRepository.saveAndFlush(session);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Also need to check for children records before deleting.
        //This prevents deletion when there are FKs
        //Thus this will only delete sessions and not children associated (FK violation!)
        //This can be done - lookup documentation 
        sessionRepository.deleteById(id);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //because this is PUT, expect all attributes to be passed in. A PATCH would only
        //allow a portion to be updated.
        //This method takes all attributes.
        //TODO: Validation that all attributes are passed in.
        Session existingSession = sessionRepository.getOne(id);
        //the ignored properties ignores the id, so that it wont be returned as null
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
    
}
