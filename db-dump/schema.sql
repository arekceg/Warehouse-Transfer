create database warehouse-management;

create table TransferContent
(
    id          bigint auto_increment
        primary key,
    amount      int    not null,
    item_id     bigint not null,
    transfer_id bigint null,
    constraint FK4uf2hb53pnj4ffad4h4pdj70f
        foreign key (transfer_id) references transfers (id),
    constraint FKal9ayu8pkmgjddhq2h5obihth
        foreign key (item_id) references items (id)
);

create table authorities
(
    role_id bigint auto_increment
        primary key,
    name    varchar(255) null
);

create table items
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table stocks
(
    id           bigint auto_increment
        primary key,
    itemStock    int          not null,
    stockType    varchar(255) not null,
    item_id      bigint       not null,
    warehouse_id bigint       not null,
    constraint FKjftt43i266337pt7y8b291hpx
        foreign key (warehouse_id) references warehouses (id),
    constraint FKko9qvmybxt0opqoibsqrmivh8
        foreign key (item_id) references items (id)
);

create table transfers
(
    id                      bigint auto_increment
        primary key,
    acceptedDate            date   null,
    challengedDate          date   null,
    createdDate             date   null,
    isAccepted              bit    not null,
    isChallenged            bit    not null,
    updatedDate             date   null,
    destinationWarehouse_id bigint not null,
    sourceWarehouse_id      bigint not null,
    constraint FK73whp1ysj4shp6f6nc7fos51y
        foreign key (sourceWarehouse_id) references warehouses (id),
    constraint FKb2p8pq2fnyft6mhjf54uenbyt
        foreign key (destinationWarehouse_id) references warehouses (id)
);

create table transfers_transferContents
(
    Transfer_id         bigint not null,
    transferContents_id bigint not null,
    constraint UK_p06kudcbqsu5sq1chyg4hs1od
        unique (transferContents_id),
    constraint FKfdyx1yif7mihh0gp88kx51wrp
        foreign key (transferContents_id) references TransferContent (id),
    constraint FKhnugeh6rak3fu658fuyg5pta5
        foreign key (Transfer_id) references transfers (id)
);

create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK95oa6ae8l75auh3uwggwirr32
        foreign key (role_id) references authorities (role_id),
    constraint FKj345gk1bovqvfame88rcx7yyx
        foreign key (user_id) references users (id)
);

create table users
(
    id           bigint auto_increment
        primary key,
    enabled      int          not null,
    name         varchar(255) null,
    password     varchar(255) not null,
    username     varchar(255) not null,
    warehouse_id bigint       null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username),
    constraint FKpfeijlxk6hivbyuvedan09sqk
        foreign key (warehouse_id) references warehouses (id)
);

create table warehouses
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) null,
    manager_id bigint       not null,
    constraint FKg7kbfcmunhbgv2ssj5ruj93v4
        foreign key (manager_id) references users (id)
);
