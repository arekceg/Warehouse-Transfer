INSERT INTO `warehouse-management-TESTING`.authorities (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `warehouse-management-TESTING`.authorities (role_id, name) VALUES (2, 'ROLE_USER');

INSERT INTO `warehouse-management-TESTING`.users (id, enabled, name, password, username, warehouse_id) VALUES (1, 1, 'admin', '$2a$10$M/uYzo3dN45rp8LxVKfMcem4YBLAKGFlTXfipW.9NoARBo9Bupzlm', 'admin', null);
INSERT INTO `warehouse-management-TESTING`.users (id, enabled, name, password, username, warehouse_id) VALUES (2, 1, 'aa-name', '$2a$10$THTlXCX27GNC8212UD8HBeSvYB7t5/cnAs3KtBZv8ASvR.3qOtEA2', 'aa', null);
INSERT INTO `warehouse-management-TESTING`.users (id, enabled, name, password, username, warehouse_id) VALUES (3, 1, 'bb', '$2a$10$s9Nr2tdz9TvpDmIROT0yQ./530NGyrAoIAsRtp8UVOQspykMsJfQS', 'bb', null);
INSERT INTO `warehouse-management-TESTING`.users (id, enabled, name, password, username, warehouse_id) VALUES (4, 1, 'cc', '$2a$10$/NiJNkpEuVzsBtRMmRFifef529/ZDs35nlZJqJ.rGAWRfNQvo/KYi', 'cc', null);
INSERT INTO `warehouse-management-TESTING`.users (id, enabled, name, password, username, warehouse_id) VALUES (5, 1, 'ff', '$2a$10$ZR7FkGkC9sQJT/Mjg5yFh.o4FPhbBpG2uodIJ1.4byTK0g4sEi1/a', 'ff', null);

INSERT INTO `warehouse-management-TESTING`.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO `warehouse-management-TESTING`.user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO `warehouse-management-TESTING`.user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO `warehouse-management-TESTING`.user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO `warehouse-management-TESTING`.user_role (user_id, role_id) VALUES (5, 2);

INSERT INTO `warehouse-management-TESTING`.warehouses (id, name, manager_id) VALUES (1, 'admin-warehouse', 1);
UPDATE users SET warehouse_id = 1 WHERE id = 1;
INSERT INTO `warehouse-management-TESTING`.warehouses (id, name, manager_id) VALUES (2, 'aa-warehouse', 2);
UPDATE users SET warehouse_id = 2 WHERE id = 2;
INSERT INTO `warehouse-management-TESTING`.warehouses (id, name, manager_id) VALUES (3, 'bb_warehouse', 3);
UPDATE users SET warehouse_id = 3 WHERE id = 3;
INSERT INTO `warehouse-management-TESTING`.warehouses (id, name, manager_id) VALUES (4, 'ff_warehouse', 5);
UPDATE users SET warehouse_id = 5 WHERE id = 5;

INSERT INTO `warehouse-management-TESTING`.items (id, name) VALUES (1, 'Item1');
INSERT INTO `warehouse-management-TESTING`.items (id, name) VALUES (2, 'Item2');
INSERT INTO `warehouse-management-TESTING`.items (id, name) VALUES (3, 'Item3');
INSERT INTO `warehouse-management-TESTING`.items (id, name) VALUES (4, 'Item_nowy');

INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (1, 550, 'AVAILABLE', 1, 1);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (2, 50, 'AVAILABLE', 2, 1);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (3, 300, 'AVAILABLE', 3, 1);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (4, 190, 'AVAILABLE', 1, 2);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (5, 20, 'AVAILABLE', 3, 3);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (6, 65, 'AVAILABLE', 1, 3);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (7, 20, 'RESERVED', 2, 1);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (8, 10, 'AVAILABLE', 2, 2);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (9, 0, 'RESERVED', 1, 1);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (10, 0, 'RESERVED', 1, 2);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (11, 0, 'RESERVED', 2, 2);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (12, 10, 'AVAILABLE', 2, 3);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (13, 0, 'RESERVED', 1, 3);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (14, 0, 'RESERVED', 3, 3);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (15, 50, 'AVAILABLE', 3, 4);
INSERT INTO `warehouse-management-TESTING`.stocks (id, itemStock, stockType, item_id, warehouse_id) VALUES (16, 100, 'AVAILABLE', 4, 4);

INSERT INTO `warehouse-management-TESTING`.transfers (id, acceptedDate, challengedDate, createdDate, isAccepted, isChallenged, updatedDate, destinationWarehouse_id, sourceWarehouse_id) VALUES (2, '2019-06-07', null, '2019-06-07', true, false, null, 3, 1);
INSERT INTO `warehouse-management-TESTING`.transfers (id, acceptedDate, challengedDate, createdDate, isAccepted, isChallenged, updatedDate, destinationWarehouse_id, sourceWarehouse_id) VALUES (4, '2019-06-07', null, '2019-06-07', true, false, null, 3, 2);

INSERT INTO `warehouse-management-TESTING`.TransferContent (id, amount, item_id, transfer_id) VALUES (2, 50, 1, null);
INSERT INTO `warehouse-management-TESTING`.TransferContent (id, amount, item_id, transfer_id) VALUES (4, 10, 1, null);
INSERT INTO `warehouse-management-TESTING`.TransferContent (id, amount, item_id, transfer_id) VALUES (5, 10, 2, null);

INSERT INTO `warehouse-management-TESTING`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (2, 2);
INSERT INTO `warehouse-management-TESTING`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (4, 4);
INSERT INTO `warehouse-management-TESTING`.transfers_transferContents (Transfer_id, transferContents_id) VALUES (4, 5);