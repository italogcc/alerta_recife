package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_COORDENADA")
@Access(AccessType.FIELD)
public class Coordenada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @NotNull
    @OneToOne(mappedBy = "coordenada")
    private Endereco endereco;
    
    @NotNull
    @Column(name = "PONTO_X")
    double pontoX; // 12 caracteres
    
    @NotNull
    @Column(name = "PONTO_Y")
    double pontoY; // 12 caracteres

    public Coordenada() {

    }

    public Coordenada(Long id, Endereco endereco, double pontoX, double pontoY) {
        this.id = id;
        this.endereco = endereco;
        this.pontoX = pontoX;
        this.pontoY = pontoY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public double getPontoX() {
        return pontoX;
    }

    public void setPontoX(double pontoX) {
        this.pontoX = pontoX;
    }

    public double getPontoY() {
        return pontoY;
    }

    public void setPontoY(double pontoY) {
        this.pontoY = pontoY;
    }

}
