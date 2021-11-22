<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 球员表现
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
                <el-input v-model="player_name" placeholder="球员名" class="handle-input mr10"></el-input>
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
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column label="球员" prop="player" align="center"></el-table-column>
                    <el-table-column label="出场时间" prop="mp" align="center"></el-table-column>
                    <el-table-column label="运动进球" prop="fg" align="center"></el-table-column>
                    <el-table-column label="三分命中" prop="three" align="center"></el-table-column>
                    <el-table-column label="两份命中" prop="two" align="center"></el-table-column>
                    <el-table-column label="罚球命中" prop="ft" align="center"></el-table-column>
                    <el-table-column label="篮板球" prop="trb" align="center"></el-table-column>
                    <el-table-column label="助攻" prop="ast" align="center"></el-table-column>
                    <el-table-column label="抢断" prop="stl" align="center"></el-table-column>
                    <el-table-column label="盖帽" prop="blk" align="center"></el-table-column>
                    <el-table-column label="犯规" prop="pf" align="center"></el-table-column>
                    <el-table-column label="失误" prop="tov" align="center"></el-table-column>
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

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
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
        // 分页导航
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
