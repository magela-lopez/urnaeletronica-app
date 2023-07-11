
/*Inserindo dados na tabela Cargos*/
INSERT INTO cargos VALUES(1, "Presidente", 2);
INSERT INTO cargos VALUES(2, "Governador", 2);
INSERT INTO cargos VALUES(3, "Senador", 1);
INSERT INTO cargos VALUES(4, "Deputado federal", 1);
INSERT INTO cargos VALUES(5, "Deputado estadual", 1);

/*Inserindo dados na tabela Partidos*/
INSERT INTO partidos VALUES (1, "PT", 13);
INSERT INTO partidos VALUES (2, "PL", 22);
INSERT INTO partidos VALUES (3, "PDT", 12);
INSERT INTO partidos VALUES (4, "DC", 27);
INSERT INTO partidos VALUES (5, "NOVO", 30);
INSERT INTO partidos VALUES (6, "PTB", 14);
INSERT INTO partidos VALUES (7, "MDB", 15);
INSERT INTO partidos VALUES (8, "PCB", 21);
INSERT INTO partidos VALUES (9, "UNIAO", 44);
INSERT INTO partidos VALUES (10, "PSTU", 16);
INSERT INTO partidos VALUES (11, "PSC", 20);
INSERT INTO partidos VALUES (12, "PSDB", 45);
INSERT INTO partidos VALUES (13, "PP", 11);
INSERT INTO partidos VALUES (14, "PSB", 40);
INSERT INTO partidos VALUES (15, "PSD", 55);
INSERT INTO partidos VALUES (16, "REPUBLICANOS", 10);
INSERT INTO partidos VALUES (17, "AVANTE", 70);

/*Inserindo dados na tabela Eleicao*/
INSERT INTO eleicao VALUES (1, "2023-07-10", false, "Primeiro Turno", 100, NULL , NULL , NULL , NULL );
INSERT INTO eleicao VALUES (2, "2023-08-20", false, "Segundo Turno", 100, NULL , NULL , NULL , NULL );

/*Inserindo dados na tabela Candidatos*/

/*Presidencia*/

INSERT INTO candidatos VALUES(1, FALSE, "Ciro Gomes" ,12, 0, "Ana Paula Matos" ,1, 3);
INSERT INTO candidatos VALUES(2, FALSE, "Felipe D'Avila" ,30, 0, "Tiago Mitraud" , 1, 5);
INSERT INTO candidatos VALUES(3, FALSE, "Jair Bolsonaro" ,22, 0, "Braga Netto", 1, 2);
INSERT INTO candidatos VALUES(4, FALSE, "Lula" ,13, 0, "Geraldo Alckmin", 1 ,1);
INSERT INTO candidatos VALUES(5, FALSE, "Padre Kelmon" ,14, 0,  "Pastor Gamonal", 1, 6);
INSERT INTO candidatos VALUES(6, FALSE, "Simone Tebet" ,15, 0, "Mara Gabrilli",1, 7);
INSERT INTO candidatos VALUES(7, FALSE, "Soraya Thronicke" ,44, 0, "Marcos Cintra", 1, 9);

/*Governador*/
INSERT INTO candidatos VALUES(8, FALSE, "Argenta" ,20, 0, "Nivea Rosa", 2, 11 );
INSERT INTO candidatos VALUES(9, FALSE, "Carlos Messalla" ,21, 0, "Edson Canabarro", 2, 8);
INSERT INTO candidatos VALUES(10, FALSE, "Edegar Pretto" ,13, 0, "Pedro Ruas", 2, 1);
INSERT INTO candidatos VALUES(11, FALSE, "Eduardo Leite" ,45, 0,  "Gabriel Souza" ,2,12);
INSERT INTO candidatos VALUES(12, FALSE, "Luis Carlos Heinze" ,11, 0, "Psicóloga Tanise Sabino" ,2, 13);
INSERT INTO candidatos VALUES(13, FALSE, "Onyx Lorenzoni" ,22, 0, "Cláudia Jardim", 2, 2);
INSERT INTO candidatos VALUES(14, FALSE, "Rejane de Oliveira" ,16, 0, "Vera Rosane de Oliveira", 2,10);
INSERT INTO candidatos VALUES(15, FALSE, "Ricardo Jobim" ,30, 0, "Rafael Dresch", 2, 5);
INSERT INTO candidatos VALUES(16, FALSE, "Vicente Bogo" ,40, 0,  "Josi Paz", 2, 14);
INSERT INTO candidatos VALUES(17, FALSE, "Vieira da Cunha" ,12, 0, "Professora Regina", 2, 3);

/*Senador*/
INSERT INTO candidatos VALUES(18, FALSE, "Ana Amélia Lemos" ,555, 0, NULL, 3, 15);
INSERT INTO candidatos VALUES(19, FALSE, "Fabiana Sanguiné" ,160, 0, NULL, 3, 10);
INSERT INTO candidatos VALUES(20, FALSE, "Hamilton Mourão" ,100, 0, NULL, 3, 16);
INSERT INTO candidatos VALUES(21, FALSE, "Maristela Zanotto" ,200, 0, NULL, 3, 11);
INSERT INTO candidatos VALUES(22, FALSE, "Olívio Dutra" ,131, 0, NULL, 3, 1);
INSERT INTO candidatos VALUES(23, FALSE, "Paulo Rosa" ,270, 0, NULL, 3, 4);
INSERT INTO candidatos VALUES(24, FALSE, "Professor Nado" ,700, 0, NULL, 3, 17);
INSERT INTO candidatos VALUES(25, FALSE, "Sanny Figueiredo" ,400, 0, NULL, 3, 14);

/*Deputado Federal*/
INSERT INTO candidatos VALUES(26, FALSE, "Abdias Felipe Franco" ,7022, 0, NULL, 4, 17);
INSERT INTO candidatos VALUES(27, FALSE, "Ada Cristina Munaretto" ,2221, 0, NULL, 4, 2);
INSERT INTO candidatos VALUES(28, FALSE, "Ademir Jose Andrioli Gonzatto" ,1121, 0, NULL, 4, 13);
INSERT INTO candidatos VALUES(29, FALSE, "Adão Renato Schultz Costa" ,2016, 0, NULL, 4, 11);
INSERT INTO candidatos VALUES(30, FALSE, "Adriana Leite Da Silva" ,2250, 0, NULL, 4, 2);

/*Deputado Estadual*/
INSERT INTO candidatos VALUES(31, FALSE, "Abner Dos Santos Dillmann" ,45500, 0, NULL, 5, 12);
INSERT INTO candidatos VALUES(32, FALSE, "Adalberto De Oliveira Noronha" ,13313, 0, NULL, 5, 1);
INSERT INTO candidatos VALUES(33, FALSE, "Adamir Vivan" ,44244, 0, NULL, 5, 9);
INSERT INTO candidatos VALUES(34, FALSE, "Adao Cleiton Leal Da Silva" ,14114, 0, NULL, 5, 6);
INSERT INTO candidatos VALUES(35, FALSE, "Adavilson De Castilhos Magagnin" ,45145, 0, NULL, 5, 12);
INSERT INTO candidatos VALUES(36, FALSE, "Adelmo Antônio De Souza" ,10110, 0, NULL, 5, 16);
INSERT INTO candidatos VALUES(37, FALSE, "Ademar Sarzi Sartori" ,14999, 0, NULL, 5, 6);
INSERT INTO candidatos VALUES(38, FALSE, "Admar Eugenio Pozzobom" ,45678, 0, NULL, 5, 12);
INSERT INTO candidatos VALUES(39, FALSE, "Adolfo José Brito" ,11240, 0, NULL, 5, 13);


