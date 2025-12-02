# 🏋️ FitGymGPT

### Sistema SaaS de gestão de academias com geração de treinos personalizados usando IA (Google Gemini)

<hr>

### 📊 Diagrama de Arquitetura

<img src="https://github.com/MOliveiraDev/Fit-GymAI/blob/main/assets/FitGymApp.jpg"></img>

<hr>

## 🚀 Funcionalidades

### 👤 Gestão de Usuários
- **Múltiplos tipos de usuário**: USER, PERSONAL_TRAINER, CEO_TRAINER, ROOT
- **Autenticação JWT** com blacklist de tokens
- **Registro diferenciado** por tipo de usuário com validações específicas
- **Perfis especializados**: Personal Trainers (CREF) e CEOs (CNPJ)

### 🏢 Gestão de Academias
- **Cadastro de academias** com informações completas (CNPJ, endereço, horários)
- **Relacionamento CEO-Academia**: Um CEO pode gerenciar múltiplas academias
- **Status automático**: Sistema de scheduling que atualiza status (OPEN/CLOSED) baseado em horários
- **Gestão de membros**: Controle de usuários vinculados a cada academia

### 💪 Planos de Treino com IA
- **Integração com Google Gemini AI** para geração de treinos personalizados
- **Análise completa**: Idade, biotipo, medidas corporais, objetivos, experiência
- **Strategy Pattern**: Construção modular de prompts (lesões, equipamentos, restrições)
- **Treinos semanais** com exercícios, séries, repetições e vídeos demonstrativos
- **Persistência**: Salvamento de treinos no PostgreSQL

### 🔒 Segurança
- **Spring Security** com autenticação baseada em roles
- **JWT tokens** com validação e expiração
- **Token blacklist** para logout seguro
- **Controle de acesso granular** por endpoint

### 📝 Validações
- **Strategy Pattern** para validações de registro
- **Validações específicas** por tipo de usuário
- **Bean Validation** com mensagens customizadas

<hr>

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.5.0**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **PostgreSQL** (Banco de dados)
- **Flyway** (Migrations)

### Segurança & Autenticação
- **JWT (jjwt 0.11.0)**
- **BCrypt** (Password encoding)

### Integrações
- **Google Gemini AI** (Geração de treinos)
- **RestTemplate** (HTTP Client)

### Documentação & Qualidade
- **SpringDoc OpenAPI 3** (Swagger UI)
- **Lombok** (Redução de boilerplate)
- **Hibernate Validator**

### DevOps
- **Maven** (Build tool)
- **Docker** (Containerização)
- **Dotenv** (Gestão de variáveis de ambiente)

<hr>

## 📋 Endpoints da API

### 🔐 Autenticação (`/api/auth`)

| Método | Rota                        | Acesso                    | Descrição                           |
|--------|----------------------------|---------------------------|-------------------------------------|
| POST   | `/api/auth/register`       | Público                   | Registro de usuário comum           |
| POST   | `/api/auth/register/admin` | ROOT                      | Registro de CEO Trainer             |
| POST   | `/api/auth/register/admin` | ROOT, CEO_TRAINER         | Registro de Personal Trainer        |
| POST   | `/api/auth/login`          | Público                   | Login com retorno de token JWT      |
| POST   | `/api/auth/logout`         | Autenticado               | Logout (adiciona token à blacklist) |

### 💪 Planos de Treino (`/api/workplan`)

| Método | Rota                              | Acesso                    | Descrição                              |
|--------|-----------------------------------|---------------------------|----------------------------------------|
| POST   | `/api/workplan/generate/{userId}` | PERSONAL_TRAINER, CEO     | Gera plano de treino para usuário      |
| GET    | `/api/workplan/my`                | USER, PERSONAL, CEO       | Retorna plano de treino do usuário     |

### 🏢 Academias (`/api/gym`)

| Método | Rota                    | Acesso      | Descrição                        |
|--------|------------------------|-------------|----------------------------------|
| POST   | `/api/gym/create`      | CEO_TRAINER | Cria nova academia               |
| GET    | `/api/gym/{id}`        | Autenticado | Busca academia por ID            |
| GET    | `/api/gym/my`          | CEO_TRAINER | Lista academias do CEO           |
| PUT    | `/api/gym/{id}`        | CEO_TRAINER | Atualiza dados da academia       |

<hr>

## 📦 Estrutura do Projeto

```
FitGymGpt/
├── config/              # Configurações (Security, Swagger, Scheduling)
├── controller/          # Controllers REST
├── database/
│   ├── domain/         # Entidades JPA
│   └── repository/     # Repositories
├── dto/                # Data Transfer Objects
├── enums/              # Enumerações (Roles, Status)
├── exception/          # Exception handlers
├── service/            # Lógica de negócio
└── strategy/           # Strategy Pattern (Validações, Prompts)
```

<hr>

## 🗄️ Modelo de Dados

### Entidades Principais

- **UserEntity**: Usuário base (autenticação)
- **PersonalEntity**: Perfil de Personal Trainer (CREF, especialização)
- **CeoTrainerEntity**: Perfil de CEO (anos de experiência)
- **GymCenterEntity**: Academia (CNPJ, horários, status)
- **WorkPlan**: Plano de treino gerado pela IA

### Relacionamentos

- **User ↔ GymCenter**: ManyToOne (usuário pertence a uma academia)
- **CeoTrainer ↔ GymCenter**: OneToMany (CEO pode ter várias academias)
- **User ↔ PersonalEntity**: OneToOne (perfil de personal)
- **User ↔ CeoTrainerEntity**: OneToOne (perfil de CEO)
- **User ↔ WorkPlan**: OneToMany (usuário pode ter vários treinos)

<hr>

## 📄 Exemplos de Requisições

### Registro de Usuário Comum
```json
{
  "username": "João Silva",
  "email": "joao@email.com",
  "password": "senha123",
  "birthDate": "1995-05-15",
  "gender": "Masculino"
}
```

### Registro de CEO Trainer
```json
{
  "username": "Maria CEO",
  "email": "maria@gym.com",
  "password": "senha123",
  "birthDate": "1985-03-20",
  "gender": "Feminino",
  "businessRegistration": "12.345.678/0001-90",
  "companyName": "FitGym Ltda",
  "yearsExperience": 10
}
```

### Registro de Personal Trainer
```json
{
  "username": "Carlos Personal",
  "email": "carlos@gym.com",
  "password": "senha123",
  "birthDate": "1990-08-10",
  "gender": "Masculino",
  "crefNumber": "123456-G/SP",
  "specialization": "Musculação e Hipertrofia",
  "yearsExperience": 5
}
```

### Geração de Plano de Treino
```json
{
  "age": 28,
  "height": 1.75,
  "weight": 80.0,
  "gender": "Masculino",
  "biotype": "Mesomorfo",
  "leftArm": 35.0,
  "rightArm": 35.5,
  "leftForearm": 28.0,
  "rightForearm": 28.0,
  "shoulders": 115.0,
  "chest": 105.0,
  "waist": 85.0,
  "abdomen": 88.0,
  "hip": 98.0,
  "leftThigh": 58.0,
  "rightThigh": 58.5,
  "leftCalf": 38.0,
  "rightCalf": 38.0,
  "fitnessGoal": "Hipertrofia",
  "experienceLevel": "Intermediário",
  "weeklyWorkoutDays": 5,
  "preferredWorkoutTime": "Manhã",
  "injuries": ["Dor no joelho esquerdo"],
  "availableEquipment": ["Halteres", "Barras", "Máquinas"]
}
```

### Criar Academia
```json
{
  "gymCenterName": "FitGym Centro",
  "gymDescription": "Academia completa no centro da cidade",
  "gymPhone": "(11) 98765-4321",
  "gymEmail": "contato@fitgym.com",
  "taxId": "12.345.678/0001-90",
  "gymWebsite": "https://fitgym.com",
  "gymCenterAddress": "Rua Principal, 123 - Centro",
  "openingTime": "06:00",
  "closingTime": "22:00"
}
```

<hr>

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 21+
- PostgreSQL 14+
- Maven 3.8+
- Docker (opcional)

### Variáveis de Ambiente (.env)
```properties
DB_URL=jdbc:postgresql://localhost:5432/fitgymgpt
DB_USERNAME=postgres
DB_PASSWORD=senha
JWT_SECRET=sua-chave-secreta-aqui
GEMINI_API_KEY=sua-api-key-gemini
GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro
```

### Executar Localmente
```bash
# Clone o repositório
git clone https://github.com/MOliveiraDev/Fit-GymAI.git

# Entre no diretório
cd FitGymGpt

# Configure as variáveis de ambiente
cp .env.example .env
# Edite o .env com suas credenciais

# Execute com Maven
mvn spring-boot:run
```

### Executar com Docker
```bash
# Build da imagem
docker build -t fitgymgpt .

# Execute o container
docker-compose up -d
```

### Acessar Documentação
Após iniciar a aplicação, acesse:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

<hr>

## 🎯 Padrões de Projeto Utilizados

- **Strategy Pattern**: Validações de registro e construção de prompts
- **Repository Pattern**: Acesso a dados
- **DTO Pattern**: Transferência de dados entre camadas
- **Builder Pattern**: Construção de objetos complexos
- **Dependency Injection**: Inversão de controle com Spring

<hr>

## 🔮 Roadmap

- [ ] Implementar sistema de pagamentos
- [ ] Adicionar notificações push
- [ ] Dashboard de analytics para CEOs
- [ ] Sistema de agendamento de aulas
- [ ] Integração com wearables (smartwatches)
- [ ] App mobile (React Native)
- [ ] Testes automatizados (JUnit, Mockito)
- [ ] CI/CD com GitHub Actions

<hr>

## 📝 Licença

Este projeto está sob a licença MIT.

<hr>

## 👨‍💻 Autor

**Matheus Oliveira**
- GitHub: [@MOliveiraDev](https://github.com/MOliveiraDev)

<hr>

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
