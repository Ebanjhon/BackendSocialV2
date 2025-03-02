SELECT usr.user_id, usr.username, usr.firstname, usr.lastname,
usr.email, usr.avatar, usr.active, 
FROM public."user" usr 
JOIN public.profile pro ON usr.user_id = pro.user_id 
WHERE usr.username = '';

-- SELECT usr.user_id, usr.username, usr.firstname, usr.lastname,
-- usr.email, usr.avatar, usr.active, pro.birth_date, pro.date_joid, pro.bio, pro.gender, pro.phone_number
-- FROM public."user" usr 
-- JOIN public.profile pro ON usr.user_id = pro.user_id 
-- WHERE usr.username = 'eban123';

