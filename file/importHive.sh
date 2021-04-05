echo 'orders'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/tradecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select order_id,parent_order_id,order_type,status,buyer_status,seller_type,order_platform,order_source,total_money,freight_money,discount_money,cut_money,other_fee,round_down,actually_payment_money,buyer_id,buyer_name,buyer_shop_id,buyer_shop_name,seller_id,seller_name,shop_id,shop_name,\`option\`, paid, payment_source, payment_time, refund, exchange, invoice, buyer_memo, seller_memo, is_change_price, change_price_user, change_price_time, settle_flag, evaluation, create_time, modify_time, create_user, modify_user, deposit, retainage, retainage_order_id, presell_id, presell_pay_type, order_credit, yn, manage_user_id, manage_username, buyer_manage_user_id, buyer_manage_username, purchase_date, warehouse_code, warehouse_name, reason, audit_time, audit_user_id, audit_username, remark, seller_org_code, seller_org_parent_code, buyer_org_code, buyer_org_parent_code, delivery_type, print_price, consignment, store_complete, balance_amount, balance_flag, issue_flag, self_pick_flag, expect_receive_time, delivery_remark from orders where (date_format(create_time,'%Y%m%d') = ${dt} or date_format(modify_time,'%Y%m%d') = ${dt}) and shop_id is not null and \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by shop_id \
--hcatalog-database ods \
--hcatalog-table ods_orders \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'
echo 'orders_detail'+${dt}
sqoop import \
--connect jdbc:mysql://10.2.0.92:3306/tradecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--table orders_detail \
--where "(date_format(create_time,'%Y%m%d') = ${dt} or date_format(modify_time,'%Y%m%d') = ${dt}) and shop_id is not null" \
--driver com.mysql.jdbc.Driver \
--hcatalog-database ods \
--hcatalog-table ods_orders_detail \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'

echo 'orders_receive_info'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/tradecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,order_id,consignee_name,consignee_mobile,consignee_phone,consignee_mail,province_code,city_code,country_code,town_code,province_name,city_name,country_name,town_name,detail_address,create_time,modify_time,create_user,modify_user,yn from orders_receive_info where (date_format(create_time,'%Y%m%d') = ${dt} or date_format(modify_time,'%Y%m%d') = ${dt}) and \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by order_id \
--hcatalog-database ods \
--hcatalog-table ods_orders_receive \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'



echo 'refund_detail'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/tradecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,refund_id,order_id,order_detail_id,item_id,item_name,sku_id,sku_code,sku_pic_url,sku_sale_attr_str,num,transaction_money,refund_money,refund_num,refund_price,notes,create_time,modify_time,create_user,modify_user,yn,buyer_item_id,buyer_sku_id,buyer_sku_code,inbound_num,outbound_num,divided_balance from refund_detail where (date_format(create_time,'%Y%m%d') = ${dt} or date_format(modify_time,'%Y%m%d') = ${dt}) and \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by refund_id \
--hcatalog-database ods \
--hcatalog-table ods_refund_detail \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'


echo 'refund_apply'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/tradecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,refund_no,type,refund_type,order_id,order_status,refund_status,refund_reason,question_description,receiving,audit_id,audit_name,audit_time,audit_notes,refund_total_money,apply_refund_money,province_code,province_name,city_code,city_name,country_code,country_name,town_code,town_name,detail_address,refund_address,refund_receiver,refund_mobile,refund_directions,create_time,modify_time,create_user,modify_user,yn,manage_user_id,manage_username,seller_org_code,seller_org_parent_code,buyer_manage_user_id,buyer_manage_username,buyer_org_code,buyer_org_parent_code,create_flag,buyer_id,buyer_name,buyer_shop_id,buyer_shop_name,seller_id,seller_name,shop_id,shop_name,inbound_num,outbound_num,all_item_num,consignment,store_complete,balance_amount,is_create_bound_bill from refund_apply where (date_format(create_time,'%Y%m%d') = ${dt} or date_format(modify_time,'%Y%m%d') = ${dt}) and \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by shop_id \
--hcatalog-database ods \
--hcatalog-table ods_refund_apply \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'

echo 'item'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/goodscenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select item_id,brand_id,cid,seller_id,shop_id,shop_cid,shop_freight_template_id,attributes,attr_sale,status,type,item_name,shelve_time,off_shelve_time,task_shelve_time,task_off_shelve_time,origin,weight,volume,length,width,height,ad,keyword,remark,unit_code,unit_name,quotation_way,create_time,create_user,update_time,update_user,yn,sign,status_change_reason,platform,give_away,pay_type,sale_channel,points,upc,goods_code,attr_template_id,\`describe\`,ad_url,source_item_id,master_item_id,rebate_flag,shop_sales_terr_temp_id from item where (date_format(create_time,'%Y%m%d') = ${dt} or date_format(update_time,'%Y%m%d') = ${dt}) and \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by shop_id \
--hcatalog-database ods \
--hcatalog-table ods_item \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'


echo 'item_sku'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/goodscenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select sku_id,item_id,seller_id,shop_id,attributes,sku_code,status,create_time,update_time,create_user,update_user,yn,source_item_id,source_sku_id,master_item_id,master_sku_id from item_sku where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by shop_id \
--hcatalog-database ods \
--hcatalog-table ods_item_sku \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'

echo 'item_category'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/goodscenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select cid,parent_cid,name,level,leaf,sort_num,status,channel,sign,create_time,update_time,create_user,update_user,yn,picture_url,platform,seller_code,activity,rebate_flag from item_category where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by cid \
--hcatalog-database ods \
--hcatalog-table ods_item_category \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'



echo 'shop_info'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/storecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select shop_id,seller_id,shop_name,platform,status,\`option\`,operation_status,\`domain\`,logo,\`type\`,intro,keyword,description,open_time,create_time,create_user,update_time,update_user,yn,month_num,payment_status,autotrophy_type,company_name,shop_type,customer_phone,shop_grade,fax,postcode,contact,remark,province_code,province_name,city_code,city_name,country_code,country_name,town_code,town_name,detail_address,tax_number,bank_name,bank_account,manage_user_id,account_book_no,account_owner,org_code,inventory_sync,plat_industry_rel_id,industry from shop_info where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by shop_id \
--hcatalog-database ods \
--hcatalog-table ods_shop_info \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'


echo 'shop_info'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/storecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,platform,platform_name,industry,industry_name,create_time,update_time,create_user,update_user,yn,description from shop_info where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by platform \
--hcatalog-database ods \
--hcatalog-table ods_shop_info \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'


echo 'usercenter.user'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/usercenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,platform,tenant_id,seller_id,parent_id,\`name\`,mobile,email,nickname,sex,birthday,hobbies,icon,\`type\`,flag,pay_password,status,create_time,modify_time,create_user,modify_user,failed_login_count,yn,login_time,login_num,pay_password_safe,seller_pay_password,logout_time,job_number,remark,is_sales_man,is_buyer_man,is_cut_man from user where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by platform \
--hcatalog-database ods \
--hcatalog-table ods_user \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'



echo 'basecenter.base_dictionary'+${dt}
sqoop import  \
--connect jdbc:mysql://10.2.0.92:3306/basecenter?serverTimezone=GMT%2B8 \
--username root \
--password 123456 \
--query "select id,code,\`name\`,\`type\`,remark,create_time,create_user,update_time,update_user,yn,delete_flag,content_type,url,shop_id from base_dictionary where  \$CONDITIONS" \
--driver com.mysql.jdbc.Driver \
--split-by yn \
--hcatalog-database ods \
--hcatalog-table ods_base_dictionary \
--hcatalog-partition-keys dt \
--hcatalog-partition-values ${dt} \
--hcatalog-storage-stanza 'stored as parquet tblproperties ("orc.compress"="SNAPPY")'




