# API de Reservas de Casa de Temporada - Backend

Este repositório contém o backend da aplicação de reservas de casa de temporada, desenvolvido usando Spring Boot. A aplicação permite que hóspedes façam reservas em datas específicas, fornecendo informações sobre as reservas e a quantidade de pessoas.

## Requisitos Técnicos

- Spring Boot: Framework utilizado para o desenvolvimento do projeto.
- H2 Database: Banco de dados em memória utilizado para persistência dos dados.
- API Rest: A aplicação expõe endpoints para realizar operações de reserva.

## Configuração e Execução

1. Clone este repositório para sua máquina local.
2. Certifique-se de ter o Java JDK e o Maven instalados.
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
