package com.liu.nba.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
public class Player implements Writable {
    private String rowkey;
    //base
    private String player; //球员
    private int age; //年龄
    private int rk; //联盟排名
    private String position; //位置
    private String team; //所属球队
    private double salary_millions; //薪资
    //performance
    private int gp; //出场次数
    private double mp; //上场时间
    private double fg; //运动战命中数
    private double fga; //运动战出手数
    private double fg_percent; //运动战命中率
    private double three; //场均三分
    private double threea; //场均三分出手
    private double three_percent; //三分命中率
    private double two; //场均两分
    private double twoa; //场均两分出手
    private double two_percent; //两分命中率
    private double efg; //有效命中率
    private double ft; //罚球
    private double fta; //罚球出手
    private double ft_percent; //罚球命中率
    private double orb; //进攻/前场篮板
    private double drb; //防守/后场篮板
    private double trb; //篮板球总数
    private double ast; //助攻
    private double stl; //抢断
    private double blk; //盖帽
    private double tov; //失误次数
    private double pf; //个人犯规次数
    private double points; //场均得分
    //advanced
    private double orpm; //进攻真实正负值
    private double drpm; //防守真实正负值
    private double rpm; //真实正负值
    private double wins_rpm; //胜利贡献值
    private double pie; //球员贡献度
    private int w; //胜利场次
    private double pace; //场均回合数
    private double w_percentage; //个人胜率

    public void set(String rowkey,String player, int age, int rk, String position, String team, double salary_millions, int gp, double mp, double fg, double fga, double fg_percent, double three, double threea, double three_percent, double two, double twoa, double two_percent, double efg, double ft, double fta, double ft_percent, double orb, double drb, double trb, double ast, double stl, double blk, double tov, double pf, double points, double orpm, double drpm, double rpm, double wins_rpm, double pie, int w, double pace,double w_percentage) {
        this.rowkey = rowkey;
        this.player = player;
        this.age = age;
        this.rk = rk;
        this.position = position;
        this.team = team;
        this.salary_millions = salary_millions;
        this.gp = gp;
        this.mp = mp;
        this.fg = fg;
        this.fga = fga;
        this.fg_percent = fg_percent;
        this.three = three;
        this.threea = threea;
        this.three_percent = three_percent;
        this.two = two;
        this.twoa = twoa;
        this.two_percent = two_percent;
        this.efg = efg;
        this.ft = ft;
        this.fta = fta;
        this.ft_percent = ft_percent;
        this.orb = orb;
        this.drb = drb;
        this.trb = trb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pf = pf;
        this.points = points;
        this.orpm = orpm;
        this.drpm = drpm;
        this.rpm = rpm;
        this.wins_rpm = wins_rpm;
        this.pie = pie;
        this.w = w;
        this.pace = pace;
        this.w_percentage = w_percentage;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.rowkey);
        dataOutput.writeUTF(this.player);
        dataOutput.writeInt(this.age);
        dataOutput.writeInt(this.rk);
        dataOutput.writeUTF(this.position);
        dataOutput.writeUTF(this.team);
        dataOutput.writeDouble(this.salary_millions);
        dataOutput.writeInt(this.gp);
        dataOutput.writeDouble(this.mp);
        dataOutput.writeDouble(this.fg);
        dataOutput.writeDouble(this.fga);
        dataOutput.writeDouble(this.fg_percent);
        dataOutput.writeDouble(this.three);
        dataOutput.writeDouble(this.threea);
        dataOutput.writeDouble(this.three_percent);
        dataOutput.writeDouble(this.two);
        dataOutput.writeDouble(this.twoa);
        dataOutput.writeDouble(this.two_percent);
        dataOutput.writeDouble(this.efg);
        dataOutput.writeDouble(this.ft);
        dataOutput.writeDouble(this.fta);
        dataOutput.writeDouble(this.ft_percent);
        dataOutput.writeDouble(this.orb);
        dataOutput.writeDouble(this.drb);
        dataOutput.writeDouble(this.trb);
        dataOutput.writeDouble(this.ast);
        dataOutput.writeDouble(this.stl);
        dataOutput.writeDouble(this.blk);
        dataOutput.writeDouble(this.tov);
        dataOutput.writeDouble(this.pf);
        dataOutput.writeDouble(this.points);
        dataOutput.writeDouble(this.orpm);
        dataOutput.writeDouble(this.drpm);
        dataOutput.writeDouble(this.rpm);
        dataOutput.writeDouble(this.wins_rpm);
        dataOutput.writeDouble(this.pie);
        dataOutput.writeInt(this.w);
        dataOutput.writeDouble(this.pace);
        dataOutput.writeDouble(this.w_percentage);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.rowkey = dataInput.readUTF();
        this.player = dataInput.readUTF();
        this.age = dataInput.readInt();
        this.rk = dataInput.readInt();
        this.position = dataInput.readUTF();
        this.team = dataInput.readUTF();
        this.salary_millions = dataInput.readDouble();
        this.gp = dataInput.readInt();
        this.mp = dataInput.readDouble();
        this.fg = dataInput.readDouble();
        this.fga = dataInput.readDouble();
        this.fg_percent = dataInput.readDouble();
        this.three = dataInput.readDouble();
        this.threea = dataInput.readDouble();
        this.three_percent = dataInput.readDouble();
        this.two = dataInput.readDouble();
        this.twoa = dataInput.readDouble();
        this.two_percent = dataInput.readDouble();
        this.efg = dataInput.readDouble();
        this.ft = dataInput.readDouble();
        this.fta = dataInput.readDouble();
        this.ft_percent = dataInput.readDouble();
        this.orb = dataInput.readDouble();
        this.drb = dataInput.readDouble();
        this.trb = dataInput.readDouble();
        this.ast = dataInput.readDouble();
        this.stl = dataInput.readDouble();
        this.blk = dataInput.readDouble();
        this.tov = dataInput.readDouble();
        this.pf = dataInput.readDouble();
        this.points = dataInput.readDouble();
        this.orpm = dataInput.readDouble();
        this.drpm = dataInput.readDouble();
        this.rpm = dataInput.readDouble();
        this.wins_rpm = dataInput.readDouble();
        this.pie = dataInput.readDouble();
        this.w = dataInput.readInt();
        this.pace = dataInput.readDouble();
        this.w_percentage = dataInput.readDouble();
    }
    @Override
    public String toString() {
        return this.rowkey+","+
        this.player+","+
        this.age+","+
        this.rk+","+
        this.position+","+
        this.team+","+
        this.salary_millions+","+
        this.gp+","+
        this.mp+","+
        this.fg+","+
        this.fga+","+
        this.fg_percent+","+
        this.three+","+
        this.threea+","+
        this.three_percent+","+
        this.two+","+
        this.twoa+","+
        this.two_percent+","+
        this.efg+","+
        this.ft+","+
        this.fta+","+
        this.ft_percent+","+
        this.orb+","+
        this.drb+","+
        this.trb+","+
        this.ast+","+
        this.stl+","+
        this.blk+","+
        this.tov+","+
        this.pf+","+
        this.points+","+
        this.orpm+","+
        this.drpm+","+
        this.rpm+","+
        this.wins_rpm+","+
        this.pie+","+
        this.w+","+
        this.pace+","+
        this.w_percentage;
    }
}
