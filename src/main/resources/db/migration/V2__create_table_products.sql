CREATE  TABLE products (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY ,
    name VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    description TEXT,
    category VARCHAR(255),
    price FLOAT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);