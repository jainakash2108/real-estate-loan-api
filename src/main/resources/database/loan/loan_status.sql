INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 1,
       1,
       'SUBMITTED',
       'Loan application submitted',
       'Henrik',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 1);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 2,
       1,
       'IN_REVIEW',
       'Loan application under review',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 2);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 3,
       1,
       'APPROVED',
       'Loan application has been approved',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 3);


INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 4,
       2,
       'SUBMITTED',
       'Loan application submitted',
       'Arild',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 4);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 5,
       2,
       'IN_REVIEW',
       'Loan application under review',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 5);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 6,
       2,
       'REJECTED',
       'Loan application has been approved',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 6);


INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 7,
       3,
       'SUBMITTED',
       'Loan application submitted',
       'Arild',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 7);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 8,
       3,
       'IN_REVIEW',
       'Loan application under review',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 8);


INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 9,
       4,
       'SUBMITTED',
       'Loan application submitted',
       'Julian',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 9);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 10,
       4,
       'IN_REVIEW',
       'Loan application under review',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 10);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 11,
       4,
       'APPROVED',
       'Loan application has been approved',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 11);


INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 12,
       5,
       'SUBMITTED',
       'Loan application submitted',
       'Julian',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 12);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 13,
       5,
       'IN_REVIEW',
       'Loan application under review',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 13);

INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 14,
       5,
       'REJECTED',
       'Loan application has been approved',
       'Jonathan',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 14);


INSERT INTO public.loan_status (loan_id, status, description, status_by, created_at)
SELECT 15,
       6,
       'SUBMITTED',
       'Loan application submitted',
       'Julian',
       now() WHERE NOT EXISTS (SELECT 1 FROM public.loan_status WHERE id = 15);
