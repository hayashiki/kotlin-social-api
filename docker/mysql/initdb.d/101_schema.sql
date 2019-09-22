/* SQLEditor (MySQL (2))*/


CREATE TABLE address
(
id CHAR,
building_number CHAR,
latitude CHAR,
longitude CHAR,
postal_code CHAR,
street_name CHAR,
city_id CHAR UNIQUE
);

CREATE TABLE authority
(
id CHAR,
name CHAR
);

CREATE TABLE city
(
id CHAR,
name CHAR
);

CREATE TABLE city_alias
(
id CHAR,
name CHAR,
city_id CHAR
);

CREATE TABLE spot
(
id CHAR,
capacity CHAR,
number CHAR,
restaurant_id CHAR UNIQUE ,
min_people_number CHAR
);

CREATE TABLE restaurant
(
id CHAR,
name CHAR,
url_name CHAR,
address_id CHAR,
rate CHAR,
service_rate CHAR,
food_rate CHAR,
price_quality_rate CHAR,
avg_reservation_time CHAR,
is_active CHAR,
menu_id CHAR,
settings_id CHAR,
owner_id CHAR,
type CHAR,
phone_number CHAR,
email CHAR,
description CHAR
);

CREATE TABLE user_authority
(
id CHAR,
username CHAR,
authority CHAR
);

ALTER TABLE city ADD FOREIGN KEY id_idxfk (id) REFERENCES address (city_id);

ALTER TABLE restaurant ADD FOREIGN KEY id_idxfk_1 (id) REFERENCES spot (restaurant_id);
