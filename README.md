# Guia Completo: Desenvolvimento JSF no Eclipse com Banco Dockerizado

## 📋 Pré-requisitos
- [Eclipse IDE for Enterprise Java](https://www.eclipse.org/downloads/packages/)
- [JDK 8](https://www.oracle.com/java/technologies/javase-downloads.html) (Uma das versões mais estáveis do java até hoje)
- [Apache Tomcat 9.x](https://tomcat.apache.org/download-90.cgi)
- [Docker](https://www.docker.com/products/docker-desktop) (Mais versatilidade ao instanciar um banco de dados sem precisar se preocupar com instalação prévia)
- [Primefaces](https://www.primefaces.org/showcase/index.xhtml?jfwid=87707) (Melhor framework para JSF do mercado atualmente)
- [Maven] (Gerenciador de pacotes para melhorar a manutenção das bibliotecas de forma concisa)

## 🔧 Parte 1: Configurar Eclipse + Tomcat

### 1. Adicionar Tomcat no Eclipse
1. **Window** → **Preferences** → **Server** → **Runtime Environments**
2. Clique em **Add...**
3. Selecione **Apache Tomcat v9.0**
4. Aponte para a pasta de instalação do Tomcat
5. **Finish** → **Apply and Close**

### 2. Importar Projeto
1. **File** → **Import**
2. Selecione a pasta do projeto

### 3. Adicionar ao Tomcat
1. Na aba **Servers** → **New Server**
2. Selecione **Tomcat v9.0** → Adicione seu projeto
3. **Finish**

### 4. Instalando Pacotes
1. Clique com o botão direito no projeto
2. Selecione **Maven** → **Update Project**

---

## 🐋 Parte 2: Banco de Dados com Docker

### `docker-compose.yml`
```yaml
version: '3.8'

services:
  db:
    image: postgres:13
    environment:
      POSTGRES_DB: postgres
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 12345
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d appdb"]

volumes:
  postgres_data:
```
### Rodar o Contêiner
Já existe hoje um docker-compose no projeto que serve para subir a instancia do banco de dados sem dores de cabeça. Basta abrir o terminal de comando na raiz do projeto e executar o seguinte comando:
```sh
docker-compose up -d
```

---


## 📦 Parte 3: Configurar o Banco no `persistence.xml`

Certifique-se de configurar a conexão com o PostgreSQL no arquivo `persistence.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">

 
    <persistence-unit name="my-persistence-unit">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

     
        <class>br.com.sinerji.model.Pessoa</class>
        <class>br.com.sinerji.model.Endereco</class>

    
        <properties>
        
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            
          
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/postgres"/>
            
         
            <property name="javax.persistence.jdbc.user" value="postgres"/>
            <property name="javax.persistence.jdbc.password" value="12345"/>

           
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL9Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>

        </properties>
    </persistence-unit>
</persistence>

```

---

## 🛠 Parte 4: Solução de Problemas

### ❌ **Erro: Porta 5432 já está em uso**
Execute o seguinte comando para encontrar o processo que está ocupando a porta:

```sh
netstat -ano | findstr :5432  # Windows
lsof -i :5432  # Linux/Mac
```

Se necessário, mate o processo:

```sh
taskkill /PID <PID> /F  # Windows
kill -9 <PID>  # Linux/Mac
```

---



---
## ▶️ Parte 5: Rodando a Aplicação no Eclipse


### 1. Rodar a Aplicação
1. Na aba **Servers**, clique com o botão direito no **Tomcat v9.0** → **Add and Remove...**
2. Selecione o projeto → **Add** → **Finish**
3. Clique com o botão direito no **Tomcat v9.0** → **Start**
4. Acesse `http://localhost:8080/sinerji-project/Interfaces/CadastroPessoa.xhtml`

---

## 🎯 Conclusão
Agora sua aplicação está configurada no Eclipse com Tomcat e banco de dados rodando no Docker.

---

## 🧪 Como Testar a Aplicação

### 1. Cadastro e Listagem de Pessoas
1. Preencha os campos do formulário de cadastro (Nome, Idade, Sexo).
2. Clique em "Salvar" para adicionar a pessoa à lista.
3. Clique em "Excluir" para excluir um registro.
4. Clique em "Editar" Altere os dados e depois em "Salvar" para editar um registro

### 2. Cadastro e Edição de Endereços.
1. Na listagem de pessoas, clique em "Cadastrar Endereço" na linha correspondente.
2. Na nova página, preencha os dados do endereço e clique em "Salvar".
3. São as mesmas operações do cadastro de pessoa

