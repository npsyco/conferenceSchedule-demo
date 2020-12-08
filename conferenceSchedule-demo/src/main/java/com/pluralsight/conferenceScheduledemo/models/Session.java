package com.pluralsight.conferenceScheduledemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
//solves serialization
//ignores property: hibernateLazy and handler
//create an entity and have relationship, hibernate adds stub methods to
//handle lazy loading and eager loading of the relational data
//when serializing a hibernate object, dont serialize this because it will try
//to load in all relational data with sql and causes problems
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
        private Long session_id;
        private String session_name;
        private String session_description;
        private Integer session_length;
        
        @ManyToMany
        @JoinTable(
                name = "session_speakers",
                joinColumns = @JoinColumn(name = "session_id"),
                inverseJoinColumns = @JoinColumn(name = "speaker_id"))
        private List<Speaker> speakers;
        
    public Session(){
    }
    
    public List<Speaker> getSpeakers() {
        return speakers;
    }
    
    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }
    
    public Long getSession_id() {
        return session_id;
    }
    
    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }
    
    public String getSession_name() {
        return session_name;
    }
    
    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }
    
    public String getSession_description() {
        return session_description;
    }
    
    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }
    
    public Integer getSession_length() {
        return session_length;
    }
    
    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
