Insert Into address (city, country, number, street, zip_code) Values ('Colliershire', 'Liechtenstein', '717', '245 Pagac Curve', '7977');
Insert Into address (city, country, number, street, zip_code) Values ('South Hazel', 'Taiwan', '121', '961 Mateo Key', '7924');
Insert Into address (city, country, number, street, zip_code) Values ('East Rosinashire', 'Benin', '28230', '24993 Ratke Gateway', '8005');
Insert Into address (city, country, number, street, zip_code) Values ('Serenitymouth', 'Cambodia', '98718', '429 Toy Meadow', '6636');
Insert Into address (city, country, number, street, zip_code) Values ('Niagara Falls', 'Heard Island and McDonald Islands', '401', '32583 Strosin Run', '5185');
Insert Into address (city, country, number, street, zip_code) Values ('North Jarrettport', 'Mongolia', '8515', '57250 Noemy Gardens', '5181');
Insert Into address (city, country, number, street, zip_code) Values ('North Madelynn', 'North Macedonia', '8652', '2248 Yost Hollow', '7558');
Insert Into address (city, country, number, street, zip_code) Values ('New Pascale', 'United States Minor Outlying Islands', '8236', '16643 Karen Shores', '5232');
Insert Into address (city, country, number, street, zip_code) Values ('Conroe', 'Costa Rica', '968', '44415 Connelly Manor', '6532');
Insert Into address (city, country, number, street, zip_code) Values ('McClureberg', 'Jordan', '84709', '2855 Daniel Ranch', '0738');

Insert Into users (email, password, status, user_type) Values ('guest@hotmail.com', 'guest', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Ivan_Bogisich30@yahoo.com', 'JR78k9H69z5YzW_', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Judy_Gusikowski39@yahoo.com', 'to63t1QNRJLKIzl', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Kent83@gmail.com', 'ZXAKlKmJyxn3c_G', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Richard29@hotmail.com', 'ww6kPYJrr0d1A8D', 1, 0);
Insert Into users (email, password, status, user_type) Values ('host@hotmail.com', 'host', 1, 1);
Insert Into users (email, password, status, user_type) Values ('Tammy.Nicolas88@gmail.com', 'fVzIvtOI4qM98UW', 1, 1);
Insert Into users (email, password, status, user_type) Values ('Daniel.Orn6@gmail.com', 'bEAVKuU_KlNeYdi', 1, 1);
Insert Into users (email, password, status, user_type) Values ('Clark69@gmail.com', 'k1EDPX5NHIJrQ68', 1, 1);
Insert Into users (email, password, status, user_type) Values ('admin@hotmail.com', 'admin', 1, 2);

Insert Into guest (name, surname, phone, address_id, user_id) Values ('Guy.Johnston25@yahoo.com', 'Pmzy2OkXbgfeU13', '+(394) 04 360 5786', 1, 1);
Insert Into guest (name, surname, phone, address_id, user_id) Values ('Elena23@gmail.com', 'CiA7YVOT8utTiY5', '+(019) 02 143 1435', 2, 2);
Insert Into guest (name, surname, phone, address_id, user_id) Values ('Carrie.Goldner@hotmail.com', '2mgBQJn76ymkYKI', '+(053) 87 788 0503', 3, 3);
Insert Into guest (name, surname, phone, address_id, user_id) Values ('Marlene.Kutch97@yahoo.com', 'QyYL9tRO9vAd8sH', '+(512) 23 527 0015', 4, 4);
Insert Into guest (name, surname, phone, address_id, user_id) Values ('Darrin.Effertz@hotmail.com', '9Fo5m08rpoKVUSo', '+(444) 85 502 8396', 5, 5);

Insert Into host (name, surname, phone, address_id, user_id) Values ('Teri_DuBuque@hotmail.com', 'TcDZcVocoLDkRrO', '+(258) 44 184 2315', 6, 6);
Insert Into host (name, surname, phone, address_id, user_id) Values ('Chelsea.Shields@yahoo.com', 'lgJ5y55G9b6cmLR', '+(511) 23 572 8526', 7, 7);
Insert Into host (name, surname, phone, address_id, user_id) Values ('Dixie.Willms@hotmail.com', 'LtEQs49qrdjiMPY', '+(144) 61 233 4983', 8, 8);
Insert Into host (name, surname, phone, address_id, user_id) Values ('Sandy57@yahoo.com', 'G2ldvVClsi4bD7b', '+(433) 77 678 9250', 9, 9);

insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Reilly-Volkman', 1, 'Burn of first degree of toe(s) (nail)', 'Revision of Autologous Tissue Substitute in Right Ear, Percutaneous Approach', 1, 'STUDIO', 'ACTIVE', 1, 3, 10, false);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Fisher and Sons', 1, 'Melanocytic nevi of unspecified lower limb, including hip', 'Removal of Spacer from Right Carpal Joint, Open Approach', 2, 'APARTMENT', 'ACTIVE', 1, 3, 20, false);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Pfeffer-Schmeler', 1, 'Corrosion of unsp degree of unspecified forearm, init encntr', 'Excision of Left Lower Lobe Bronchus, Percutaneous Endoscopic Approach, Diagnostic', 3, 'ROOM', 'ACTIVE', 1, 3, 30, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('McCullough-Zieme', 1, 'Laceration with foreign body of forearm', 'Drainage of Descending Colon, Via Natural or Artificial Opening Endoscopic, Diagnostic', 4, 'ROOM', 'ACTIVE', 2, 5, 14, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Lehner Group', 1, 'Milt op w indirect blast effect of nuclear weapon, civ, init', 'Introduction of Anti-inflammatory into Cranial Nerves, Percutaneous Approach', 5, 'STUDIO', 'ACTIVE', 2, 5, 15, false);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Hand and Sons', 2, 'Benign lipomatous neoplasm of intra-abdominal organs', 'Replacement of Left Choroid with Autologous Tissue Substitute, Percutaneous Approach', 6, 'ROOM', 'ACTIVE', 0, 6, 16, false);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Predovic-Orn', 2, 'Sarcoidosis, unspecified', 'Extirpation of Matter from Portal Vein, Open Approach', 7, 'ROOM', 'ACTIVE', 0, 6, 17, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Lehner, Schiller and Dickinson', 2, 'Dislocation of tarsal joint of unspecified foot', 'Fragmentation in Accessory Pancreatic Duct, Via Natural or Artificial Opening Endoscopic', 8, 'STUDIO', 'ACTIVE', 0, 8, 18, false);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Blanda, Herzog and Rohan', 3, 'Displ oblique fx shaft of l ulna, 7thR', 'Introduction of Radioactive Substance into Central Vein, Percutaneous Approach', 9, 'ROOM', 'ACTIVE', 1, 9, 19, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation) values ('Gleichner, Toy and Stracke', 3, 'Poisoning by antiparkns drug/centr muscle-tone depr, undet', 'Control Bleeding in Upper Back, Percutaneous Endoscopic Approach', 10, 'STUDIO', 'ACTIVE', 7, 10, 10, false);




