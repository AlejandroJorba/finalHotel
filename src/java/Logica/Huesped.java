package Logica;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Huesped implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    long id_huesped;
    @Basic
    int dni;
    String nombre;

    String apellido;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date nacimiento;
    String direccion;
    String profesion;
    @OneToMany
    List<Reserva> reservasHuesped = new ArrayList<>();

    public Huesped() {
    }

    public Huesped(long id_huesped, int dni, String nombre, String apellido, Date nacimiento, String direccion, String profesion) {
        this.id_huesped = id_huesped;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.direccion = direccion;
        this.profesion = profesion;
    }

    
    public long getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(long id_huesped) {
        this.id_huesped = id_huesped;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Reserva> getReservasHuesped() {
        return reservasHuesped;
    }

    public void setReservasHuesped(List<Reserva> reservasHuesped) {
        this.reservasHuesped = reservasHuesped;
    }


    
    
    
}
