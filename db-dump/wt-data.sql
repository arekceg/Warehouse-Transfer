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

INSERT INTO `warehouse-management`.authorities (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `warehouse-management`.authorities (role_id, name) VALUES (2, 'ROLE_USER');

INSERT INTO `warehouse-management`.users (id, enabled, name, password, username, warehouse_id) VALUES (1, 1, 'admin', '$2a$10$M/uYzo3dN45rp8LxVKfMcem4YBLAKGFlTXfipW.9NoARBo9Bupzlm', 'admin', null);
INSERT INTO `warehouse-management`.users (id, enabled, name, password, username, warehouse_id) VALUES (2, 1, 'aa-name', '$2a$10$THTlXCX27GNC8212UD8HBeSvYB7t5/cnAs3KtBZv8ASvR.3qOtEA2', 'aa', null);
INSERT INTO `warehouse-management`.users (id, enabled, name, password, username, warehouse_id) VALUES (3, 1, 'bb', '$2a$10$s9Nr2tdz9TvpDmIROT0yQ./530NGyrAoIAsRtp8UVOQspykMsJfQS', 'bb', null);
INSERT INTO `warehouse-management`.users (id, enabled, name, password, username, warehouse_id) VALUES (4, 1, 'cc', '$2a$10$/NiJNkpEuVzsBtRMmRFifef529/ZDs35nlZJqJ.rGAWRfNQvo/KYi', 'cc', null);
INSERT INTO `warehouse-management`.users (id, enabled, name, password, username, warehouse_id) VALUES (5, 1, 'ff', '$2a$10$ZR7FkGkC9sQJT/Mjg5yFh.o4FPhbBpG2uodIJ1.4byTK0g4sEi1/a', 'ff', null);

INSERT INTO `warehouse-management`.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO `warehouse-management`.user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO `warehouse-management`.user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO `warehouse-management`.user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO `warehouse-management`.user_role (user_id, role_id) VALUES (5, 2);

INSERT INTO `warehouse-management`.warehouses (id, name, manager_id) VALUES (1, 'admin-warehouse', 1);
UPDATE users SET warehouse_id = 1 WHERE id = 1;
INSERT INTO `warehouse-management`.warehouses (id, name, manager_id) VALUES (2, 'aa-warehouse', 2);
UPDATE users SET warehouse_id = 2 WHERE id = 2;
INSERT INTO `warehouse-management`.warehouses (id, name, manager_id) VALUES (3, 'bb_warehouse', 3);
UPDATE users SET warehouse_id = 3 WHERE id = 3;
INSERT INTO `warehouse-management`.warehouses (id, name, manager_id) VALUES (4, 'ff_warehouse', 5);
UPDATE users SET warehouse_id = 5 WHERE id = 5;

INSERT INTO `warehouse-management`.items (id, name) VALUES (1, 'Item1');
INSERT INTO `warehouse-management`.items (id, name) VALUES (2, 'Item2');
INSERT INTO `warehouse-management`.items (id, name) VALUES (3, 'Item3');
INSERT INTO `warehouse-management`.items (id, name) VALUES (4, 'Item_nowy');

INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (1, 550, 'AVAILABLE', 1, 1);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (2, 50, 'AVAILABLE', 2, 1);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (3, 300, 'AVAILABLE', 3, 1);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (4, 190, 'AVAILABLE', 1, 2);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (5, 20, 'AVAILABLE', 3, 3);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (6, 65, 'AVAILABLE', 1, 3);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (7, 20, 'RESERVED', 2, 1);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (8, 10, 'AVAILABLE', 2, 2);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (9, 0, 'RESERVED', 1, 1);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (10, 0, 'RESERVED', 1, 2);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (11, 0, 'RESERVED', 2, 2);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (12, 10, 'AVAILABLE', 2, 3);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (13, 0, 'RESERVED', 1, 3);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (14, 0, 'RESERVED', 3, 3);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (15, 50, 'AVAILABLE', 3, 4);
INSERT INTO `warehouse-management`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (16, 100, 'AVAILABLE', 4, 4);

INSERT INTO `warehouse-management`.transfers (id, acceptedDate, challengedDate, createdDate, isAccepted, isChallenged, updatedDate, destinationWarehouse_id, sourceWarehouse_id) VALUES (2, '2019-06-07', null, '2019-06-07', true, false, null, 3, 1);
INSERT INTO `warehouse-management`.transfers (id, acceptedDate, challengedDate, createdDate, isAccepted, isChallenged, updatedDate, destinationWarehouse_id, sourceWarehouse_id) VALUES (4, '2019-06-07', null, '2019-06-07', true, false, null, 3, 2);

INSERT INTO `warehouse-management`.TransferContent (id, amount, item_id, transfer_id) VALUES (2, 50, 1, null);
INSERT INTO `warehouse-management`.TransferContent (id, amount, item_id, transfer_id) VALUES (4, 10, 1, null);
INSERT INTO `warehouse-management`.TransferContent (id, amount, item_id, transfer_id) VALUES (5, 10, 2, null);

INSERT INTO `warehouse-management`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (2, 2);
INSERT INTO `warehouse-management`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (4, 4);
INSERT INTO `warehouse-management`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (4, 5);


