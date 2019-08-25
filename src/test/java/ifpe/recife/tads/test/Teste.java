package ifpe.recife.tads.test;

import ifpe.recife.tads.alerta_recife.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

        logger.info("Executando: criarUsuarioValido");
        Usuario usuario = new Usuario();
        usuario.setEmail("italo@gmail.com");
        usuario.setPrimeiroNome("Italo");
        usuario.setUltimoNome("Dantas");
        usuario.setSenha("1234");
        usuario.setHabilitado(true);
        em.persist(usuario);
        em.flush();
        assertNotNull(usuario.getId());

    }
/*
    @Test
    public void recuperarUsuarioPorLogin() {

        logger.info("Executando: recuperarUsuarioPorLogin");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        assertTrue(usuario.getLogin().equals("MARCOSANDRE"));

    }

    public void recuperarUsuarioPorNome() {

        logger.info("Executando: recuperarUsuarioPorNome");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorNome", Usuario.class);
        query.setParameter("primeironome", "%Silva%");
        query.setParameter("ultimonome", "%Silva%");
        List<Usuario> usuarios = query.getResultList();

        for (Usuario usuario : usuarios) {
            assertTrue((usuario.getPrimeiroNome().contains("Silva")) || (usuario.getUltimoNome().contains("Silva")));
        }

        assertEquals(1, usuarios.size());
    }

    @Test
    public void modificarSenhaUsuario() {

        logger.info("Executando: modificarSenhaUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        String novaSenha = "newsenha2";
        usuario.setSenha(novaSenha);
        em.flush();
        assertTrue(usuario.getSenha().equals(novaSenha));

    }

    @Test
    public void modificarEmailUsuario() {

        logger.info("Executando: modificarEmailUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        String newMail = "newemail@teste.com";
        usuario.setEmail(newMail);
        em.flush();
        assertEquals(newMail, usuario.getEmail());

    }

    @Test
    public void modificarExpiracaoSenhaUsuario() {

        logger.info("Executando: modificarExpiracaoSenhaUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date newDate = calendar.getTime();
        usuario.setDataExpiracaoSenha(newDate);
        em.flush();
        assertEquals(newDate, usuario.getDataExpiracaoSenha());

    }

    @Test
    public void desabilitarUsuario() {

        logger.info("Executando: desabilitarUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        usuario.setHabilitado(false);
        em.flush();
        assertEquals(false, usuario.isHabilitado());

    }

    @Test
    public void habilitarUsuario() {

        logger.info("Executando: habilitarUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        usuario.setHabilitado(true);
        em.flush();
        assertEquals(true, usuario.isHabilitado());

    }

    @Test
    public void removerUsuario() {

        logger.info("Executando: removerUsuario");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "MARCOSANDRE");
        Usuario usuario = (Usuario) query.getSingleResult();
        assertNotNull(usuario);
        em.remove(usuario);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

    // Testes Administrador
    @Test
    public void criarAdminValido() {

        logger.info("Executando: criarAdminValido");
        Administrador admin = new Administrador();
        admin.setLogin("LUNAM007");
        admin.setSenha("MgV3fTi8");
        admin.setPrimeiroNome("Luna");
        admin.setUltimoNome("Macedo");
        admin.setEmail("lunab@teste.com");
        admin.setHabilitado(true);
        Calendar calendar = Calendar.getInstance();
        admin.setDataCriacao(calendar.getTime());
        calendar.add(Calendar.MONTH, 3);
        admin.setDataExpiracaoSenha(calendar.getTime());
        admin.setRegistroAdmin("1578");
        admin.setCpfAdmin("775.443.160-25");
        em.persist(admin);
        em.flush();
        assertNotNull(admin.getId());

    }

    @Test
    public void modificarRegistroAdmin() {

        logger.info("Executando: modificarRegistroAdmin");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "LUNAM007");
        Administrador admin = (Administrador) query.getSingleResult();
        String novoRegistro = "9800";
        admin.setRegistroAdmin(novoRegistro);
        em.flush();
        assertEquals(novoRegistro, admin.getRegistroAdmin());

    }

    @Test
    public void modificarCpfAdmin() {

        logger.info("Executando: modificarCpfAdmin");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "LUNAM007");
        Administrador admin = (Administrador) query.getSingleResult();
        String novoCpf = "738.072.940-31";
        admin.setCpfAdmin(novoCpf);
        em.flush();
        assertEquals(novoCpf, admin.getCpfAdmin());

    }

    @Test
    public void recuperarAdminPorRegistro() {

        logger.info("Executando: recuperarAdminPorRegistro");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorRegistro", Administrador.class);
        String registro = "9800";
        query.setParameter("registro", registro);
        Administrador admin = (Administrador) query.getSingleResult();
        assertTrue(admin.getRegistroAdmin().equals(registro));

    }

    @Test
    public void recuperarAdminPorCpf() {

        logger.info("Executando: recuperarAdminPorCpf");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorCpf", Administrador.class);
        String cpf = "738.072.940-31";
        query.setParameter("cpf", cpf);
        Administrador admin = (Administrador) query.getSingleResult();
        assertTrue(admin.getCpfAdmin().equals(cpf));

    }

    @Test
    public void removerAdmin() {

        logger.info("Executando: removerAdmin");
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.RecuperarPorLogin", Usuario.class);
        query.setParameter("login", "LUNAM007");
        Administrador admin = (Administrador) query.getSingleResult();
        assertNotNull(admin);
        em.remove(admin);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

    //Teste Função
    @Test
    public void criarFuncao() {

        logger.info("Executando: criarFuncao");
        Funcao funcao = new Funcao();
        funcao.setDescricao("ELETROTECNICO");
        em.persist(funcao);
        em.flush();
        assertNotNull(funcao.getId());

    }

    @Test
    public void modificarFuncao() {

        logger.info("Executando: modificarFuncao");
        TypedQuery<Funcao> query = em.createNamedQuery("Funcao.RecuperarPorDescricao", Funcao.class);
        String descricao = "ELETROTECNICO";
        query.setParameter("descricao", descricao);
        Funcao funcao = (Funcao) query.getSingleResult();
        String novaDescricao = "TECNICO";
        funcao.setDescricao(novaDescricao);
        em.flush();
        assertEquals(novaDescricao, funcao.getDescricao());

    }

    public void recuperarFuncoes() {

        logger.info("Executando: recuperarFuncoes");
        TypedQuery<Funcao> query = em.createNamedQuery("Funcao.RecuperarFuncoes", Funcao.class);
        List<Funcao> funcoes = query.getResultList();
        assertEquals(5, funcoes.size());

    }

    public void recuperarFuncaoPorDescricao() {

        logger.info("Executando: recuperarFuncaoPorDescricao");
        TypedQuery<Funcao> query = em.createNamedQuery("Funcao.RecuperarPorDescricao", Funcao.class);
        String descricao = "TECNICO";
        query.setParameter("descricao", descricao);
        Funcao funcao = (Funcao) query.getSingleResult();
        assertTrue(funcao.getDescricao().equals(descricao));

    }

    public void removerFuncao() {

        logger.info("Executando: removerFuncao");
        TypedQuery<Funcao> query = em.createNamedQuery("Usuario.RecuperarPorDescricao", Funcao.class);
        String descricao = "TECNICO";
        query.setParameter("descricao", descricao);
        Funcao funcao = (Funcao) query.getSingleResult();
        assertNotNull(funcao);
        em.remove(funcao);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

    //Teste Subestacao
    @Test
    public void criarSubestacao() {

        logger.info("Executando: criarSubestacao");
        Subestacao subestacao = new Subestacao();
        subestacao.setDescricao("Nova Hamburgo");
        em.persist(subestacao);
        em.flush();
        assertNotNull(subestacao.getId());

    }

    @Test
    public void modificarSubestacao() {

        logger.info("Executando: modificarSubestacao");
        TypedQuery<Subestacao> query = em.createNamedQuery("Subestacao.RecuperarPorDescricao", Subestacao.class);
        String descricao = "Nova Hamburgo";
        query.setParameter("descricao", descricao);
        Subestacao subestacao = (Subestacao) query.getSingleResult();
        String novaDescricao = "Nova Hamburgo I";
        subestacao.setDescricao(novaDescricao);
        em.flush();
        assertEquals(novaDescricao, subestacao.getDescricao());

    }

    public void recuperarSubestacoes() {

        logger.info("Executando: recuperarSubestacoes");
        TypedQuery<Subestacao> query = em.createNamedQuery("Subestacao.RecuperarSubestacoes", Subestacao.class);
        List<Subestacao> subestacoes = query.getResultList();
        assertEquals(6, subestacoes.size());

    }

    public void recuperarSubestacaoPorDescricao() {

        logger.info("Executando: recuperarSubestacaoPorDescricao");
        TypedQuery<Subestacao> query = em.createNamedQuery("Subestacao.RecuperarPorDescricao", Subestacao.class);
        String descricao = "Nova Hamburgo I";
        query.setParameter("descricao", descricao);
        Subestacao subestacao = (Subestacao) query.getSingleResult();
        assertTrue(subestacao.getDescricao().equals(descricao));

    }

    public void removerSubestacao() {

        logger.info("Executando: removerSubestacao");
        TypedQuery<Subestacao> query = em.createNamedQuery("Subestacao.RecuperarPorDescricao", Subestacao.class);
        String descricao = "Nova Hamburgo I";
        query.setParameter("descricao", descricao);
        Subestacao subestacao = (Subestacao) query.getSingleResult();
        assertNotNull(subestacao);
        em.remove(subestacao);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }
    
    //Teste Centro de Custo
    @Test
    public void criarCentroDeCusto() {

        logger.info("Executando: criarCentroDeCusto");
        CentroDeCusto centro = new CentroDeCusto();
        centro.setDescricao("4698");
        em.persist(centro);
        em.flush();
        assertNotNull(centro.getId());

    }

    public void recuperarCentrosDeCusto() {

        logger.info("Executando: recuperarCentrosDeCusto");
        TypedQuery<CentroDeCusto> query = em.createNamedQuery("CentroDeCusto.RecuperarCentros", CentroDeCusto.class);
        List<CentroDeCusto> centros = query.getResultList();
        assertEquals(4, centros.size());

    }

    public void recuperarCentrosPorDescricao() {

        logger.info("Executando: recuperarCentrosPorDescricao");
        TypedQuery<CentroDeCusto> query = em.createNamedQuery("CentroDeCusto.RecuperarPorDescricao", CentroDeCusto.class);
        String descricao = "4698";
        query.setParameter("descricao", descricao);
        CentroDeCusto centro = (CentroDeCusto) query.getSingleResult();
        assertTrue(centro.getDescricao().equals(descricao));

    }

    public void removerCentro() {

        logger.info("Executando: removerCentro");
        TypedQuery<CentroDeCusto> query = em.createNamedQuery("CentroDeCusto.RecuperarPorDescricao", CentroDeCusto.class);
        String descricao = "4698";
        query.setParameter("descricao", descricao);
        CentroDeCusto centro = (CentroDeCusto) query.getSingleResult();
        assertNotNull(centro);
        em.remove(centro);
        em.flush();
        assertEquals(0, query.getResultList().size());

    } */

}
