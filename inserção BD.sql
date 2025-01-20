use escolaOn;

insert into professor(`nome`,`data_de_nascimento`, `CPF`, `telefone`, `disciplina`, `carga_Horaria`,`salario`, `comunicado`) values
	("Dioclécio perereira", "22-05-1987", "111.111.111-11", "5184579965", "português",40 , 4568.50, "comunicado de teste");

insert into turma(`turma`) values(201),(101);

insert into aluno(`nome`,`data_de_nascimento`, `CPF`, `telefone`, `turma_id`) values
    ("Fernando Dias", "12-02-2009", "789.789.789-10", "51976440871", 101),
    ("Douglas Cunha", "20-07-2008", "123.123.123-12", "5194598265", 201),
    ("Gabriela Rocha", "08-12-2008", "456.456.456-45","51294772615", 201);  


 insert into usuario(permissao, login, senha) values 
 ("administrador","adm123",123);

-- insert para alunos usuários
 insert into usuario (id_aluno, permissao, login, senha) values 
 (1, "aluno", "fernando123", 123),
 (2, "aluno","douglas123",123),
 (3, "aluno", "gabriela123", 123);

-- insert para professor usuário
 insert into usuario(id_professor, permissao, login, senha) values
 (1, "professor", "dioclecio123", 123);
 
 insert into tarefa(`nome`, `data_entrega`, `descricao`, `turma_id`, `professor_id`) values
 ("verbos e pronomes", "25-12-2024", "pesquisar definição de verbo e pronome no dicionário", 201, 1);


 