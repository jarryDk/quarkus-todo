package dk.jarry.techtalk.todo.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class ToDo extends PanacheEntity {

    public String subject;
    public String body;

    @Temporal(TIMESTAMP)
	public Date createdDate;
    
}