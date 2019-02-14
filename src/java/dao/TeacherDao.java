package dao;

import entity.Teacher;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stefan Ohlsson // DAO-lager
 */
@Stateless
public class TeacherDao {

    @PersistenceContext(name = "TeacherRestPU")  // gör att vi får ett objekt av denna typ
    EntityManager em;

    public void addTeacher(Teacher t) {
        em.persist(t);// skapar objekt av typen Teacher

    }

    public void editTeacher(Teacher t) {
        em.merge(t); // om samma id, kolla merge om det finns och resten av info uppdateras med samma id
    }

    public void removeTeacher(Teacher t) {
        em.remove(em.merge(t));// merge bindeer ihop med motsvarande lärare i db och tar bort den sedan
    }

    public void removeTeacher2(Integer id) {
        em.remove(em.find(Teacher.class, id)); // tar ut id, letar i db, tar bort sedan
    }

    public Teacher findById(Integer id) {
        return em.find(Teacher.class, id);
    }

    // retur typ lista av lärare
    public List<Teacher> findAll() {
        return em.createQuery("SELECT t from Teacher t").getResultList();
    }

}
