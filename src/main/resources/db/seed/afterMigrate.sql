INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$9ZU5tzTtOfNyyD2zNDEQMeQqBwuiSCLIUaYyGIBn4oEw27Neq35VS', 'ADMIN')
ON CONFLICT (email) DO NOTHING;