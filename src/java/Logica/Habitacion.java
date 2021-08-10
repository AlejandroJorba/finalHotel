package Logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Habitacion implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    long id_habitacion;
    @Basic
    int num_habitacion;
    int piso_habitacion;
    double precio_habitacion;
    String nombre_habitacion;
    String tipo_habitacion;
    @OneToMany
    List<Reserva> reservasHabitacion = new ArrayList<>();

    public Habitacion() {
    }

    
    public Habitacion(long id_habitacion, int num_habitacion, int piso_habitacion, double precio_habitacion, String nombre_habitacion, String tipo_habitacion) {
        this.id_habitacion = id_habitacion;
        this.num_habitacion = num_habitacion;
        this.piso_habitacion = piso_habitacion;
        this.precio_habitacion = precio_habitacion;
        this.nombre_habitacion = nombre_habitacion;
        this.tipo_habitacion = tipo_habitacion;
    }

    public long getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public int getPiso_habitacion() {
        return piso_habitacion;
    }

    public void setPiso_habitacion(int piso_habitacion) {
        this.piso_habitacion = piso_habitacion;
    }

    public double getPrecio_habitacion() {
        return precio_habitacion;
    }

    public void setPrecio_habitacion(double precio_habitacion) {
        this.precio_habitacion = precio_habitacion;
    }

    public String getNombre_habitacion() {
        return nombre_habitacion;
    }

    public void setNombre_habitacion(String nombre_habitacion) {
        this.nombre_habitacion = nombre_habitacion;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public List<Reserva> getReservasHabitacion() {
        return reservasHabitacion;
    }

    public void setReservasHabitacion(List<Reserva> reservasHabitacion) {
        this.reservasHabitacion = reservasHabitacion;
    }


    

}
