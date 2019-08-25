package ifpe.recife.tads.alerta_recife;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_ENDERECO")
@Access(AccessType.FIELD)
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_COORDENADA", referencedColumnName = "ID")
    private Coordenada coordenada;

    @NotNull
    @OneToOne(mappedBy = "endereco")
    private PontoDeRisco pontoDeRisco;

    @NotNull
    @Column(name = "RUA", length = 100)
    private String rua;

    @NotNull
    @Column(name = "NUMERO", length = 10)
    private String numero;

    @NotNull
    @Column(name = "BAIRRO", length = 100)
    private String bairro;

    @NotNull
    @Column(name = "CIDADE", length = 100)
    private String cidade;

    public Endereco() {

    }

    public Endereco(Long id, Coordenada coordenada, PontoDeRisco pontoDeRisco, String rua, String numero, String bairro, String cidade) {
        this.id = id;
        this.coordenada = coordenada;
        this.pontoDeRisco = pontoDeRisco;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
    public PontoDeRisco getPontoDeRisco() {
        return pontoDeRisco;
    }

    public void setPontoDeRisco(PontoDeRisco pontoDeRisco) {
        this.pontoDeRisco = pontoDeRisco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
