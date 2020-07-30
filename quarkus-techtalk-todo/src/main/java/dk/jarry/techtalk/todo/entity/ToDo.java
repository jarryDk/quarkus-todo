package dk.jarry.techtalk.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ToDo {

	@Id
	@SequenceGenerator( //
			name = "todosSequence", //
			sequenceName = "todos_id_seq", //
			allocationSize = 1, //
			initialValue = 10)
	@GeneratedValue( //
			strategy = GenerationType.SEQUENCE, //
			generator = "todosSequence")
    private Integer id;
    
    private String subject;
    private String body;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
    
}