INSERT INTO spring.authorities VALUES (1, 'john', 'write') ON CONFLICT DO NOTHING;
INSERT INTO spring.users VALUES (1, 'john', '{MD5}{nt8XCm32M9dzeaetdtFACO0jiUMhqeayK7I4NJ+Exmw=}9469cad74c2e2cf18700b3d751d314c8', '1') ON CONFLICT DO NOTHING;
