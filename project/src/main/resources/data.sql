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

Insert Into users (email, password, status, user_type) Values ('guest@hotmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Ivan_Bogisich30@yahoo.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 0);
Insert Into users (email, password, status, user_type) Values ('test@gmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Kent83@gmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 0);
Insert Into users (email, password, status, user_type) Values ('Richard29@hotmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 0);
Insert Into users (email, password, status, user_type) Values ('host@hotmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 1);
Insert Into users (email, password, status, user_type) Values ('Tammy.Nicolas88@gmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 1);
Insert Into users (email, password, status, user_type) Values ('testHost@gmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 1);
Insert Into users (email, password, status, user_type) Values ('Clark69@gmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 1);
Insert Into users (email, password, status, user_type) Values ('admin@hotmail.com', '$2a$10$plkV8LzaMiyd9qDGAKdRf.6XqCbYw3lJB7561Z.17RJ0Jp4deP.T6', 1, 2);

Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (1, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (2, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (3, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (4, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (5, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (6, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (7, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (8, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (9, true, true, true);
Insert Into settings (user_id, reservation_notification, review_notification, accommodation_review_notification) Values (10, true, true, true);

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

-- valid -> id = 1
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-01-10', '2025-01-20', 10, 500.0, 2, 1, 1, 'PENDING', false);
-- automatic reservation -> id = 2
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-01-10', '2025-01-20', 10, 500.0, 2, 1, 3, 'ACCEPTED', false);
-- not valid status -> id = 3, 4, 5
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-05-10', '2025-05-20', 10, 500.0, 2, 1, 1, 'ACCEPTED', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-01-10', '2025-01-20', 10, 500.0, 2, 1, 1, 'CANCELLED', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-01-10', '2025-01-20', 10, 500.0, 2, 1, 1, 'REJECTED', false);
-- empty calendar -> id = 6
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-08-13', '2025-08-14', 1, 30.0, 3, 3, 2, 'PENDING', false);
-- overlapping -> id = 7, 8, 9, 10, 11
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-10', '2025-02-20', 10, 500.0, 2, 1, 1, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-05', '2025-02-15', 10, 500.0, 2, 1, 1, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-11', '2025-02-16', 8, 400.0, 2, 1, 1, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-15', '2025-02-25', 10, 500.0, 2, 1, 1, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-05', '2025-02-25', 20, 1000.0, 2, 1, 1, 'PENDING', false);
-- status not PENDING -> id = 12
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-07-22', '2025-07-26', 4, 120.0, 2, 1, 1, 'CANCELLED', false);
-- dates not available -> id = 13
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2026-12-03', '2026-12-09', 6, 240.0, 2, 2, 1, 'PENDING', false);
-- others
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-08-12', '2025-08-17', 5, 100.0, 4, 4, 3, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-07-22', '2025-07-26', 4, 120.0, 2, 3, 1, 'ACCEPTED', false);
-- selenium accept reservation
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-10', '2025-02-20', 10, 500.0, 2, 1, 10, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-05', '2025-02-15', 10, 500.0, 2, 1, 10, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-11', '2025-02-16', 8, 400.0, 2, 1, 10, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-15', '2025-02-25', 10, 500.0, 2, 1, 10, 'PENDING', false);
insert into reservation (start_date, end_date, duration, price, guests_no, guest_id, accommodation_id, status, passed) values ('2025-02-05', '2025-02-25', 20, 1000.0, 2, 1, 10, 'PENDING', false);

insert into notification (user_id, title, text, type, status) values (1, 'Title', 'Exc of accessory spleen','RESERVATION', 'NEW');
insert into notification (user_id, title, text, type, status) values (1, 'Title', 'Remove penetrat FB eye','REVIEW', 'READ');
insert into notification (user_id, title, text, type, status) values (1, 'Title', 'Other suture of tendon','RESERVATION', 'READ');
insert into notification (user_id, title, text, type, status) values (2, 'Title', 'Remove penetrat cerv FB','REVIEW', 'NEW');
insert into notification (user_id, title, text, type, status) values (2, 'Title', 'Pacemaker impedance chck','RESERVATION', 'NEW');
insert into notification (user_id, title, text, type, status) values (3, 'Title', 'Open reduc-dislocat NEC','ACCOMMODATION_REVIEW', 'READ');
insert into notification (user_id, title, text, type, status) values (4, 'Title', 'Arth/pros rem wo rep NOS','ACCOMMODATION_REVIEW', 'READ');
insert into notification (user_id, title, text, type, status) values (5, 'Title', 'Failed forceps','ACCOMMODATION_REVIEW', 'NEW');
insert into notification (user_id, title, text, type, status) values (5, 'Title', 'Disarticulation of wrist','RESERVATION', 'NEW');
insert into notification (user_id, title, text, type, status) values (6, 'Title', 'Ligate thoracic duct','REVIEW', 'NEW');

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

insert into user_report (reporter_id, reportee_id, reason, status) values (1, 1, 'Sutur capsul/ligamen arm', 'ACTIVE');
insert into user_report (reporter_id, reportee_id, reason, status) values (1, 2, 'Mouth biopsy NOS', 'ACTIVE');
insert into user_report (reporter_id, reportee_id, reason, status) values (2, 1, 'Tot abd colectmy NEC/NOS', 'ACTIVE');
insert into user_report (reporter_id, reportee_id, reason, status) values (3, 4, 'Simple suture of dura', 'ACTIVE');
insert into user_report (reporter_id, reportee_id, reason, status) values (2, 5, 'Intrcoronry thromb infus', 'ACTIVE');

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


INSERT INTO day (date, price, availability)
VALUES
    ('2024-01-22', 100.0, 'AVAILABLE'),
    ('2024-01-23', 100.0, 'AVAILABLE'),
    ('2024-01-24', 100.0, 'AVAILABLE'),
    ('2024-01-25', 150.0, 'AVAILABLE'),
    ('2024-01-26', 150.0, 'AVAILABLE');

INSERT INTO accommodation_calendar (accommodation_id, calendar_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);



