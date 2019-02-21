package services;

import dao.TeacherDao;
import entity.Teacher;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Stefan Ohlsson
 */
@Stateless
@Path("entity.teacher")
public class DaoRest {

    @Inject // injekt skapas vid run-time
    TeacherDao tdao; // injekta för att få ett objekt av typen

    // Vi post-ar, lägger till lärare i db
    // Consumes - accepterar    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createTeacher(Teacher t) {
        tdao.addTeacher(t);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Teacher> getAll() {
        return tdao.findAll();
    }

    @PUT   // Läsa in via 
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void edit(Teacher t) {   // tar emot en lärare, konsumerae xml/json
        tdao.editTeacher(t);        // anropar tdao

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Teacher getById(@PathParam("id") Integer id) {
        return tdao.findById(id);
    }

    // REMOVE
    @DELETE
    @Path("{id}")
    public void DeleteById(@PathParam("id") Integer id) {
        tdao.removeTeacher2(id);
    }

}
