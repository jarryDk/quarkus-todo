package dk.jarry.techtalk.todo.boundary;

import java.util.List;

import javax.transaction.Transactional;

import javax.ws.rs.core.Context;
import javax.annotation.security.DenyAll;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import dk.jarry.techtalk.todo.entity.ToDo;

@Path("todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {

    
	@POST
	@Counted(name = "createPerformed", description = "How many create have been performed.")
	@Timed(name = "createTimer", description = "A measure of how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)
	@Transactional	
	public ToDo create(ToDo toDo) {
		toDo.persist();
		return toDo;
	}

	@GET
	@Path("{id}")
	public ToDo read(@PathParam("id") Integer id) {
		ToDo toDo = ToDo.findById(id);
		if (toDo == null) {
			throw new WebApplicationException( //
					"toDo with id of " + id + " does not exist.", //
					Response.Status.NOT_FOUND);
		}
		return toDo;
	}

	@PUT
	@Path("{id}")
	@Transactional
	public ToDo update(@PathParam("id") Integer id, ToDo toDo) {
		if(ToDo.findById(id) == null) {
			throw new WebApplicationException( //
					"toDo with id of " + id + " does not exist.", //
					Response.Status.NOT_FOUND);
		}
		toDo.persist();
		return toDo;
	}

	@DELETE
	@Path("{id}")	
	@Transactional
	public void delete(@PathParam("id") Integer id) {
		ToDo toDo = ToDo.findById(id);
		if(toDo == null) {
			throw new WebApplicationException( //
					"toDo with id of " + id + " does not exist.", //
					Response.Status.NOT_FOUND);
		}
		toDo.delete();
	}

	@GET
	public List<ToDo> list( //
			@DefaultValue("0") @QueryParam("from") int from, //
			@DefaultValue("100") @QueryParam("limit") int limit) {

		return ToDo.findAll() //
			.page(from, limit) //
			.list();
	}

}
