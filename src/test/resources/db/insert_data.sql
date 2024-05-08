INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role) VALUES ('ROLE_EMPLOYEE');

INSERT INTO users (user_id, active, password, username, role) VALUES (1, true, '$2a$10$6gNV5pNmtIkRFMuFfCOGweEQGfL5Xjxz2NAgoDMXgaqfVmp4j.by2', 'admin', 'ROLE_ADMIN');
INSERT INTO users (user_id, active, password, username, role) VALUES (2, true, '$2a$10$2QSRMb3PQL8R1xckwz5Xwu.b/EyHn37Z6.AEpaGDE3Z7e/XH69Vke', 'altin', 'ROLE_EMPLOYEE');
INSERT INTO users (user_id, active, password, username, role) VALUES (3, true, '$2a$12$7UsS35xTaqQ/cwNLfLnF4eCUBTJccJKQc/U0roz8BmMNHR2O0O6.a', 'test', 'ROLE_EMPLOYEE');

INSERT INTO doctors (doctor_id, name, specialization, username) VALUES (5, 'Doctor 1', 'Kardiolog', 'doctor1');
INSERT INTO doctors (doctor_id, name, specialization, username) VALUES (9, 'Doctor James', 'Test', 'doctor3');

INSERT INTO appointmets (appointment_id, end_time, note, patient_name, start_time, status, doctor, report) VALUES (1, '2023-09-23 20:00:00', 'string', 'string', '2023-09-23 19:00:00', 'CREATED', 5, null);
INSERT INTO appointmets (appointment_id, end_time, note, patient_name, start_time, status, doctor, report) VALUES (2, '2023-09-23 20:30:00', 'string', 'string', '2023-09-23 20:00:00', 'CREATED', 5, null);




