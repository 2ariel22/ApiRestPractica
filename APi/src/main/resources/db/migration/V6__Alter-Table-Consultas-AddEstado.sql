ALTER TABLE consultas ADD estado VARCHAR(100);
UPDATE consultas SET estado="FINALIZADA";