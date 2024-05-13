CREATE TABLE taxis(
  id INT PRIMARY KEY,
  plate VARCHAR(9) NOT NULL
);

CREATE TABLE trajectories (
  id INT GENERATED ALWAYS AS IDENTITY,
  taxi_id INT NOT NULL,
  date TIMESTAMP NOT NULL,
  latitude DOUBLE PRECISION NOT NULL,
  longitude DOUBLE PRECISION NOT NULL,
  CONSTRAINT fk_taxi FOREIGN KEY(taxi_id) REFERENCES taxis(id)
);


-- CREATE TABLE "taxis" (
--   "id" INT NOT NULL,
--   "plate" VARCHAR NOT NULL,
--   CONSTRAINT "PK_id_taxi" PRIMARY KEY ("id")
-- );
--
-- CREATE TABLE "trajectories" (
--    "id" INT SERIAL,
--    "taxi_id" INT NOT NULL,
--    "date" DATE NOT NULL,
--    "latitude" DECIMAL NOT NULL,
--    "longitude" DECIMAL NOT NULL,
--    CONSTRAINT "PK_trajectories" PRIMARY KEY ("id"),
--    CONSTRAINT "FK_taxi_trajectories" FOREIGN KEY ("taxi_id") REFERENCES "taxis" ("id")
-- );