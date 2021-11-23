package com.liu.nba.pojo;

import com.sun.corba.se.spi.ior.Writeable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;
import org.checkerframework.common.aliasing.qual.NonLeaked;
import org.omg.CORBA_2_3.portable.OutputStream;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
public class Team implements Writable {
    private String rowKey; //行键rowKey
    private String team; //队名
    private int totalscore; //球队总资产
    private int yearscore; //本赛季球队得分
    private double perscore; //球队场均得分
    private int salary; //球队本赛季薪资总额
    public void set(String rowKey,String team,int totalscore,int yearscore,Double perscore,int salary) {
        this.rowKey = rowKey;
        this.team = team;
        this.totalscore = totalscore;
        this.yearscore = yearscore;
        this.perscore = perscore;
        this.salary = salary;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.rowKey);
        dataOutput.writeUTF(this.team);
        dataOutput.writeInt(this.totalscore);
        dataOutput.writeInt(this.yearscore);
        dataOutput.writeDouble(this.perscore);
        dataOutput.writeInt(this.salary);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.rowKey = dataInput.readUTF();
        this.team = dataInput.readUTF();
        this.totalscore = dataInput.readInt();
        this.yearscore = dataInput.readInt();
        this.perscore = dataInput.readDouble();
        this.salary = dataInput.readInt();
    }
}
