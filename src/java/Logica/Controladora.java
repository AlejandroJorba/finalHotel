package Logica;

import Persistencia.ControladoraPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    //-----------------Creo el empleado, a la vez creo y le asigno un usuario-----------------
    
    public void crearEmpleado (String usuario, String password, String cargo, int dni, String nombre, String apellido, String nacimiento, String direccion) throws ParseException {
      Empleado empleado = new Empleado();
      Usuario usu = new Usuario();
      
      
      //Parseo de fecha
      SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
      Date nac = formateo.parse(nacimiento);
       
       
      empleado.setNombre(nombre);
      empleado.setApellido(apellido);
      empleado.setDni(dni);
      empleado.setCargo(cargo);
      empleado.setNacimiento(nac);
      empleado.setDireccion(direccion);
      
      
     
      usu.setUsuario(usuario);
      usu.setPassword(password);
      usu.setUsuarioEmpleado(empleado);
      
      controlPersis.altaEmpleado(empleado, usu);
    }
    
    //-----------------Creo la habitación-----------------
    
    public void crearHabitacion (int num_habitacion, int piso_habitacion, long id_habitacion, double precio_habitacion, String nombre_habitacion, String tipo_habitacion){
        //Creo un objeto habitación
        Habitacion habitacion = new Habitacion();
        //Le seteo nuevos valores
        habitacion.setNombre_habitacion(nombre_habitacion);
        habitacion.setPiso_habitacion(piso_habitacion);
        habitacion.setPrecio_habitacion(precio_habitacion);
        habitacion.setTipo_habitacion(tipo_habitacion);
        habitacion.setNum_habitacion(num_habitacion);
        //Lo envio a la persistencia para crearlo
        controlPersis.altaHabitacion(habitacion);
    }
    
    //-----Creo la reserva, el huesped, le asigno un usuario, un huesped y una habitación-----
    
    
    public void crearReserva (int num_habitacion, int cant_personas, String usuarioRes, String check_in, String check_out, String profesion, int dni, String nombre, String apellido, String nacimiento, String direccion) throws ParseException {
       //Creo los objetos
       Reserva reserva = new Reserva();
       Huesped huesped = new Huesped();

       //Parseo fechas
       SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
       Date check_inParse = formateo.parse(check_in);
       Date check_outParse = formateo.parse(check_out);
       Date nacimientoHuesped = formateo.parse(nacimiento);
       
       //Coloco dia actual a la reserva
       Date fecha = new Date();
       
       //Calculo el precio final
       long checkIn = check_inParse.getTime();
       long checkOut = check_outParse.getTime();
       long diferencia = checkOut - checkIn;
       double tiempoHospedado = Math.floor(diferencia / (1000 * 60 * 60 * 24));

       //Coloco los datos del objeto reserva del JSP
       reserva.setCant_personas(cant_personas);
       reserva.setCheck_in(check_inParse);
       reserva.setCheck_out(check_outParse);
       reserva.setCant_noches(tiempoHospedado);
       reserva.setDiaCarga(fecha);
       
       
       //Le seteo los datos al huesped
       huesped.setApellido(apellido);
       huesped.setDireccion(direccion);
       huesped.setDni(dni);
       huesped.setNacimiento(nacimientoHuesped);
       huesped.setNombre(nombre);
       huesped.setProfesion(profesion);
       
       //Traigo un método que busca entre los huespedes
       Huesped huespedFrecuente = traerHuespedPorDni(dni);
       //Si existe le asigna la reserva, sino, crea una y crea un huesped
       if (huespedFrecuente != null) {
           reserva.setHuesped(huespedFrecuente);
       }
       else {
           reserva.setHuesped(huesped);
           controlPersis.altaHuesped(huesped);
       }
       
       //Seteo la habitación a la reserva
       Habitacion habRes = traerHabitacion(num_habitacion);
       reserva.setHabitacion(habRes);
       
       //Calculo y seteo el precio final
       double precioHab = habRes.getPrecio_habitacion();
       double precioFinal = tiempoHospedado * precioHab;
       reserva.setPrecio_final(precioFinal);
       
       //Seteo el usuario a la reserva
       Usuario usuReserva = traerUsuarioNombre(usuarioRes);
       reserva.setUsuario(usuReserva);
       
       
        System.out.println(usuarioRes);
        System.out.println(usuReserva);
       controlPersis.crearReserva(reserva);
    }
    
    
    //-----------------Traigo los huespedes para mostrarlos-----------------
    
    public List<Huesped> traerHuespedes() {
        return controlPersis.traerHuesped();
    }
    
    //-----------------Traigo las reservas para mostrarlas-----------------
    public List<Reserva> traerReservas() {
        return controlPersis.traerReserva();
    }
    //Traigo los usuarios   
    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }
    //Traigo las habitaciones
    public List<Habitacion> traerHabitaciones () {
        return controlPersis.traerHabitaciones();
    }
    
    //Traigo los empleados
    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
    }
    
    //-----------------Verifico si existe el usuario para el login-----------------
    public boolean verificarUsuario(String user, String password) {
        List <Usuario> listaUsuarios = controlPersis.traerUsuarios();
        if (listaUsuarios != null) {
            for (Usuario usu : listaUsuarios) {
                    if (usu.getUsuario().equals(user) && usu.getPassword().equals(password))
                    {       
                        return true;
                    }
            }
        }
        return false;
    }
    
    //Traigo al huesped por dni   
    public Huesped traerHuespedPorDni(int huespedDni){
        List<Huesped> listaHuesped = controlPersis.traerHuesped();
            for (Huesped huesped : listaHuesped){
                if(huesped.getDni() == huespedDni){
                    return huesped;
                }     
        } return null;
    }
    
    //Traigo la habitación por el número
    public Habitacion traerHabitacion(int num_habitacion){
        List<Habitacion> listaHabitaciones = controlPersis.traerHabitaciones();
        for (Habitacion hab : listaHabitaciones) {
            if (hab.getNum_habitacion() == num_habitacion) {
                return hab;
            }
        }
        return null;
    }
    
    //Traigo al usuario por el nombre
    public Usuario traerUsuarioNombre(String usuarioRes){
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        for (Usuario usuList: listaUsuarios) {
            if (usuList.getUsuario().equals(usuarioRes)){
                return usuList;
            }
        }
        return null;
    }
    
    //Traigo usuario de la reserva
    public List<Reserva> traerUsu(String usuarioConsulta){
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        for (Usuario us : listaUsuarios) {
            List<Reserva> lista = us.getReservasUsuario();
            String nombre = us.getUsuario();
           if (usuarioConsulta.equalsIgnoreCase(nombre))
               return lista;
        }
        return null;
    }

    //Elimino la reserva
    
    public void borrarReserva(long id) {
        controlPersis.eliminarReserva(id);
    }

    //Elimino el empleado
    public void borrarEmpleado(long id) {
        controlPersis.eliminarEmpleado(id);
    }
    //Elimio el usuario
    public void borrarUsuario(long id) {
        controlPersis.eliminarUsuario(id);
    }
    
    public void borrarHabitacion(long id) {
        controlPersis.eliminarHabitacion(id);
    }
    //Modificar reserva
    public Reserva traerReserva(long id) {
        return controlPersis.buscarReserva(id);
    }
    public void modificarReserva(Reserva res, Huesped hues){
        controlPersis.modificarReserva(res, hues);
    }
    
    public Usuario traerUsuario(long id) {
        return controlPersis.buscarUsuario(id);
    }
    
    public void modificarUsuario(Usuario us, Empleado empl) {
        controlPersis.modificarUsuario(us, empl);
    }
    
    public Habitacion traerHabitacion(long id) {
        return controlPersis.buscarHabitacion(id);
    }
    
    public void modificarHabitacion (Habitacion hab) {
        controlPersis.modificarHabitacion(hab);
    }
}