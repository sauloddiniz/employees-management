## Employees Management

#### O 'Employees Management' é um projeto educacional que visa aprimorar e analisar a prática de escrita de código. Uma API foi criada para executar operações de CRUD (Criar, Ler, Atualizar, Deletar) nos dados dos vendedores, enquanto as filiais associadas podem apenas ser visualizadas, não modificadas.

## <a name="guia-de-uso"></a>Guia de uso
### <a name="pre-requisitos-local"></a>Pré-requisitos ambiente local
Antes de começar, certifique-se de ter os seguintes requisitos instalados no seu sistema:

- Java 17: Certifique-se de ter o JDK (Java Development Kit) 17 ou uma versão superior instalada no seu sistema.
- Maven: O projeto requer o Apache Maven para gerenciamento de dependências e compilação. Certifique-se de ter o Maven instalado no seu sistema.
- Banco de dados H2: O projeto utiliza o banco de dados em memória H2. O banco de dados iniciará automaticamente quando a aplicação for iniciada e será destruído quando a aplicação for encerrada.
- Docker: O projeto utiliza o Docker para executar o servidor WireMock que é usado para criar um servidor de mock das filiais. Você deve ter o Docker instalado no seu sistema.

### <a name="executar-api"></a>Executar
### <a name="ambiente-local"></a>ambiente local
No diretório raiz do projeto execute os comando em ordem
````
docker-compose up
mvn spring-boot:run  
````

### Documentacao dos controladores
Com o projeto em execução
* [Swagger-local](http://localhost:8080/swagger-ui/index.html)
