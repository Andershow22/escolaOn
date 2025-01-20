create database escolaOn;
use escolaoN;


create table professor(
	`id` int not null auto_increment,
    `nome` varchar(50),
    `data_de_nascimento` varchar(10),
    `CPF` varchar(14),
    `telefone` varchar(20),
    `login` varchar(20),
    `senha` int,
    `disciplina` varchar(25),
    `carga_Horaria` int,
    `salario` double,
    `comunicado` text,
    primary key(id)
);


create table aluno(
	`id` int not null auto_increment,
    `nome` varchar(50),
    `data_de_nascimento` varchar(10),
    `CPF` varchar(14),
    `telefone` varchar(20),
    `nota1` float default 0,
    `nota2` float default 0,
    `nota3` float default 0,
    `aprovacao` varchar(20),
    `turma_id` int,
    primary key (id)
);

create table turma(
	`turma` int not null auto_increment,
    `tarefa_id` int,
    primary key(turma)
);

alter table aluno
	add foreign key (turma_id) references turma(turma);
    
create table tarefa(
	id int not null auto_increment,
    `nome` varchar(20),
    `data_entrega` varchar(10),
    `descricao` varchar(200),
    `turma_id` int,
    `professor_id` int,
     primary key(id),
     foreign key (turma_id) references turma(turma),
     foreign key (professor_id) references professor(id)
);
    
alter table turma 
	add foreign key (tarefa_id) references tarefa(id);
    
create table usuario(
	id int not null auto_increment,
    id_aluno int,
    id_professor int,
    permissao varchar(25),
    login varchar(20),
    senha int,
    primary key (id),
    foreign key (id_aluno) references aluno(id),
    foreign key (id_professor) references professor(id)
);

