INSERT INTO public.user_info (name, email, customer_ssn, password, roles, created_by, created_at)
SELECT 'Jonathan', 'Jonathan@loanapi.no', '23098334576', '$2a$10$EIFZcgaZLeg57tRPyLvd/OmajxzIm.AjLabQ2DW1CL8mks1Y4bvO.', 'ADMIN', 'SYSTEM', now()
WHERE NOT EXISTS (SELECT 1 FROM public.user_info WHERE customer_ssn = '23098334576');

INSERT INTO public.user_info (name, email, customer_ssn, password, roles, created_by, created_at)
SELECT 'Henrik', 'Henrik@loanapi.no', '29038934576', '$2a$10$nd3lMLfaWUKQC1lxpKaE4eqrBsLFvQ0CKunxssyf0tK/IHScZ0Cx6', 'USER', 'SYSTEM', now()
WHERE NOT EXISTS (SELECT 1 FROM public.user_info WHERE customer_ssn = '29038934576');

INSERT INTO public.user_info (name, email, customer_ssn, password, roles, created_by, created_at)
SELECT 'Arild', 'Arild@loanapi.no', '22037934576', '$2a$10$nd3lMLfaWUKQC1lxpKaE4eqrBsLFvQ0CKunxssyf0tK/IHScZ0Cx6', 'USER', 'SYSTEM', now()
WHERE NOT EXISTS (SELECT 1 FROM public.user_info WHERE customer_ssn = '22037934576');

INSERT INTO public.user_info (name, email, customer_ssn, password, roles, created_by, created_at)
SELECT 'Julian', 'Julian@loanapi.no', '21038234576', '$2a$10$nd3lMLfaWUKQC1lxpKaE4eqrBsLFvQ0CKunxssyf0tK/IHScZ0Cx6', 'USER', 'SYSTEM', now()
WHERE NOT EXISTS (SELECT 1 FROM public.user_info WHERE customer_ssn = '21038234576');