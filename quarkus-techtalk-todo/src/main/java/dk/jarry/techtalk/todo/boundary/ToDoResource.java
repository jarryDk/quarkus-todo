package dk.jarry.techtalk.todo.boundary;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.annotation.security.DenyAll;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import dk.jarry.techtalk.todo.entity.ToDo;

@Path("todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {

    @Inject
	ToDoService toDoService;

	@POST
	@Counted(name = "createPerformed", description = "How many create have been performed.")
	@Timed(name = "createTimer", description = "A measure of how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)
	public ToDo create(ToDo toDo) {
		return toDoService.create(toDo);
	}

	@GET
	@Path("{id}")
	public ToDo read(@PathParam("id") Integer id) {
		return toDoService.read(id);
	}

	@PUT
	@Path("{id}")
	public ToDo update(@PathParam("id") Integer id, ToDo toDo) {
		return toDoService.update(id, toDo);
	}

	@DELETE
	@Path("{id}")	
	public void delete(@PathParam("id") Integer id) {
		toDoService.delete(id);
	}

	@GET
	public List<ToDo> list( //
			@QueryParam("from") Long from, //
			@QueryParam("limit") Long limit) {
		return toDoService.list(from, limit);
	}

}
