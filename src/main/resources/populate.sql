
CREATE TABLE IF NOT EXISTS questions (
                                         question_id SERIAL PRIMARY KEY,--Serial instead of autoincrement
                                         user_id INTEGER,
                                         question_title TEXT NOT NULL,
                                         question_description TEXT,
                                         question_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS answers (
                                       answer_id SERIAL PRIMARY KEY, --Serial instead of autoincrement
                                       question_id INTEGER,
                                       user_id INTEGER,
                                       answer_text TEXT NOT NULL,
                                       answer_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

INSERT INTO questions (user_id, question_title, question_description) VALUES (0, 'hello?', 'HELLO WORLD');
INSERT INTO answers (question_id, user_id, answer_text) VALUES (1, 0, 'szia');
