<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> 联盟数据可视化
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-row type="flex" class="row-bg">
                <el-col :span="20">
                    <div class="schart-box">
                    <div class="content-title">联盟记录保持者</div>
                    <div id="BestRecord" :style="{width: '500px', height: '300px'}"></div>
                    </div>
                </el-col>
                <el-col :span="20">
                    <el-row type="flex" class="row-bg">
                        <el-col :span="10"><div class="grid-content bg-purple"><span>最高龄球员:{{maxData[0].player}}</span></div></el-col>
                        <el-col :span="10" offset="2"><div class="grid-content bg-purple"><span>最高薪球员 :{{maxData[1].player}}</span></div></el-col>
                    </el-row>
                    <el-row type="flex" class="row-bg">
                        <el-col :span="10"><div class="grid-content bg-purple"><span>得分王:{{maxData[2].player}}</span></div></el-col>
                        <el-col :span="10" offset="2"><div class="grid-content bg-purple"><span>出场最多:{{maxData[3].player}}</span></div></el-col>
                    </el-row>
                    <el-row type="flex" class="row-bg">
                        <el-col :span="10"><div class="grid-content bg-purple"><span>三分王:{{maxData[4].player}}</span></div></el-col>
                        <el-col :span="10" offset="2"><div class="grid-content bg-purple"><span>罚球最多:{{maxData[7].player}}</span></div></el-col>
                    </el-row>
                    <el-row type="flex" class="row-bg">
                        <el-col :span="10"><div class="grid-content bg-purple"><span>篮板王:{{maxData[8].player}}</span></div></el-col>
                        <el-col :span="10" offset="2"><div class="grid-content bg-purple"><span>赛季助攻王:{{maxData[9].player}}</span></div></el-col>
                    </el-row>
                    <el-row type="flex" class="row-bg">
                        <el-col :span="10"><div class="grid-content bg-purple"><span>抢断王:{{maxData[10].player}}</span></div></el-col>
                        <el-col :span="10" offset="2"><div class="grid-content bg-purple"><span>盖帽王:{{maxData[11].player}}</span></div></el-col>
                    </el-row>
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
            maxData: [],
            age: 19
        };
    },
    methods: {
        async getData() {
            await this.$axios.get('http://localhost:8080/getMaxs')
            .then(response => {
                this.maxData = response.data;
            })
        },
        async drawLine(){
        await this.getData();
        //基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('BestRecord'));
        myChart.setOption({
            title: {
                text: '联盟记录'
            },
            tooltip: {
                show : true
            },
            xAxis: [{
                type : 'category',
                data: ['年龄', '薪资', '得分','时间', '三分', '罚球','篮板','助攻','抢断','盖帽'],
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
             legend: {
                show: true,
                itemStyle: {},
                textStyle: {},
                data: [{
                    name: '系列1',
                    // 强制设置图形为圆。
                    icon: 'circle',
                    // 设置文本为红色
                    textStyle: {
                    color: 'red'
                    }
            }]
            },
            series: [
            {
                type: 'bar',
                data: [this.maxData[0].age, this.maxData[1].salary_millions, this.maxData[2].points, this.maxData[3].mp, this.maxData[4].three,
                       this.maxData[7].ft,this.maxData[8].trb,this.maxData[9].ast,this.maxData[10].stl,
                       this.maxData[11].blk],
                colorBy: 'data',
                label: {
                        show: true,
                        offset: [0, -10],
                },
            }
          ]
        });
        }
    },
    created () {
        this.$nextTick(() => {
           this.drawLine();
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