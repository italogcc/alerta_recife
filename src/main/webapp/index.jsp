<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- CSS e Bootstrap CSS -->
        <link href="resources/css/frontend/index.css" rel="stylesheet">
        <link href="resources/css/frontend/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/frontend/jquery-ui.css" rel="stylesheet">
        <!-- JavaScript -->
        <script src="resources/js/frontend/jQuery.js"></script>
        <script src="resources/js/frontend/jquery-ui.js"></script>
        <script src="resources/js/frontend/jquery.min.js"></script>
        <script src="resources/js/frontend/popper.min.js"></script>
        <script src="resources/js/frontend/bootstrap.js"></script>
        <script src="resources/js/frontend/bootstrap.min.js"></script>
        
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <img id="logonImg" src="resources/imagens/logo.png"/>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <button type="button" class="btn" onclick='window.location = "indexLogin.xhtml"' >
                        Fazer Login
                    </button>
                    <button type="button" class="btn btn-success" onclick='window.location = "indexCadastro.xhtml"'>
                        Cadastrar-se
                    </button>  
                </div>
            </div>
        </nav>        
        <div id="mainContainer" class="container-fluid">
            <img id="mainLogo" src="resources/imagens/logo.png"/>
            <p id="explanation">
                Alerta Recife é a forma mais eficiente de informar <br/> 
                à prefeitura sobre incidentes com estruturas <br/>  
                de risco, deslizamento e alagamento.
            </p>	
            <p id="subExplanation">
                Este sistema permite uma maior aproximação entre cidadãos que residem  <br/>
                em áreas de risco e a Prefeitura do Recife, visando a prevenção de danos<br/>
                causados por fatores naturais <br/>
            </p>	
            <button id="cadBtnLight" type="button" onclick='window.location = "indexCadastro.xhtml"'>Cadastre-se no Alerta Recife</button>		
            <br><br>
            <a id="loginBtnLight" onclick='window.location = "indexLogin.xhtml"' href="#" >Fazer Login</a>
        </div>		
        <footer id="mainFooter">
            <p id="mainFooterText">
                Informe aqui as áreas de risco da cidade e solicite a estrutura necessária
            </p> 
        </footer>
    </div>
</body>
</html>
