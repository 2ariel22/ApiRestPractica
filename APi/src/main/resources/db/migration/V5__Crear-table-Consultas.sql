CREATE TABLE consultas (
                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                          `medico_id` BIGINT NOT NULL,
                          `fecha` DATETIME NOT NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT fk_consultas_medicos_id FOREIGN KEY(medico_id) REFERENCES medicos(Id));