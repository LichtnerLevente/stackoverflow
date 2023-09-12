
CREATE TABLE IF NOT EXISTS questions (
                                         question_id SERIAL PRIMARY KEY,--Serial instead of autoincrement
                                         username TEXT NOT NULL,
                                         question_text TEXT NOT NULL,
                                         question_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS answers (
                                       answer_id SERIAL PRIMARY KEY, --Serial instead of autoincrement
                                       question_id INTEGER,
                                       username TEXT NOT NULL,
                                       answer_text TEXT NOT NULL,
                                       answer_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

INSERT INTO questions (username, question_text) VALUES ('user1', 'hello?');
INSERT INTO answers (question_id, username, answer_text) VALUES (1, 'user2', 'szia');
