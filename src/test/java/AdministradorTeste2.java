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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class AdministradorTeste2 {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AdministradorTeste2() {
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
    public void t01_criaAdministrador() {

        logger.info("Executando: criaAdministrador");
        Administrador admin = new Administrador();
        admin.setEmail("paulo.gomes@gmail.com");
        admin.setSenha("iUyBm12");
        admin.setPrimeiroNome("Paulo");
        admin.setUltimoNome("Gomes");
        admin.setHabilitado(true);
        admin.setMatricula("12098651");
        admin.setCargo(Cargo.TECNICO.numCargo);
        em.persist(admin);
        em.flush();
        assertNotNull(admin.getId());

    }

    @Test
    public void t02_recuperaAdministradorPorMatricula() {

        logger.info("Executando: recuperaAdministradorPorMatricula");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorMatricula", Administrador.class);
        query.setParameter("matricula", "12098651");
        Administrador adm = (Administrador) query.getSingleResult();
        assertTrue(adm.getMatricula().equals("12098651"));

    }

    @Test
    public void t03_recuperaAdministradorPorCargo() {

        logger.info("Executando: recuperaAdministradorPorCargo");
        TypedQuery<Administrador> query = em.createNamedQuery("Usuario.RecuperarPorCargo", Administrador.class);
        query.setParameter("cargo", Cargo.TECNICO.numCargo);
        List<Administrador> adms = query.getResultList();
        adms.forEach((Administrador adm) -> {
            assertTrue(adm.getCargo() == Cargo.TECNICO.numCargo);
        });
        assertEquals(2, adms.size());

    }

    @Test
    public void t04_atualizaDado() {
        logger.info("Executando: atualizaDado");
        Administrador adm;
        String consulta = "SELECT a FROM Administrador a WHERE a.matricula = :matricula";
        Query query = em.createQuery(consulta);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("matricula", "96785321");
        //query.setParameter("cargo", Cargo.TECNICO.numCargo);
        adm = (Administrador) query.getSingleResult();
        adm.setCargo(Cargo.ANALISTA.numCargo);
        em.flush();
        adm = (Administrador) query.getSingleResult();
        assertEquals(2, adm.getCargo());

    }

    @Test
    public void t05_removeAdministrador() {

        logger.info("Executando: removeAdministrador");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorMatricula", Administrador.class);
        query.setParameter("matricula", "12098651");
        Administrador adm = (Administrador) query.getSingleResult();
        assertNotNull(adm);
        em.remove(adm);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

}