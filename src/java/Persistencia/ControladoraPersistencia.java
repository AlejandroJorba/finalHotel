package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    HuespedJpaController huespedJPA = new HuespedJpaController();
    HabitacionJpaController habitacionJPA = new HabitacionJpaController();
    ReservaJpaController reservaJPA = new ReservaJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    
    //----------------------Creación---------------------
    public void altaEmpleado (Empleado empleado, Usuario usu) {
        empleadoJPA.create(empleado);
        usuarioJPA.create(usu);
    }
    
    public void altaHabitacion (Habitacion habitacion){
        habitacionJPA.create(habitacion);
    }
    
    public void crearReserva (Reserva reserva) {
        reservaJPA.create(reserva);
    }
    
    public void altaHuesped (Huesped huesped) {
        huespedJPA.create(huesped);
    }
    
    //----------------------Traer---------------------

    public List<Huesped> traerHuesped() {  
       return huespedJPA.findHuespedEntities();
    }
    
    public List<Reserva> traerReserva() {
        return reservaJPA.findReservaEntities();
    }
    public List<Usuario> traerUsuarios(){
        return usuarioJPA.findUsuarioEntities();
    }
    public List<Empleado> traerEmpleados(){
        return empleadoJPA.findEmpleadoEntities();
    }
    public List<Habitacion> traerHabitaciones() {
        return habitacionJPA.findHabitacionEntities();
    }
    
    
    //----------------------Eliminar---------------------
    
    public void eliminarReserva(long id) {
        try {
            reservaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado(long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarUsuario(long id){
        try {
            usuarioJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarHabitacion(long id) {
        try {
            habitacionJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //----------------------Modificar---------------------
    
    public Reserva buscarReserva (long id){
        return reservaJPA.findReserva(id);
    }
    
    public Usuario buscarUsuario (long id) {
        return usuarioJPA.findUsuario(id);
    }

    public void modificarUsuario(Usuario us, Empleado empl) {
        try {
            usuarioJPA.edit(us);
            empleadoJPA.edit(empl);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarReserva(Reserva res, Huesped hues) {
        try {
            reservaJPA.edit(res);
            huespedJPA.edit(hues);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Habitacion buscarHabitacion (long id) {
        return habitacionJPA.findHabitacion(id);
    }
    
    public void modificarHabitacion (Habitacion hab) {
        try {
            habitacionJPA.edit(hab);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

