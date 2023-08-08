# API de Reservas de Casa de Temporada - Backend

Este repositório contém o backend da aplicação de reservas de casa de temporada, desenvolvido usando Spring Boot. A aplicação permite que hóspedes façam reservas em datas específicas, fornecendo informações sobre as reservas e a quantidade de pessoas.

## Requisitos Técnicos

- Spring Boot: Framework utilizado para o desenvolvimento do projeto.
- H2 Database: Banco de dados em memória utilizado para persistência dos dados.
- API Rest: A aplicação expõe endpoints para realizar operações de reserva.

## Configuração e Execução

1. Clone este repositório para sua máquina local.
2. Certifique-se de ter o Java JDK e o Maven instalados.
   - **Java JDK:** Se você ainda não possui o Java JDK instalado, você pode baixá-lo no site oficial da Oracle ou usar um gerenciador de pacotes, dependendo do sistema operacional que você está usando.

     - **Windows:** Baixe o instalador do Java JDK no site oficial da Oracle: [Oracle Java SE Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)
     - **macOS:** Você pode usar o Homebrew para instalar o Java JDK:
       ```bash
       brew install openjdk
       ```
     - **Linux:** Use o gerenciador de pacotes apropriado para a sua distribuição Linux. Por exemplo, para Ubuntu/Debian:
       ```bash
       sudo apt-get update
       sudo apt-get install openjdk-11-jdk
       ```

   - **Maven:** O Maven é usado para compilar e gerenciar as dependências do projeto. Você pode baixar o Maven no site oficial do Apache Maven ou usar um gerenciador de pacotes.

     - **Windows:** Baixe o arquivo ZIP do Maven no site oficial do Apache Maven: [Apache Maven Download](https://maven.apache.org/download.cgi) e siga as instruções para configurar o PATH do sistema.
     - **macOS:** Você pode instalar o Maven usando o Homebrew:
       ```bash
       brew install maven
       ```
     - **Linux:** Use o gerenciador de pacotes apropriado para a sua distribuição Linux. Por exemplo, para Ubuntu/Debian:
       ```bash
       sudo apt-get update
       sudo apt-get install maven
       ```
3. Configure as propriedades do banco de dados no arquivo `application.properties`.
4. Execute o seguinte comando para iniciar a aplicação:

```bash
mvn spring-boot:run
```

### Observações

- Esta é uma API inicial, que poderá ser aprimorada no futuro com sistemas de login e autenticação.
- **Importante:** Somente hóspedes cadastrados no sistema podem criar reservas.


## Endpoints da API
### Criar um usuário

- **Método:** POST
- **Endpoint:** /auth/signup
- **Corpo da solicitação (JSON):**
  ```json
  {
      "nome": "Novo Usuário",
      "email": "usuario@example.com",
      "phoneNumber": "1234567890"
  }

### Criar uma reserva

- **Método:** POST
- **Endpoint:** /reservas
- **Corpo da solicitação (JSON):**
```json
{
   "nomeHospede": "Fulano de Tal",
   "dataInicio": "2023-08-10",
   "dataFim": "2023-08-15",
   "quantidadePessoas": 4
}
```
- **Resposta (JSON):**
```json
{
  "id": 1,
  "nomeHospede": "Fulano de Tal",
  "dataInicio": "2023-08-10",
  "dataFim": "2023-08-15",
  "quantidadePessoas": 4,
  "status": "CONFIRMADA"
}
```
### Obter todas as reservas

- **Método:** GET
- **Endpoint:** /reservas
- **Resposta (JSON):**
```json
[
  {
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
  },
  {
    "id": 2,
    "nomeHospede": "Ciclano da Silva",
    "dataInicio": "2023-09-01",
    "dataFim": "2023-09-05",
    "quantidadePessoas": 2,
    "status": "PENDENTE"
  },
  ...
]
```

### Obter uma reserva específica por ID

- **Método:** GET
- **Endpoint:** /reservas/{id}
- **Resposta (JSON):**
```json
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```
### Atualizar uma reserva existente

- **Método:** PUT
- **Endpoint:** /reservas/{id}
- **Corpo da solicitação (JSON):**
```json
{
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "PENDENTE"
}
```
- **Resposta (JSON):**
```json
{
  "id": 1,
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "PENDENTE"
}
```
### Cancelar uma reserva

- **Método:** DELETE
- **Endpoint:** /reservas/{id}/cancelar
- **Resposta (JSON):**
```json
{
  "id": 1,
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "CANCELADA"
}
```
