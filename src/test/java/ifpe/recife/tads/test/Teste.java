package ifpe.recife.tads.test;

import ifpe.recife.tads.alerta_recife.Administrador;
import ifpe.recife.tads.alerta_recife.Cargo;
import ifpe.recife.tads.alerta_recife.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
public class Teste {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public Teste() {
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

    // Testes Usuário
    @Test
    public void criaUsuario() {

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

    @Test
    public void recuperaUsuarioPorEmail() {

        logger.info("Executando: recuperaUsuarioPorEmail");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorEmail", Usuario.class);
        query.setParameter("email", "pedro.dantas@gmail.com");
        Usuario usuario = (Usuario) query.getSingleResult();
        assertTrue(usuario.getEmail().equals("pedro.dantas@gmail.com"));

    }

    public void recuperaUsuariosAtivos() {

        logger.info("Executando: recuperaUsuariosAtivos");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarAtivos", Usuario.class);
        query.setParameter("habilitado", true);
        List<Usuario> usuarios = query.getResultList();
        usuarios.forEach((Usuario usuario) -> {
            assertTrue(usuario.isHabilitado() == true);
        });
        assertEquals(3, usuarios.size());
    }

    @Test
    public void removeUsuario() {

        logger.info("Executando: removeUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorEmail", Usuario.class);
        query.setParameter("email", "pedro.dantas@gmail.com");
        Usuario usuario = (Usuario) query.getSingleResult();
        assertNotNull(usuario);
        em.remove(usuario);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

    // Testes Administrador
    @Test
    public void criaAdministrador() {

        logger.info("Executando: criaAdministrador");
        Administrador admin = new Administrador();
        admin.setEmail("paulo.gomes@gmail.com");
        admin.setSenha("iUyBm12");
        admin.setPrimeiroNome("Paulo");
        admin.setUltimoNome("Gomes");
        admin.setHabilitado(true);
        admin.setMatricula("12098651");
        admin.setCargo(Cargo.TECNICO);
        em.persist(admin);
        em.flush();
        assertNotNull(admin.getId());

    }

    @Test
    public void recuperaAdministradorPorMatricula() {

        logger.info("Executando: recuperaAdministradorPorMatricula");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorMatricula", Administrador.class);
        query.setParameter("matricula", "12098651");
        Administrador adm = (Administrador) query.getSingleResult();
        assertTrue(adm.getMatricula().equals("12098651"));

    }

    public void recuperaAdministradorPorCargo() {

        logger.info("Executando: recuperaAdministradorPorCargo");
        TypedQuery<Administrador> query = em.createNamedQuery("Usuario.RecuperarPorCargo", Administrador.class);
        query.setParameter("cargo", 3);
        List<Administrador> adms = query.getResultList();
        adms.forEach((Administrador adm) -> {
            assertTrue(adm.getCargo() == Cargo.TECNICO);
        });
        assertEquals(1, adms.size());

    }

    @Test
    public void removeAdministrador() {

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
