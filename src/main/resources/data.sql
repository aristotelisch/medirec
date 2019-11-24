INSERT INTO public.users (id, created_at, email, father_name, first_name, last_name, password, role)
VALUES (1, '2019-11-24 20:38:31.000000', 'dev@example.com', null, 'Telis', 'Christou', '123456', 'doctor')
ON CONFLICT ON CONSTRAINT users_pkey
    DO NOTHING;

INSERT INTO public.patients (id, amka, created_at, email, father_name, first_name, insurance_type, last_name, password,
                             role, telephone, user_id)
VALUES (1, '123456AMKA', '2019-11-24 20:39:44.000000', 'patient@example.com', 'Nikos', 'Telis', 'IKA', 'Christou',
        '123456', null, '2101234567', 1)
ON CONFLICT ON CONSTRAINT patients_pkey
    DO NOTHING;

INSERT INTO public.visits (id, clinical_examination, cost, created_at, note, reexamination_at, symptoms, patient_id)
VALUES (2, 'Test examination results', 50, '2019-11-25 00:06:15.190987', 'Test Note', null, null, 1)
ON CONFLICT ON CONSTRAINT visits_pkey
    DO NOTHING;

INSERT INTO public.lab_files (id, data, file_name, file_type, patient_id, visit_id)
VALUES ('d630bd6d-ed24-47af-99a2-65d9e9a751a3', 49051, 'Forrester-Future-of-CX.pdf', 'application/pdf', 1, 1)
ON CONFLICT ON CONSTRAINT lab_files_pkey
    DO NOTHING;
