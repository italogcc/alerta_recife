# ALERTA_RECIFE
Projeto Alerta Recife

O Alerta Recife possui código desenvolvido no Netbeans 8.2 e faz uso do container EJB, do servidor Glassfish 4.1.1 e SGBD MySQL 5.7. É preferível executar o projeto em SO Linux como Ubuntu.

## Instruções de uso
- Ao abrir o projeto na IDE deve-se aguardar o carregamento inicial e editar o arquivo "pom.xml" localizado em "Arquivos do Projeto";
- Localizar a tag: 
> <glassfish.embedded-static-shell.jar>/home/henrique/glassfish-4.1.1/glassfish/lib/embedded/glassfish-embedded-static-shell.jar</glassfish.embedded-static-shell.jar>

ou 

> <glassfish.embedded-static-shell.jar>C:\\Programas\\glassfish\\4.1.1\\glassfish\\lib\\embedded\\glassfish-embedded-static-shell.jar</glassfish.embedded-static-shell.jar>

e editar o caminho do arquivo "glassfish-embedded-static-shell.jar" de onde se está instalado o Glassfish;
- Após salvar o ajuste no "pom.xml" construir o projeto com as dependências;
- Iniciar o servidor Glassfish e acessar o endereço de localhost, normalmente <http://localhost:4848/>;
- Em "Common Tasks > Resources" clicar no botão "Add Resources" e localizar o arquivo "glassfish-resources.xml" do projeto. O arquivo fica localizado em <\src\main\webapp\WEB-INF>;
- Verificar em "Common Tasks > Resources > JDBC", clicar em JDBC Resources e verificar na coluna JNDI Name o registro de "jdbc/alerta_recife" com Connection Pool "mysql_alerta_recife_rootPool";
- Realizar o logon no MySQL com login:root e senha:root;
- Criar uma base de dados com o comando "create database alerta_recife;";
- De volta ao IDE Netbeans, executar o projeto a partir do "index.html".
