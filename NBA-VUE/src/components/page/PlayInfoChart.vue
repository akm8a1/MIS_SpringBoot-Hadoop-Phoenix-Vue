<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> 球员数据可视化
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="content-title">{{player.player}}</div>
            <el-row type="flex" class="row-bg">
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="BaseData" :style="{width: '1000px', height: '300px'}"></div>
                    </div>
                </el-col>  
            </el-row>      
             <el-row type="flex" class="row-bg">
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="RKSchart" :style="{width: '1000px', height: '500px'}"></div>
                    </div>
                </el-col>  
            </el-row>  
            <el-row type="flex" class="row-bg">
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="Percent" :style="{width: '500px', height: '300px'}"></div>
                    </div>
                </el-col>  
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="ThreePercent" :style="{width: '500px', height: '300px'}"></div>
                    </div>
                </el-col>  
            </el-row>     
            <el-row type="flex" class="row-bg">
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="TwoPercent" :style="{width: '500px', height: '300px'}"></div>
                    </div>
                </el-col>  
                <el-col :span="20">
                    <div class="schart-box">
                    <div id="FtPercent" :style="{width: '500px', height: '300px'}"></div>
                    </div>
                </el-col>  
            </el-row>      
        </div>
    </div>
</template>

<script>
export default {
    name: 'basecharts',
    components: {
    },
    data() {
        return {
            player: this.$route.query.row,
            rankData: 123,
        };
    },
    methods: {
    async getRkData() {
       await this.$axios.post("http://localhost:8080/getRk", this.player,{
            headers: {
                 "Content-Type": "application/json;charset=utf-8"  //头部信息
            }
         })
        .then(response =>{
            this.rankData = response.data;
         })   
    },
    drawLineBase(){
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('BaseData'));
        myChart.setOption({
            title: {
                text: ' 球员基础数据'
            },
            tooltip: {
                show : true
            },
            xAxis: [{
                type : 'category',
                data: ['年龄','联盟排名','场均得分','场均出手','出场次数','上场时间',
                        '有效命中率','前场篮板','后场篮板','篮板球','助攻',
                        '抢断','盖帽','失误','犯规'],
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: true,
                    interval: 0
                }
            }],
            yAxis: [{
                type: "value",
            }],
            series: [
            {
                type: 'bar',
                data: [this.player.age,this.player.rk,this.player.points,this.player.fga,this.player.gp,this.player.mp,
                       this.player.efg,this.player.orb,this.player.drb,this.player.trb,this.player.ast,this.player.stl,this.player.blk,this.player.tov,this.player.pf],
                colorBy: 'data',
                label: {
                        show: true,
                        offset: [0, -10],
                },
            }
          ]
        });
    },
    drawLinePercent(){
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('Percent'));
        myChart.setOption({
            legend: {
                        orient: "vertical",
                        left: "right",
                        data: ["运动战命中", "运动战未中"]
                    },
            color: ["#FAFAD2","#40E0D0"],
            title: {
                text: ' 运动战出手与命中'
            },
            tooltip: {
                show : true
            },
            series: [
            {
                type: 'pie',
                data: [
                        {
                            value: this.player.fg,
                            name: '运动战命中'
                        },
                        {
                            value: this.player.fga-this.player.fg,
                            name: '运动战未中'
                        },
                    ],
                label: {
                        show: true,
                        formatter: '{b}\n{c}\n{d}%'
                },
            }],
        });
    },
    drawThreePercent(){
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('ThreePercent'));
        myChart.setOption({
            legend: {
                        orient: "vertical",
                        left: "right",
                        data: ["三分球命中", "三分球未中"]
                    },
            color: ["#FF6347","#40E0D0"],
            title: {
                text: ' 三分球出手与命中'
            },
            tooltip: {
                show : true
            },
            series: [
            {
                type: 'pie',
                data: [
                        {
                            value: this.player.three,
                            name: '三分球命中'
                        },
                        {
                            value: this.player.threea-this.player.three,
                            name: '三分球未中'
                        },
                    ],
                label: {
                        show: true,
                        formatter: '{b}\n{c}\n{d}%'
                },
            }],
        });
    },
    drawTwoPercent(){
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('TwoPercent'));
        myChart.setOption({
            legend: {
                        orient: "vertical",
                        left: "right",
                        data: ["两分球命中", "两分球未中"]
                    },
            color: ["#FFA500","#40E0D0"],
            title: {
                text: ' 两分球出手与命中'
            },
            tooltip: {
                show : true
            },
            series: [
            {
                type: 'pie',
                data: [
                        {
                            value: this.player.two,
                            name: '两分球命中'
                        },
                        {
                            value: this.player.twoa-this.player.two,
                            name: '两分球未中'
                        },
                    ],
                label: {
                        show: true,
                        formatter: '{b}\n{c}\n{d}%'
                },
            }],
        });
    },
    drawFtPercent(){
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('FtPercent'));
        myChart.setOption({
            legend: {
                        orient: "vertical",
                        left: "right",
                        data: ["罚球命中", "罚球未中"]
                    },
            color: ["#EE82EE","#40E0D0"],
            title: {
                text: ' 罚球出手与命中'
            },
            tooltip: {
                show : true
            },
            series: [
            {
                type: 'pie',
                data: [
                        {
                            value: this.player.two,
                            name: '罚球命中'
                        },
                        {
                            value: this.player.twoa-this.player.two,
                            name: '罚球未中'
                        },
                    ],
                label: {
                        show: true,
                        formatter: '{b}\n{c}\n{d}%'
                },
            }],
        });
    },
    //data: ['排名','出场时间','进球','出手','三分','真实命中','罚球',
    //                    '篮板','助攻','抢断','盖帽','失误','犯规','得分','正负值','胜利贡献度','球员贡献度'],
    async drawRKSchart(){
        await this.getRkData();
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('RKSchart'));
        myChart.setOption({
            title: {
                text: '球员数据排名'
                },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                    }
                },
            legend: {},
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                //boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                axisLabel: {
                    show: true,
                    interval: 0
                },
                data: ['排名','出场时间','进球','出手','三分','真实命中','罚球',
                    '篮板','助攻','抢断','盖帽','失误','犯规','得分','正负值','胜利贡献度','球员贡献度']
            },
            series: [
                {
                    type: 'bar',
                    data: this.rankData,
                    colorBy: 'data',
                }
            ]
      });
    },
    },
    created () {
        this.player = this.$route.query.row;     
        this.$nextTick(() => {
           this.drawLineBase();
           this.drawLinePercent();
           this.drawThreePercent();
           this.drawTwoPercent();
           this.drawFtPercent();
           this.drawRKSchart();
        })
    }
};
</script>

<style scoped>
.schart-box {
    display: inline-block;
    margin: 20px;
}
.schart {
    width: 600px;
    height: 400px;
}
.content-title {
    clear: both;
    font-weight: 400;
    line-height: 50px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
}
.el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #62da2bbb;
  }
  .bg-purple {
    background: #db8a53;
  }
  .bg-purple-light {
    background: #94d6bb;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
    height: 36px;
    line-height: 36px;
    text-align: center;
    color: #f9fafc;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>