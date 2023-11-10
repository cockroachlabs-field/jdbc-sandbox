create table product
(
    id          uuid           not null default gen_random_uuid(),
    inventory   int            not null,
    name        string         not null,
    description jsonb          null,
    price       numeric(19, 2) not null,
    sku         string         not null,

    primary key (id)
);
