package ifpe.recife.tads.test;

import ifpe.recife.tads.alerta_recife.Administrador;
import ifpe.recife.tads.alerta_recife.Cargo;
import ifpe.recife.tads.alerta_recife.Contato;
import ifpe.recife.tads.alerta_recife.Usuario;
import ifpe.recife.tads.test.DataSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class UsuarioCRUDTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public UsuarioCRUDTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("alerta_recife");
        DataSet.inserirDados();

    }

    @AfterClass
    public static void tearDownClass() {

        emf.close();

    }

    @Before
    public void setUp() {

        em = emf.createEntityManager();
        beginTransaction();

    }

    @After
    public void tearDown() {

        commitTransaction();
        em.close();

    }

    private void beginTransaction() {

        et = em.getTransaction();
        et.begin();

    }

    private void commitTransaction() {

        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }

    }

    @Test
    public void t01_criaUsuario() {

        logger.info("Executando: criaUsuario");
        Usuario usuario = new Usuario();
        usuario.setEmail("pedro.dantas@gmail.com");
        usuario.setPrimeiroNome("Pedro");
        usuario.setUltimoNome("Dantas");
        usuario.setSenha("queue");
        usuario.setHabilitado(true);
        em.persist(usuario);
        em.flush();
        assertNotNull(usuario.getId());

    }

    // Ainda como teste t04 porque os demais testes de CRUD serão inseridos 
    @Test
    public void t04_atualizaDado() {
        logger.info("Executando: atualizaDado");
        Usuario usuario;
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorEmail", Usuario.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("email", "joanamendonca@gmail.com");
        usuario = (Usuario) query.getSingleResult();
        usuario.setUltimoNome("Souza"); // A usuária optou por usar o sobrenome de casamento
        em.flush();
        usuario = (Usuario) query.getSingleResult();
        assertEquals("Souza", usuario.getUltimoNome());
        
    }
    
}
