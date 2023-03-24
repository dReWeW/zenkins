<template>
    <div class="app-container">
        <div class="round-container">
            <el-row :gutter="20" style="margin-bottom: 10px;">
                <el-col :span="4">
                    <el-select v-model="defaultPlatform" placeholder="请选择平台">
                        <el-option v-for="item in platformList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="10">
                    <el-select v-model="defaultApp" placeholder="请选择应用">
                        <el-option v-for="item in appList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row>
                <el-form :model="queryParams" ref="queryForm" :inline="true">
                    <el-form-item prop="featureSearch">
                        <el-input v-model="featureSearch" placeholder="请输入Feature名称" clearable size="small"
                            style="width: 360px" @keyup.enter.native="handleQueryFeature">
                            <el-button slot="append" icon="el-icon-search" size="small"
                                @click="handleQueryFeature"></el-button>
                        </el-input>
                    </el-form-item>
                    <el-form-item style="line-height: 0;" scope>
                        <el-button icon="el-icon-plus" size="small" @click="handleCreateFeature"></el-button>
                    </el-form-item>
                </el-form>
            </el-row>
        </div>
        <div class="round-container">
            <el-table v-loading="loading" :data="featureList">
                <el-table-column label="Feature名称" align="center" prop="name" width="260">
                    <template v-slot="scope">
                        <router-link :to="'/project/detail?'+'projectId='+projectId+'&featureId='+scope.row.id" class="link-type">
                            <span>{{ scope.row.name }}</span>
                        </router-link>
                    </template>
                </el-table-column>
                <el-table-column label="创建时间" align="center" prop="createTime" sortable width="260">
                    <template v-slot="scope">
                        <span>{{ parseTime(scope.row.gmtCreate) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="构建次数" align="center" prop="buildCount" width="100" />
                <el-table-column label="最近构建时间" align="center" prop="createTime" sortable width="260">
                    <template v-slot="scope">
                        <span>{{ parseTime(scope.row.lastBuild) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="提测次数" align="center" prop="qaCount" width="100" />
                <el-table-column label="最近提测状态" prop="lastQaStatus" width="160W">
                    <template v-slot="scope">
                        <el-tag v-if="scope.row.lastQaStatus == 'SUCCESS'" type="success">提测成功</el-tag>
                        <el-tag v-else type="danger">提测失败</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="发布次数" prop="publishCount" width="100" />
                <el-table-column label="最近发布状态" prop="lastPublishStatus">
                    <template v-slot="scope">
                        <el-tag v-if="scope.row.lastPublishStatus == 'SUCCESS'" type="success">发布成功</el-tag>
                        <el-tag v-else type="danger">发布失败</el-tag>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog :title="'创建feature'" :visible.sync="featCreateShow" width="700px" append-to-body>
                <el-form ref="featureForm" :model="form" label-width="120px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="feature名称" prop="name">
                                <el-input v-model="form.name" :disabled="false" placeholder="请输入feature名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="createFeature">添 加</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>
<style lang="scss">
.el-form-item--medium .el-form-item__content {
    line-height: 1px;
}

.app-container {
    background-color: rgb(250, 250, 250)
}
</style>
<style lang="scss" scoped>
::v-deep .el-form-item {
    margin-bottom: 0;
}
</style>
<script>
import {
    listProject
} from '@/api/system/project';

import {addFeature,listFeature,pageFeature} from '@/api/system/project/feature'


export default {
    data() {
        return {
            projectId: 20,
            form:{
                projectId: 20
            },
            featCreateShow: false,
            defaultPlatform: '1',
            defaultApp: '1',
            platformList: [{
                value: '1',
                label: 'web'
            }, {
                value: '2',
                label: 'android'
            }],
            appList: [{
                value: '1',
                label: 'cloud-eureka'
            }, {
                value: '2',
                label: 'content-production'
            }, {
                value: '3',
                label: 'content-search'
            }],
            projectList: [],
            total: 0,
            featureSearch: '',
            loading: true,
            featureList: [],
            queryParams: {
                pageNo: 1,
                pageSize: 10,
                name: undefined,
                code: undefined,
                status: undefined,
                createTime: []
            }
        }
    },
    created() {
        this.loading = false;
        this.getFeatureList();
    },
    methods: {
        getFeatureList(){
            listFeature({projectId:20}).then(response => {
                this.featureList = response.data;
            });
        },
        createFeature(){
            addFeature(this.form).then(response => {
                this.$message({
                    message: '创建成功',
                    type: 'success'
                });
                this.featCreateShow = false;
                this.getFeatureList();
            });
        },
        handleQueryFeature() {
            this.queryParams.name = this.featureSearch;
            this.queryParams.pageNo = 1;
            this.$refs.queryForm.validate((valid) => {
                if (valid) {
                    console.log("query feature:" + this.queryParams);
                    // listProject(this.queryParams).then(response => {
                    //     this.projectList = response.data.records;
                    //     this.total = response.data.total;
                    // });
                }
            });
        },
        handleCreateFeature() {
            this.featCreateShow = true;
        }
    }

}
</script>