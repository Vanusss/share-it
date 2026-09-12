CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE item_requests (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               description TEXT NOT NULL,
                               requestor_id UUID NOT NULL,
                               created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_item_requests_requestor
                                   FOREIGN KEY (requestor_id)
                                       REFERENCES users (id)
);

CREATE TABLE items (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       available BOOLEAN NOT NULL,
                       owner_id UUID NOT NULL,
                       request_id UUID,

                       CONSTRAINT fk_items_owner
                           FOREIGN KEY (owner_id)
                               REFERENCES users (id),

                       CONSTRAINT fk_items_request
                           FOREIGN KEY (request_id)
                               REFERENCES item_requests (id)
);

CREATE TABLE bookings (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          start_time TIMESTAMP NOT NULL,
                          end_time TIMESTAMP NOT NULL,
                          item_id UUID NOT NULL,
                          booker_id UUID NOT NULL,
                          status VARCHAR(20) NOT NULL,

                          CONSTRAINT fk_bookings_item
                              FOREIGN KEY (item_id)
                                  REFERENCES items (id),

                          CONSTRAINT fk_bookings_booker
                              FOREIGN KEY (booker_id)
                                  REFERENCES users (id),

                          CONSTRAINT chk_bookings_time_range
                              CHECK (end_time > start_time),

                          CONSTRAINT chk_bookings_status
                              CHECK (status IN ('WAITING', 'APPROVED', 'REJECTED', 'CANCELED'))
);