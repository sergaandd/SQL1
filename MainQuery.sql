SELECT sum(qty) as sum,type_name,store_name FROM
 stores s, goods g, types t, store_lot l
 WHERE l.goods_id=g.goods_id and l.store_id=s.store_id and g.type_id=t.type_id
 and t.type_name='****'
 GROUP BY t.type_id,s.store_id
 ORDER BY sum desc limit 1
