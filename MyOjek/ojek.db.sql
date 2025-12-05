BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS passenger (
    passenger_id INTEGER PRIMARY KEY AUTOINCREMENT,
    no_hp TEXT,
    email TEXT,
    nama TEXT,
    password TEXT
);
CREATE TABLE IF NOT EXISTS ride_order (
                order_id INTEGER PRIMARY KEY AUTOINCREMENT,
                tanggal TEXT,
                alamat_jemput TEXT,
                alamat_antar TEXT,
                passenger_id INTEGER,
                driver_id INTEGER,
                biaya REAL,
                jarak_km REAL,
                accepted INTEGER DEFAULT 0,
                finished INTEGER DEFAULT 0,
                FOREIGN KEY(passenger_id) REFERENCES passenger(passenger_id),
            );
INSERT INTO "passenger" VALUES (1,'67','67','67','67');
INSERT INTO "passenger" VALUES (2,'a','a','a','a');
INSERT INTO "passenger" VALUES (3,'1','1','1','1');
INSERT INTO "passenger" VALUES (4,'1234','qwer','rt','1234');
INSERT INTO "ride_order" VALUES (1,'2025-11-27','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',1,1,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (2,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (3,'2025-12-03','a','b',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (4,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (5,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (6,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (7,'2025-12-03','Jakarta','Malang',0,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (8,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',0,0,52000.0,10.0,1,1);
INSERT INTO "ride_order" VALUES (9,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',1,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (10,'2025-12-03','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',2,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (11,'2025-12-04','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',4,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (12,'2025-12-04','suhat','ub',4,0,12000.0,2.0,1,1);
INSERT INTO "ride_order" VALUES (13,'2025-12-04','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',4,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (14,'2025-12-05','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',4,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (15,'2025-12-05','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',4,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (16,'2025-12-05','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (17,'2025-12-05','a','b',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (18,'2025-12-05','a','b',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (19,'2025-12-05','a','b',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (20,'2025-12-05','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (21,'2025-12-05','Gerbang Utama, Fakultas Ilmu Komputer, M...','Kost Putra, Jalan Bunga Mawar, Kota Malang',3,0,19500.0,3.5,1,1);
INSERT INTO "ride_order" VALUES (22,'2025-12-05','F','M',3,0,19500.0,3.5,1,1);
COMMIT;
