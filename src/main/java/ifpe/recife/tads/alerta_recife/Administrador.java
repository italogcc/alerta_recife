package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name="TB_ADMINISTRADOR")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Administrador.RecuperarPorMatricula",
                    query = "SELECT a FROM Administrador a WHERE a.matricula = :matricula"
            )
            ,
            @NamedQuery(
                    name = "Administrador.RecuperarPorCargo",
                    query = "SELECT a FROM Administrador a WHERE a.cargo = :cargo ORDER BY a.primeiroNome"
            )
        }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Administrador.RecuperarPorMatriculaSQL",
                    query = "SELECT * FROM TB_ADMINISTRADOR adm JOIN TB_USUARIO usr ON adm.ID_ADMIN = usr.ID WHERE adm.MATRICULA = ?",
                    resultClass = Administrador.class
            )
            ,
            @NamedNativeQuery(
                    name = "Administrador.RecuperarPorCargoSQL",
                    query = "SELECT * FROM TB_ADMINISTRADOR WHERE CARGO = ? ORDER BY PRIMEIRO_NOME",
                    resultClass = Administrador.class
            )
        }
)
@Access(AccessType.FIELD)
@DiscriminatorValue(value = "ADMIN")
@PrimaryKeyJoinColumn(name = "ID_ADMIN", referencedColumnName = "ID")
public class Administrador extends Usuario implements Serializable {
	
    @NotNull
    @Column(name="MATRICULA", unique=true, length = 15)
    private String matricula;

    @NotNull
    @Column(name="CARGO")
    private int cargo; 

    public Administrador(){
        
    }
    
    public Administrador(String matricula, int cargo) {
        this.matricula = matricula;
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int numCargo) {
        this.cargo = numCargo;
    }

}