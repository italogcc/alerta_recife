package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_ADMINISTRADOR")
@Access(AccessType.FIELD)
@DiscriminatorValue(value = "ADMIN")
@PrimaryKeyJoinColumn(name = "ID_ADMIN", referencedColumnName = "ID_USUARIO")
public class Administrador extends Usuario implements Serializable {
	
    @NotNull
    @Column(name="MATRICULA", unique=true, length = 15)
    private String matricula;

    @NotNull
    @Column(name="CARGO", length = 15)
    private Cargo cargo; 

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}