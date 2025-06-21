# Fit GymAI

### Back-end do projeto FitGymAi usando Spring boot junto com a intregração com a API do Gemini.
<hr>
## Diagrama

<img src="https://github.com/MOliveiraDev/Fit-GymAI/blob/main/assets/Fit-Gym.AI%20Diagrama.jpg"></img>
<hr>
## Funcionalidades

- Cadastro/Login/Logout de usuários.
- Controle de acesso: apenas os ADMINS podem acessar o endpoint de criação de treinos e de cadastro de usuários.
- Integrado com a API do Gemini para geração de treinos personalizados.
- Salvamento de treinos no banco de dados (PostgreSQL).
- Documentação da API com Swagger.
- Videos armazenados no bucket do AWS S3.
- Validação de tokens JWT para autenticação e autorização.

## Tecnologias Utilizadas
- Java 21
- Spring Boot (Web, Data JPA, Security)
- PostgreSQL
- AWS (S3)
- Swagger
- API do Gemini
- Jwt
- Maven
- Docker

# End points

## Autenticação

| Método | Rota                 | Acesso     | Descrição                    |
| ------ | -------------------- |------------|------------------------------|
| POST   | `/api/auth/register` | ADMIN      | Registro de usuário          |
| POST   | `/api/auth/login`    | USER/ADMIN | Login com retorno de token   |
| POST   | `/api/auth/logout`   | USER/ADMIN |  Logout (blacklist do token) |



## WorkPlan
| Método | Rota                              | Acesso     | Descrição                              |
| ------ | --------------------------------- | ---------- | -------------------------------------- |
| POST   | `/api/workplan/generate/{userId}` | ADMIN      | Gera ficha para o usuário especificado |
| GET    | `/api/workplan/my`                | USER/ADMIN | Retorna a ficha do usuário logado      |


## Body Json para gerar a ficha
```json
{
  "age": 24,
  "height": 1.75,
  "weight": 73.0,
  "arm": 34.0,
  "chest": 98.0,
  "waist": 83.0,
  "abdomen": 85.0,
  "hip": 92.0,
  "thigh": 59.0,
  "calf": 38.0,
  "biotype": "Ectomorfo"
}
```
