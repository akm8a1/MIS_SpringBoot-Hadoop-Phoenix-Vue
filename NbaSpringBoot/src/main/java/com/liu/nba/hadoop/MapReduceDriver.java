package com.liu.nba.hadoop;
import com.liu.nba.pojo.Player;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class MapReduceDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(MapReduceDriver.class);
        job.setMapperClass(FileFormatMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Player.class);
        job.setOutputFormatClass(FileFormatOutputFormat.class);
        FileInputFormat.setInputPaths(job,new Path("C:\\Users\\Administrator\\Desktop\\data\\nba_2017_players_data.csv"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.188.101:9000/nba/data"));
        job.waitForCompletion(true);
    }
}
class FileFormatMapper extends Mapper<LongWritable,Text,LongWritable, Player>{
    private Player player = new Player();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /*获取当前的这一行数据*/
        String line = value.toString();
        System.out.println(line);
        String[] words = line.split(",");
        String rowKey = words[0]; //rowKey
        int rk = Integer.parseInt(words[1]); //联盟排名
        String player = words[2]; //球员
        String position = words[3]; //位置
        int age = Integer.parseInt(words[4]); //年龄
        double mp = Double.parseDouble(words[5]); //上场时间
        double fg = Double.parseDouble(words[6]); //运动战命中数
        double fga= Double.parseDouble(words[7]); //运动战出手数
        double fg_percent = Double.parseDouble(words[8]); //运动战命中率
        double three = Double.parseDouble(words[9]); //场均三分
        double threea = Double.parseDouble(words[10]); //场均三分出手
        double three_percent = Double.parseDouble(words[11]); //三分命中率
        double two = Double.parseDouble(words[12]); //场均两分
        double twoa = Double.parseDouble(words[13]); //场均两分出手
        double two_percent = Double.parseDouble(words[14]); //两分命中率
        double efg = Double.parseDouble(words[15]); //有效命中率
        double ft = Double.parseDouble(words[16]); //罚球
        double fta = Double.parseDouble(words[17]); //罚球出手
        double ft_percent = Double.parseDouble(words[18]); //罚球命中率
        double orb = Double.parseDouble(words[19]); //进攻/前场篮板
        double drb = Double.parseDouble(words[20]); //防守/后场篮板
        double trb = Double.parseDouble(words[21]); //篮板球总数
        double ast = Double.parseDouble(words[22]); //发球命中率
        double stl = Double.parseDouble(words[23]); //抢断
        double blk = Double.parseDouble(words[24]); //盖帽
        double tov = Double.parseDouble(words[25]); //失误次数
        double pf = Double.parseDouble(words[26]); //个人犯规次数
        double points = Double.parseDouble(words[27]); //场均得分
        String team = words[28];//所属球队
        int gp = Integer.parseInt(words[29]); //出场次数
        double orpm = Double.parseDouble(words[30]); //进攻真实正负值
        double drpm = Double.parseDouble(words[31]); //防守真实正负值
        double rpm = Double.parseDouble(words[32]); //真实正负值
        double wins_rpm  = Double.parseDouble(words[33]); //胜利贡献值
        double pie = Double.parseDouble(words[34]); //胜利贡献值
        double pace = Double.parseDouble(words[35]); //场均回合数
        int w = Integer.parseInt(words[36]); //胜利场次数场均回合数
        double salary_millions = Double.parseDouble(words[37]); //薪资
        double w_percentage = (double)w/gp;
        this.player.set(rowKey,player,age,rk,position,team,salary_millions,gp,mp,fg,fga,fg_percent,three,threea,three_percent,two,twoa,two_percent,efg,ft,fta,ft_percent,orb,drb,trb,ast,stl,blk,tov,pf,points,orpm,drpm,rpm,wins_rpm,pie,w,pace,w_percentage);
        System.out.println(this.player);
        context.write(key,this.player);
    }
}
class FileFormatOutputFormat extends FileOutputFormat<LongWritable,Player>{
    @Override
    public RecordWriter<LongWritable, Player> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        FileFormatRecordWriter fileFormatRecordWriter = new FileFormatRecordWriter();
        fileFormatRecordWriter.initialize(taskAttemptContext);
        return fileFormatRecordWriter;
    }
}
class FileFormatRecordWriter extends RecordWriter<LongWritable,Player>{
    private FileSystem fs = HdfsUtils.getFileSystem();
    private FSDataOutputStream outputStream;
    public void initialize(TaskAttemptContext job) throws IOException {
        String outPath = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        outputStream = fs.create(new Path(outPath+"/player_data.dat"));
    }
    @Override
    public void write(LongWritable longWritable, Player player) throws IOException, InterruptedException {
        String line = player.toString()+"\n";
        outputStream.write(line.getBytes(StandardCharsets.UTF_8));
    }
    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        outputStream.close();
        fs.close();
    }
}
