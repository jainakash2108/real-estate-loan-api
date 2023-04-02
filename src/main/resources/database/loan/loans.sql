INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '29038934576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '29038934576');

INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '22037934576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '22037934576');

INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '22037934576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '22037934576');

INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '21038234576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '21038234576');

INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '21038234576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '21038234576');

INSERT INTO public.loan (customer_ssn, loan_amount, equity_amount, salary_amount)
SELECT '21038234576',
       100000,
       100000,
       100000 WHERE NOT EXISTS (SELECT 1 FROM public.loan WHERE customer_ssn = '21038234576');

