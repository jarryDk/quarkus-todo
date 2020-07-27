package dk.jarry.techtalk.todo.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class ToDo {

    @Id
    public Integer id;
    public String subject;
    public String body;

    @Temporal(TIMESTAMP)
	public Date createdDate;
    
}