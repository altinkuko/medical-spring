CREATE TABLE IF NOT EXISTS medical_roles (
    role VARCHAR(50) primary key
);
INSERT INTO medical_roles (role)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (
        SELECT 1 FROM medical_roles WHERE role = 'ROLE_ADMIN'
    );

INSERT INTO medical_roles (role)
SELECT 'ROLE_EMPLOYEE'
WHERE NOT EXISTS (
        SELECT 1 FROM medical_roles WHERE role = 'ROLE_EMPLOYEE'
    );
