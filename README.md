# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="400" heigth="400" src="https://user-images.githubusercontent.com/101413385/173164615-192ca98a-1a44-480e-9229-9f82f456eec8.png">

</p>

## <img width="64" height="64" src="https://img.icons8.com/nolan/64/1A6DFF/C822FF/java-coffee-cup-logo.png" alt="java-coffee-cup-logo"/> Tecnologias Utilizadas:

- Java
- Eclipse
- MySql
- Biblioteca JCalendar
- Plugin WindowBuilder </br>

## <img width="64" height="64" src="https://img.icons8.com/color/48/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1"/>Requisitos para utilizar o aplicativo:

- Java
- MySQL Server (no **localhost:3306**)

## ⚙️ Configurando o MySQL:

- Criando o Banco de Dados (O nome deve ser **hotel_alura;**)
```sql
CREATE DATABASE hotel_alura;
```
- Acessando o Banco de Dados criado

```sql
USE hotel_alura;
```
- Criando a Tabela de Usuários

```sql
create table usuarios(
nome varchar (50),
senha varchar (50)
);
```
- Inserindo Seu Usuário na Aplicação à Sua Preferência
```sql
insert into usuarios (nome,senha) values('Igor','123456');
```
- Criando a Tabela de Reservas
```sql
create table reservas(
id int not null auto_increment,
data_entrada date not null,
data_saida date not null,
valor varchar(50),
forma_de_pagar varchar(50)not null,
primary key (id)
);
```
- Criando a Tabela de Hóspedes
```sql
create table hospedes(
id int not null auto_increment,
nome varchar(50) not null,
sobrenome varchar(50)not null,
data_nascimento date not null,
nacionalidade varchar (50) not null,
telefone varchar (50) not null,
id_reserva int not null,
primary key (id),
foreign key (id_reserva) references reservas(id)
);
```
# FIM...




