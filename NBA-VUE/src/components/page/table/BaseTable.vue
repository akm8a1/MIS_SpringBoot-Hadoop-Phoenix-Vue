<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 球员概览
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                >批量删除</el-button>
                <el-input v-model="player_name" placeholder="球员名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <!--@selection-change="handleSelectionChange"-->
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="rk" label="排名" width="55" align="center"></el-table-column>
                <el-table-column prop="player" label="球员" align="center"></el-table-column>
                <el-table-column prop="team" label="球队" align="center"></el-table-column>
                <el-table-column prop="age" label="年龄" align="center"></el-table-column>
                <el-table-column prop="position" label="位置" align="center"></el-table-column>
                <el-table-column prop="salary_millions" label="薪金(百万美元)/年" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-pie-chart"
                            class="red"
                            @click="lookChart(scope.$index, scope.row)"
                        >图表</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :total="total"
                    :page-size="pageSize"
                    @current-change="handleCurrentChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'basetable',
    data() {
        return {
            player_name: '',
            tableData: [],
            multipleSelection: [], //多选框
            delList: [],  
            editVisible: false,
            total: 0,
            pageSize: 10,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData()
    },
    methods: {
        getData() {
            this.$axios.get('http://localhost:8080/players/'+1+'/'+10)
            .then(response => {
                this.tableData = response.data;      
            }),
            this.$axios.get('http://localhost:8080/playerscount')
            .then(response => {
                this.total = response.data;
            })
        },
        // 触发搜索按钮
        handleSearch() {
            this.$axios.get('http://localhost:8080/getPlayerByName/'+this.player_name+'/'+1+'/'+this.pageSize)
            .then(response => {
                this.tableData = response.data;      
            }),
            this.$axios.get('http://localhost:8080/getCounts')
            .then(response => {
                this.total = response.data;
            })
        },
        handleCurrentChange(currentPage) { //分页
            this.$axios.get('http://localhost:8080/players/'+currentPage+'/'+this.pageSize)
            .then(response => {
                this.tableData = response.data;      
            })
        }, 
        lookChart(index,row) {
            this.$router.push({ path: '/playerChart', query: { index: index, row: row } })
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
