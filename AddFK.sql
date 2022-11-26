ALTER TABLE store_lot ADD CONSTRAINT goodsKey FOREIGN KEY (goods_id) REFERENCES goods (goods_id);
ALTER TABLE store_lot ADD CONSTRAINT storeKey FOREIGN KEY (store_id) REFERENCES stores (store_id);
ALTER TABLE goods ADD CONSTRAINT goodsKey FOREIGN KEY (type_id) REFERENCES types (type_id);