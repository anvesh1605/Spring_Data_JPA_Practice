alter table profiles
drop foreign key fk_user_profile;

alter table profiles
    add constraint fk_user_profile
        foreign key (user_id) references users (id);