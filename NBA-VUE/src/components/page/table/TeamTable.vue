<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 球队
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-input v-model="team_name" placeholder="球队名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="100" align="center"></el-table-column>
                <el-table-column prop="rowKey" label="序号" width="175" align="center"></el-table-column>
                <el-table-column prop="team" label="球队" width="175" align="center"></el-table-column>
                <el-table-column prop="totalscore" label="球队总资产（万美元）" width="175" align="center"></el-table-column> 
                <el-table-column prop="yearscore" label="球队赛季得分" width="175" align="center"></el-table-column> 
                <el-table-column prop="perscore" label="场均分" width="175" align="center"></el-table-column> 
                <el-table-column prop="salary" label="球队总薪金（万美元）" width="175" align="center"></el-table-column> 
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
import { fetchData } from '../../../api/index';
export default {
    name: 'basetable',
    data() {
        return {
            team_name: '',
            tableData: [],
            multipleSelection: [], //多选框
            delList: [],  
            pageSize: 10,
            editVisible: false,
            total: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            this.$axios.get('http://localhost:8080/teams/'+1+'/'+10)
            .then(response => {
                this.tableData = response.data;      
            }),
            this.$axios.get('http://localhost:8080/teamscount')
            .then(response => {
                this.total = response.data;
            })
        },
        // 触发搜索按钮
        handleSearch() {
            this.$axios.get('http://localhost:8080/getByTeamName/'+this.team_name+'/'+1+'/'+10)
            .then(response => {
                this.tableData = response.data;      
            })
        },
        // 分页导航
        handleCurrentChange(currentPage) {
            this.$axios.get('http://localhost:8080/teams/'+currentPage+'/'+this.pageSize)
            .then(response => {
                this.tableData = response.data;      
            })
        },
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
