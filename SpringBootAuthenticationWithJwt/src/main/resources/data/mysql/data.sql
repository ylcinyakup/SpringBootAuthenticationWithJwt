USE springBootAuthenticationWithJwt;


SET AUTOCOMMIT=0;

INSERT INTO role (role_id, name) VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

COMMIT;
