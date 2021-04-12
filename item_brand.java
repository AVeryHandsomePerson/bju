// ORM class for table 'item_brand'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Apr 08 17:42:39 CST 2021
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class item_brand extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("brand_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_id = (Long)value;
      }
    });
    setters.put("brand_key", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_key = (String)value;
      }
    });
    setters.put("brand_logo_url", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_logo_url = (String)value;
      }
    });
    setters.put("brand_name", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_name = (String)value;
      }
    });
    setters.put("brand_name_cn", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_name_cn = (String)value;
      }
    });
    setters.put("brand_name_en", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.brand_name_en = (String)value;
      }
    });
    setters.put("create_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.create_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("create_user", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.create_user = (Long)value;
      }
    });
    setters.put("registered_number", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.registered_number = (String)value;
      }
    });
    setters.put("registrant", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.registrant = (String)value;
      }
    });
    setters.put("status", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.status = (Integer)value;
      }
    });
    setters.put("tm_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.tm_type = (String)value;
      }
    });
    setters.put("update_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.update_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("update_user", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.update_user = (Long)value;
      }
    });
    setters.put("yn", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        item_brand.this.yn = (Integer)value;
      }
    });
  }
  public item_brand() {
    init0();
  }
  private Long brand_id;
  public Long get_brand_id() {
    return brand_id;
  }
  public void set_brand_id(Long brand_id) {
    this.brand_id = brand_id;
  }
  public item_brand with_brand_id(Long brand_id) {
    this.brand_id = brand_id;
    return this;
  }
  private String brand_key;
  public String get_brand_key() {
    return brand_key;
  }
  public void set_brand_key(String brand_key) {
    this.brand_key = brand_key;
  }
  public item_brand with_brand_key(String brand_key) {
    this.brand_key = brand_key;
    return this;
  }
  private String brand_logo_url;
  public String get_brand_logo_url() {
    return brand_logo_url;
  }
  public void set_brand_logo_url(String brand_logo_url) {
    this.brand_logo_url = brand_logo_url;
  }
  public item_brand with_brand_logo_url(String brand_logo_url) {
    this.brand_logo_url = brand_logo_url;
    return this;
  }
  private String brand_name;
  public String get_brand_name() {
    return brand_name;
  }
  public void set_brand_name(String brand_name) {
    this.brand_name = brand_name;
  }
  public item_brand with_brand_name(String brand_name) {
    this.brand_name = brand_name;
    return this;
  }
  private String brand_name_cn;
  public String get_brand_name_cn() {
    return brand_name_cn;
  }
  public void set_brand_name_cn(String brand_name_cn) {
    this.brand_name_cn = brand_name_cn;
  }
  public item_brand with_brand_name_cn(String brand_name_cn) {
    this.brand_name_cn = brand_name_cn;
    return this;
  }
  private String brand_name_en;
  public String get_brand_name_en() {
    return brand_name_en;
  }
  public void set_brand_name_en(String brand_name_en) {
    this.brand_name_en = brand_name_en;
  }
  public item_brand with_brand_name_en(String brand_name_en) {
    this.brand_name_en = brand_name_en;
    return this;
  }
  private java.sql.Timestamp create_time;
  public java.sql.Timestamp get_create_time() {
    return create_time;
  }
  public void set_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
  public item_brand with_create_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
    return this;
  }
  private Long create_user;
  public Long get_create_user() {
    return create_user;
  }
  public void set_create_user(Long create_user) {
    this.create_user = create_user;
  }
  public item_brand with_create_user(Long create_user) {
    this.create_user = create_user;
    return this;
  }
  private String registered_number;
  public String get_registered_number() {
    return registered_number;
  }
  public void set_registered_number(String registered_number) {
    this.registered_number = registered_number;
  }
  public item_brand with_registered_number(String registered_number) {
    this.registered_number = registered_number;
    return this;
  }
  private String registrant;
  public String get_registrant() {
    return registrant;
  }
  public void set_registrant(String registrant) {
    this.registrant = registrant;
  }
  public item_brand with_registrant(String registrant) {
    this.registrant = registrant;
    return this;
  }
  private Integer status;
  public Integer get_status() {
    return status;
  }
  public void set_status(Integer status) {
    this.status = status;
  }
  public item_brand with_status(Integer status) {
    this.status = status;
    return this;
  }
  private String tm_type;
  public String get_tm_type() {
    return tm_type;
  }
  public void set_tm_type(String tm_type) {
    this.tm_type = tm_type;
  }
  public item_brand with_tm_type(String tm_type) {
    this.tm_type = tm_type;
    return this;
  }
  private java.sql.Timestamp update_time;
  public java.sql.Timestamp get_update_time() {
    return update_time;
  }
  public void set_update_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
  }
  public item_brand with_update_time(java.sql.Timestamp update_time) {
    this.update_time = update_time;
    return this;
  }
  private Long update_user;
  public Long get_update_user() {
    return update_user;
  }
  public void set_update_user(Long update_user) {
    this.update_user = update_user;
  }
  public item_brand with_update_user(Long update_user) {
    this.update_user = update_user;
    return this;
  }
  private Integer yn;
  public Integer get_yn() {
    return yn;
  }
  public void set_yn(Integer yn) {
    this.yn = yn;
  }
  public item_brand with_yn(Integer yn) {
    this.yn = yn;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof item_brand)) {
      return false;
    }
    item_brand that = (item_brand) o;
    boolean equal = true;
    equal = equal && (this.brand_id == null ? that.brand_id == null : this.brand_id.equals(that.brand_id));
    equal = equal && (this.brand_key == null ? that.brand_key == null : this.brand_key.equals(that.brand_key));
    equal = equal && (this.brand_logo_url == null ? that.brand_logo_url == null : this.brand_logo_url.equals(that.brand_logo_url));
    equal = equal && (this.brand_name == null ? that.brand_name == null : this.brand_name.equals(that.brand_name));
    equal = equal && (this.brand_name_cn == null ? that.brand_name_cn == null : this.brand_name_cn.equals(that.brand_name_cn));
    equal = equal && (this.brand_name_en == null ? that.brand_name_en == null : this.brand_name_en.equals(that.brand_name_en));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.create_user == null ? that.create_user == null : this.create_user.equals(that.create_user));
    equal = equal && (this.registered_number == null ? that.registered_number == null : this.registered_number.equals(that.registered_number));
    equal = equal && (this.registrant == null ? that.registrant == null : this.registrant.equals(that.registrant));
    equal = equal && (this.status == null ? that.status == null : this.status.equals(that.status));
    equal = equal && (this.tm_type == null ? that.tm_type == null : this.tm_type.equals(that.tm_type));
    equal = equal && (this.update_time == null ? that.update_time == null : this.update_time.equals(that.update_time));
    equal = equal && (this.update_user == null ? that.update_user == null : this.update_user.equals(that.update_user));
    equal = equal && (this.yn == null ? that.yn == null : this.yn.equals(that.yn));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof item_brand)) {
      return false;
    }
    item_brand that = (item_brand) o;
    boolean equal = true;
    equal = equal && (this.brand_id == null ? that.brand_id == null : this.brand_id.equals(that.brand_id));
    equal = equal && (this.brand_key == null ? that.brand_key == null : this.brand_key.equals(that.brand_key));
    equal = equal && (this.brand_logo_url == null ? that.brand_logo_url == null : this.brand_logo_url.equals(that.brand_logo_url));
    equal = equal && (this.brand_name == null ? that.brand_name == null : this.brand_name.equals(that.brand_name));
    equal = equal && (this.brand_name_cn == null ? that.brand_name_cn == null : this.brand_name_cn.equals(that.brand_name_cn));
    equal = equal && (this.brand_name_en == null ? that.brand_name_en == null : this.brand_name_en.equals(that.brand_name_en));
    equal = equal && (this.create_time == null ? that.create_time == null : this.create_time.equals(that.create_time));
    equal = equal && (this.create_user == null ? that.create_user == null : this.create_user.equals(that.create_user));
    equal = equal && (this.registered_number == null ? that.registered_number == null : this.registered_number.equals(that.registered_number));
    equal = equal && (this.registrant == null ? that.registrant == null : this.registrant.equals(that.registrant));
    equal = equal && (this.status == null ? that.status == null : this.status.equals(that.status));
    equal = equal && (this.tm_type == null ? that.tm_type == null : this.tm_type.equals(that.tm_type));
    equal = equal && (this.update_time == null ? that.update_time == null : this.update_time.equals(that.update_time));
    equal = equal && (this.update_user == null ? that.update_user == null : this.update_user.equals(that.update_user));
    equal = equal && (this.yn == null ? that.yn == null : this.yn.equals(that.yn));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.brand_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.brand_key = JdbcWritableBridge.readString(2, __dbResults);
    this.brand_logo_url = JdbcWritableBridge.readString(3, __dbResults);
    this.brand_name = JdbcWritableBridge.readString(4, __dbResults);
    this.brand_name_cn = JdbcWritableBridge.readString(5, __dbResults);
    this.brand_name_en = JdbcWritableBridge.readString(6, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(7, __dbResults);
    this.create_user = JdbcWritableBridge.readLong(8, __dbResults);
    this.registered_number = JdbcWritableBridge.readString(9, __dbResults);
    this.registrant = JdbcWritableBridge.readString(10, __dbResults);
    this.status = JdbcWritableBridge.readInteger(11, __dbResults);
    this.tm_type = JdbcWritableBridge.readString(12, __dbResults);
    this.update_time = JdbcWritableBridge.readTimestamp(13, __dbResults);
    this.update_user = JdbcWritableBridge.readLong(14, __dbResults);
    this.yn = JdbcWritableBridge.readInteger(15, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.brand_id = JdbcWritableBridge.readLong(1, __dbResults);
    this.brand_key = JdbcWritableBridge.readString(2, __dbResults);
    this.brand_logo_url = JdbcWritableBridge.readString(3, __dbResults);
    this.brand_name = JdbcWritableBridge.readString(4, __dbResults);
    this.brand_name_cn = JdbcWritableBridge.readString(5, __dbResults);
    this.brand_name_en = JdbcWritableBridge.readString(6, __dbResults);
    this.create_time = JdbcWritableBridge.readTimestamp(7, __dbResults);
    this.create_user = JdbcWritableBridge.readLong(8, __dbResults);
    this.registered_number = JdbcWritableBridge.readString(9, __dbResults);
    this.registrant = JdbcWritableBridge.readString(10, __dbResults);
    this.status = JdbcWritableBridge.readInteger(11, __dbResults);
    this.tm_type = JdbcWritableBridge.readString(12, __dbResults);
    this.update_time = JdbcWritableBridge.readTimestamp(13, __dbResults);
    this.update_user = JdbcWritableBridge.readLong(14, __dbResults);
    this.yn = JdbcWritableBridge.readInteger(15, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(brand_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(brand_key, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_logo_url, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name_cn, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name_en, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 7 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeLong(create_user, 8 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(registered_number, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(registrant, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(status, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(tm_type, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(update_time, 13 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeLong(update_user, 14 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(yn, 15 + __off, 4, __dbStmt);
    return 15;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(brand_id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(brand_key, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_logo_url, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name_cn, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(brand_name_en, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(create_time, 7 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeLong(create_user, 8 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(registered_number, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(registrant, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(status, 11 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(tm_type, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(update_time, 13 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeLong(update_user, 14 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeInteger(yn, 15 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.brand_id = null;
    } else {
    this.brand_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.brand_key = null;
    } else {
    this.brand_key = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.brand_logo_url = null;
    } else {
    this.brand_logo_url = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.brand_name = null;
    } else {
    this.brand_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.brand_name_cn = null;
    } else {
    this.brand_name_cn = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.brand_name_en = null;
    } else {
    this.brand_name_en = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.create_time = null;
    } else {
    this.create_time = new Timestamp(__dataIn.readLong());
    this.create_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.create_user = null;
    } else {
    this.create_user = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.registered_number = null;
    } else {
    this.registered_number = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.registrant = null;
    } else {
    this.registrant = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.status = null;
    } else {
    this.status = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.tm_type = null;
    } else {
    this.tm_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.update_time = null;
    } else {
    this.update_time = new Timestamp(__dataIn.readLong());
    this.update_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.update_user = null;
    } else {
    this.update_user = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.yn = null;
    } else {
    this.yn = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.brand_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.brand_id);
    }
    if (null == this.brand_key) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_key);
    }
    if (null == this.brand_logo_url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_logo_url);
    }
    if (null == this.brand_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name);
    }
    if (null == this.brand_name_cn) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name_cn);
    }
    if (null == this.brand_name_en) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name_en);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.create_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_user);
    }
    if (null == this.registered_number) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, registered_number);
    }
    if (null == this.registrant) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, registrant);
    }
    if (null == this.status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.status);
    }
    if (null == this.tm_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tm_type);
    }
    if (null == this.update_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_time.getTime());
    __dataOut.writeInt(this.update_time.getNanos());
    }
    if (null == this.update_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_user);
    }
    if (null == this.yn) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.yn);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.brand_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.brand_id);
    }
    if (null == this.brand_key) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_key);
    }
    if (null == this.brand_logo_url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_logo_url);
    }
    if (null == this.brand_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name);
    }
    if (null == this.brand_name_cn) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name_cn);
    }
    if (null == this.brand_name_en) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, brand_name_en);
    }
    if (null == this.create_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_time.getTime());
    __dataOut.writeInt(this.create_time.getNanos());
    }
    if (null == this.create_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.create_user);
    }
    if (null == this.registered_number) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, registered_number);
    }
    if (null == this.registrant) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, registrant);
    }
    if (null == this.status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.status);
    }
    if (null == this.tm_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tm_type);
    }
    if (null == this.update_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_time.getTime());
    __dataOut.writeInt(this.update_time.getNanos());
    }
    if (null == this.update_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.update_user);
    }
    if (null == this.yn) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.yn);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(brand_id==null?"null":"" + brand_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_key==null?"null":brand_key, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_logo_url==null?"null":brand_logo_url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name==null?"null":brand_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name_cn==null?"null":brand_name_cn, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name_en==null?"null":brand_name_en, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"null":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_user==null?"null":"" + create_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registered_number==null?"null":registered_number, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registrant==null?"null":registrant, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(status==null?"null":"" + status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tm_type==null?"null":tm_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_time==null?"null":"" + update_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_user==null?"null":"" + update_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(yn==null?"null":"" + yn, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(brand_id==null?"null":"" + brand_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_key==null?"null":brand_key, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_logo_url==null?"null":brand_logo_url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name==null?"null":brand_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name_cn==null?"null":brand_name_cn, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(brand_name_en==null?"null":brand_name_en, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_time==null?"null":"" + create_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(create_user==null?"null":"" + create_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registered_number==null?"null":registered_number, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(registrant==null?"null":registrant, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(status==null?"null":"" + status, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tm_type==null?"null":tm_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_time==null?"null":"" + update_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(update_user==null?"null":"" + update_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(yn==null?"null":"" + yn, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 9, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.brand_id = null; } else {
      this.brand_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_key = null; } else {
      this.brand_key = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_logo_url = null; } else {
      this.brand_logo_url = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name = null; } else {
      this.brand_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name_cn = null; } else {
      this.brand_name_cn = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name_en = null; } else {
      this.brand_name_en = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_time = null; } else {
      this.create_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_user = null; } else {
      this.create_user = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.registered_number = null; } else {
      this.registered_number = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.registrant = null; } else {
      this.registrant = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.status = null; } else {
      this.status = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tm_type = null; } else {
      this.tm_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_time = null; } else {
      this.update_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_user = null; } else {
      this.update_user = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.yn = null; } else {
      this.yn = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.brand_id = null; } else {
      this.brand_id = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_key = null; } else {
      this.brand_key = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_logo_url = null; } else {
      this.brand_logo_url = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name = null; } else {
      this.brand_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name_cn = null; } else {
      this.brand_name_cn = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.brand_name_en = null; } else {
      this.brand_name_en = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_time = null; } else {
      this.create_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.create_user = null; } else {
      this.create_user = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.registered_number = null; } else {
      this.registered_number = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.registrant = null; } else {
      this.registrant = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.status = null; } else {
      this.status = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tm_type = null; } else {
      this.tm_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_time = null; } else {
      this.update_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.update_user = null; } else {
      this.update_user = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.yn = null; } else {
      this.yn = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    item_brand o = (item_brand) super.clone();
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
    o.update_time = (o.update_time != null) ? (java.sql.Timestamp) o.update_time.clone() : null;
    return o;
  }

  public void clone0(item_brand o) throws CloneNotSupportedException {
    o.create_time = (o.create_time != null) ? (java.sql.Timestamp) o.create_time.clone() : null;
    o.update_time = (o.update_time != null) ? (java.sql.Timestamp) o.update_time.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("brand_id", this.brand_id);
    __sqoop$field_map.put("brand_key", this.brand_key);
    __sqoop$field_map.put("brand_logo_url", this.brand_logo_url);
    __sqoop$field_map.put("brand_name", this.brand_name);
    __sqoop$field_map.put("brand_name_cn", this.brand_name_cn);
    __sqoop$field_map.put("brand_name_en", this.brand_name_en);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("create_user", this.create_user);
    __sqoop$field_map.put("registered_number", this.registered_number);
    __sqoop$field_map.put("registrant", this.registrant);
    __sqoop$field_map.put("status", this.status);
    __sqoop$field_map.put("tm_type", this.tm_type);
    __sqoop$field_map.put("update_time", this.update_time);
    __sqoop$field_map.put("update_user", this.update_user);
    __sqoop$field_map.put("yn", this.yn);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("brand_id", this.brand_id);
    __sqoop$field_map.put("brand_key", this.brand_key);
    __sqoop$field_map.put("brand_logo_url", this.brand_logo_url);
    __sqoop$field_map.put("brand_name", this.brand_name);
    __sqoop$field_map.put("brand_name_cn", this.brand_name_cn);
    __sqoop$field_map.put("brand_name_en", this.brand_name_en);
    __sqoop$field_map.put("create_time", this.create_time);
    __sqoop$field_map.put("create_user", this.create_user);
    __sqoop$field_map.put("registered_number", this.registered_number);
    __sqoop$field_map.put("registrant", this.registrant);
    __sqoop$field_map.put("status", this.status);
    __sqoop$field_map.put("tm_type", this.tm_type);
    __sqoop$field_map.put("update_time", this.update_time);
    __sqoop$field_map.put("update_user", this.update_user);
    __sqoop$field_map.put("yn", this.yn);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
