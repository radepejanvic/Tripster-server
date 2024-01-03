insert into address (city, country, number, street, zip_code) values ('Novi Sad', 'Serbia', '23', 'Svetozara Miletića', '21000');
insert into address (city, country, number, street, zip_code) values ('Novi Sad', 'Serbia', '3', 'Bulevar Mihajla Pupina', '21000');
insert into address (city, country, number, street, zip_code) values ('Belgrade', 'Serbia', '9', 'Dobračina', '105801');
insert into address (city, country, number, street, zip_code) values ('Belgrade', 'Serbia', '20', 'Balkanska', '11000');
insert into address (city, country, number, street, zip_code) values ('Sevelen', 'Switzerland', '20', 'Bahnhofstrasse', '9475');
insert into address (city, country, number, street, zip_code) values ('Ruisui Township', 'Taiwan', '43', 'Section 1, Zhongzheng S Rd', '978');
insert into address (city, country, number, street, zip_code) values ('Berlin', 'Germany', '54', 'Leipziger Str.', '10117');
insert into address (city, country, number, street, zip_code) values ('London', 'United Kingdom', '33', 'Albemarle St', 'W1S 4BP');
insert into address (city, country, number, street, zip_code) values ('Valencia', 'Spain', '10', 'C/ de Correus, Ciutat Vella', '46002');
insert into address (city, country, number, street, zip_code) values ('Moncada', 'Spain', '1', 'Av. de Ferrran el Catòlic, Mercat de', '46113');

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

insert into guest (name, surname, phone, address_id, user_id) values ('Diego', 'Wetherell', '574-692-4946', 1, 1);
insert into guest (name, surname, phone, address_id, user_id) values ('Abagail', 'Mumbeson', '326-329-0219', 2, 2);
insert into guest (name, surname, phone, address_id, user_id) values ('Kristofer', 'Selwood', '798-594-6840', 3, 3);
insert into guest (name, surname, phone, address_id, user_id) values ('Tabitha', 'Gabriely', '270-128-5602', 4, 4);
insert into guest (name, surname, phone, address_id, user_id) values ('Jane', 'Glassford', '980-847-8405', 5, 5);

insert into host (name, surname, phone, address_id, user_id) values ('Wendall', 'Toppin', '344-293-3763', 6, 6);
insert into host (name, surname, phone, address_id, user_id) values ('Olenka', 'Applewhaite', '349-989-0362', 7, 7);
insert into host (name, surname, phone, address_id, user_id) values ('Beverlee', 'Potkins', '848-215-6921', 8, 8);
insert into host (name, surname, phone, address_id, user_id) values ('Bobbee', 'January', '104-170-4066', 9, 9);

insert into amenity (type) values ('WIFI');
insert into amenity (type) values ('KITCHEN');
insert into amenity (type) values ('PARKING');
insert into amenity (type) values ('AC');
insert into amenity (type) values ('TV');
insert into amenity (type) values ('SAFE');
insert into amenity (type) values ('PET_FRIENDLY');
insert into amenity (type) values ('FREE_CANCELLATION');
insert into amenity (type) values ('POOL');
insert into amenity (type) values ('BARBECUE');
insert into amenity (type) values ('WASHING_MACHINE');
insert into amenity (type) values ('SAUNA');
insert into amenity (type) values ('JACUZZI');

insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Reilly-Volkman', 1, 'Burn of first degree of toe(s) (nail)', 'Revision of Autologous Tissue Substitute in Right Ear, Percutaneous Approach', 1, 'STUDIO', 'ACTIVE', 1, 3, 10, false, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Fisher and Sons', 1, 'Melanocytic nevi of unspecified lower limb, including hip', 'Removal of Spacer from Right Carpal Joint, Open Approach', 2, 'APARTMENT', 'ACTIVE', 1, 3, 20, false, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Pfeffer-Schmeler', 1, 'Corrosion of unsp degree of unspecified forearm, init encntr', 'Excision of Left Lower Lobe Bronchus, Percutaneous Endoscopic Approach, Diagnostic', 3, 'ROOM', 'ACTIVE', 1, 3, 30, true, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('McCullough-Zieme', 1, 'Laceration with foreign body of forearm', 'Drainage of Descending Colon, Via Natural or Artificial Opening Endoscopic, Diagnostic', 4, 'ROOM', 'ACTIVE', 2, 5, 14, true, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Lehner Group', 1, 'Milt op w indirect blast effect of nuclear weapon, civ, init', 'Introduction of Anti-inflammatory into Cranial Nerves, Percutaneous Approach', 5, 'STUDIO', 'ACTIVE', 2, 5, 15, false, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Hand and Sons', 2, 'Benign lipomatous neoplasm of intra-abdominal organs', 'Replacement of Left Choroid with Autologous Tissue Substitute, Percutaneous Approach', 6, 'ROOM', 'ACTIVE', 0, 6, 16, false, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Predovic-Orn', 2, 'Sarcoidosis, unspecified', 'Extirpation of Matter from Portal Vein, Open Approach', 7, 'ROOM', 'ACTIVE', 0, 6, 17, true, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Lehner, Schiller and Dickinson', 2, 'Dislocation of tarsal joint of unspecified foot', 'Fragmentation in Accessory Pancreatic Duct, Via Natural or Artificial Opening Endoscopic', 8, 'STUDIO', 'ACTIVE', 0, 8, 18, false, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Blanda, Herzog and Rohan', 3, 'Displ oblique fx shaft of l ulna, 7thR', 'Introduction of Radioactive Substance into Central Vein, Percutaneous Approach', 9, 'ROOM', 'ACTIVE', 1, 9, 19, true, true);
insert into accommodation (name, owner_id, short_description, description, address_id, type, status, min_cap, max_cap, cancel_duration, automatic_reservation, price_per_night) values ('Gleichner, Toy and Stracke', 3, 'Poisoning by antiparkns drug/centr muscle-tone depr, undet', 'Control Bleeding in Upper Back, Percutaneous Endoscopic Approach', 10, 'STUDIO', 'ACTIVE', 7, 10, 10, false, true);

insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, deleted) values ('2023-07-22', '2023-07-26', 4, 120.0, 2, 1, 1, 'CANCELLED', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, deleted) values ('2022-12-03', '2022-12-09', 6, 240.0, 2, 2, 1, 'CANCELLED', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, deleted) values ('2023-08-13', '2023-08-14', 1, 30.0, 3, 3, 2, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, deleted) values ('2023-08-12', '2023-08-17', 5, 100.0, 4, 4, 3, 'PENDING', false);

insert into notification (user_id, text, status) values (1, 'Exc of accessory spleen', 'SENT');
insert into notification (user_id, text, status) values (1, 'Remove penetrat FB eye', 'READ');
insert into notification (user_id, text, status) values (1, 'Other suture of tendon', 'READ');
insert into notification (user_id, text, status) values (2, 'Remove penetrat cerv FB', 'SENT');
insert into notification (user_id, text, status) values (2, 'Pacemaker impedance chck', 'SENT');
insert into notification (user_id, text, status) values (3, 'Open reduc-dislocat NEC', 'READ');
insert into notification (user_id, text, status) values (4, 'Arth/pros rem wo rep NOS', 'READ');
insert into notification (user_id, text, status) values (5, 'Failed forceps', 'NEW');
insert into notification (user_id, text, status) values (5, 'Disarticulation of wrist', 'SENT');
insert into notification (user_id, text, status) values (6, 'Ligate thoracic duct', 'SENT');

insert into user_review (reviewer_id, title, reviewed_user_id, comment, rate, status) values (1, 'This is my title', 2, 'Incise cerebral meninges', 1, 'REPORTED');
insert into user_review (reviewer_id, title, reviewed_user_id, comment, rate, status) values (2, 'This is my title', 1, 'Extracorpor hepat Assis', 2, 'REPORTED');
insert into user_review (reviewer_id, title, reviewed_user_id, comment, rate, status) values (3, 'This is my title', 2, 'Total esophagectomy', 3, 'REPORTED');
insert into user_review (reviewer_id, title, reviewed_user_id, comment, rate, status) values (4, 'This is my title', 3, 'Manual rupt joint adhes', 4, 'ACTIVE');
insert into user_review (reviewer_id, title, reviewed_user_id, comment, rate, status) values (5, 'This is my title', 4, 'Periph ganglionect NEC', 5, 'REPORTED');

insert into accommodation_review (reviewer_id, title, accommodation_id, comment, rate, status) values (1, 'This is my title', 1, 'Wide excision of lip les', 1, 'DELETED');
insert into accommodation_review (reviewer_id, title, accommodation_id, comment, rate, status) values (2, 'This is my title', 1, 'Breast dx procedure NEC', 2, 'DELETED');
insert into accommodation_review (reviewer_id, title, accommodation_id, comment, rate, status) values (3, 'This is my title', 1, 'Tonometry', 3, 'ACTIVE');
insert into accommodation_review (reviewer_id, title, accommodation_id, comment, rate, status) values (4, 'This is my title', 2, 'Periph nerve div NEC', 4, 'ACTIVE');
insert into accommodation_review (reviewer_id, title, accommodation_id, comment, rate, status) values (5, 'This is my title', 3, 'Thymectomy NOS', 5, 'ACTIVE');

insert into user_report (reporter_id, reportee_id, reason) values (1, 1, 'Sutur capsul/ligamen arm');
insert into user_report (reporter_id, reportee_id, reason) values (1, 2, 'Mouth biopsy NOS');
insert into user_report (reporter_id, reportee_id, reason) values (2, 1, 'Tot abd colectmy NEC/NOS');
insert into user_report (reporter_id, reportee_id, reason) values (3, 4, 'Simple suture of dura');
insert into user_report (reporter_id, reportee_id, reason) values (2, 5, 'Intrcoronry thromb infus');

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 1);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 1);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 1);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 1);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 1);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 2);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 2);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 2);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 2);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 2);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 3);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 3);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 3);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 3);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 3);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 4);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 4);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 4);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 4);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 4);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 5);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 5);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 5);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 5);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 5);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 6);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 6);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 6);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 6);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 6);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 7);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 7);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 7);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 7);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 7);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 8);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 8);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 8);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 8);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 8);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 9);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 9);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 9);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 9);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 9);

insert into photo (name, type, accommodation_id) values ('primary', 'jpg', 10);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 10);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 10);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 10);
insert into photo (name, type, accommodation_id) values ('secondary', 'jpg', 10);


