<template>
    <!-- 顶部标签栏 -->
    <div class="app-container" v-loading="loading">
        <div class="round-container">
            <el-descriptions :column="3" border>
                <el-descriptions-item label="feature名称" :labelStyle="{ 'text-align': 'right' }">{{ featureInfo.name
                }}</el-descriptions-item>
                <el-descriptions-item label="创建人" :labelStyle="{ 'text-align': 'right' }">{{ featureInfo.creator
                }}</el-descriptions-item>
                <el-descriptions-item label="构建时间" :labelStyle="{ 'text-align': 'right' }">{{
                    parseTime(featureInfo.gmtCreate)
                }}</el-descriptions-item>
            </el-descriptions>
        </div>
        <!-- 流水线看板 -->
        <div class="round-container">
            <el-button type="primary" @click="openBuildDialog">发起构建</el-button>
            <el-row v-for="item in nodeDatas" type="flex">
                <el-col :span="6">
                    <el-row type="flex">
                        <el-col>
                            <!-- build -->
                            <node v-for="node in item.buildNodes" :key="node.id">
                                <i slot="status" v-if="node.status == 'FAILURE'" class="el-icon-circle-close"
                                    style="color:red;"></i>
                                <i slot="status" v-if="node.status == 'SUCCESS'" class="el-icon-circle-check"
                                    style="color:green;"></i>
                                <div class="loader" v-if="node.status == 'PROCESSING'" slot="status"><i
                                        class="el-icon-refresh-right" style="color:gray;"></i></div>
                                <div slot="time">{{ parseTime(node.gmtCreate) }}</div>
                                <router-link slot="middleSlot1" :to="'/project/detail/build_detail/' + node.id"
                                    class="link-type">
                                    构建详情
                                </router-link>
                                <router-link v-if="node.status == 'SUCCESS'" slot="middleSlot2"
                                    :to="'/project/detail/deploy?' + 'projectId=' + projectId + '&buildId=' + node.id + '&id=' + node.id + '&env=DEV' + '&op=DEPLOY'"
                                    class="link-type">
                                    部署
                                </router-link>
                                <el-link slot="bottomSlot1" type="primary"
                                    :href="'https://gitee.com/drew_zhl/springboot-demo/commit/' + node.commit"
                                    class="link-type">
                                    <!-- 限制显示长度为7 -->
                                    {{ node.commit.substring(0, 7) }}
                                </el-link>
                                <div slot="bottomSlot2" style="font-size: small;">{{ node.commitMessage }}</div>
                            </node>
                        </el-col>
                        <!-- <div class="line"></div> -->
                    </el-row>
                </el-col>
                <!-- dev -->
                <el-col :span="5">
                    <node v-for="node in item.devNodes" :key="node.id">
                        <i slot="status" v-if="node.status == 'FAILURE'" class="el-icon-circle-close"
                            style="color:red;"></i>
                        <i slot="status" v-if="node.status == 'SUCCESS'" class="el-icon-circle-check"
                            style="color:green;"></i>
                        <div class="loader" v-if="node.status == 'PROCESSING'" slot="status"><i
                                class="el-icon-refresh-right" style="color:gray;"></i></div>
                        <div slot="time">{{ parseTime(node.gmtCreate) }}</div>
                        <router-link slot="middleSlot1" :to="'/project/detail/deploy_detail/' + node.id" class="link-type">
                            部署详情
                        </router-link>
                        <router-link v-if="node.status == 'SUCCESS'" slot="middleSlot2"
                            :to="'/project/detail/deploy?' + 'projectId=' + projectId + '&buildId=' + node.buildId + '&id=' + node.id + '&env=QA' + '&op=DEPLOY'"
                            class="link-type">
                            部署
                        </router-link>
                        <el-tag v-if="node.status == 'SUCCESS'" effect="dark" size="mini" slot="bottomSlot1" type="success"
                            style="font-size: mini;">部署成功
                        </el-tag>
                        <el-tag v-if="node.status == 'FAILURE'" effect="dark" slot="bottomSlot1" size="mini" type="danger"
                            style="font-size: mini;">部署失败
                        </el-tag>
                        <el-tag v-if="node.status == 'RECYCLE'" effect="dark" slot="bottomSlot1" size="mini" type="primary"
                            style="font-size: mini;">回收成功
                        </el-tag>
                        <el-tag v-if="node.status == 'PROCESSING'" effect="dark" slot="bottomSlot1" size="mini"
                            type="primary" style="font-size: mini;">正在部署
                        </el-tag>
                    </node>
                </el-col>
                <!-- qa -->
                <el-col :span="5">
                    <node v-for="node in item.qaNodes" :key="node.id">
                        <i slot="status" v-if="node.status == 'FAILURE'" class="el-icon-circle-close"
                            style="color:red;"></i>
                        <i slot="status" v-if="node.status == 'SUCCESS'" class="el-icon-circle-check"
                            style="color:green;"></i>
                        <div class="loader" v-if="node.status == 'PROCESSING'" slot="status"><i
                                class="el-icon-refresh-right" style="color:gray;"></i></div>
                        <div slot="time">{{ parseTime(node.gmtCreate) }}</div>
                        <router-link slot="middleSlot1" :to="'/project/detail/deploy_detail/' + node.id" class="link-type">
                            部署详情
                        </router-link>
                        <router-link v-if="node.status == 'SUCCESS'" slot="middleSlot2"
                            :to="'/project/detail/deploy?' + 'projectId=' + projectId + '&buildId=' + node.buildId + '&id=' + node.id + '&env=PROD' + '&op=DEPLOY'"
                            class="link-type">
                            发布
                        </router-link>
                        <el-tag v-if="node.status == 'SUCCESS'" effect="dark" size="mini" slot="bottomSlot1" type="success"
                            style="font-size: mini;">部署成功
                        </el-tag>
                        <el-tag v-if="node.status == 'FAILURE'" effect="dark" slot="bottomSlot1" size="mini" type="danger"
                            style="font-size: mini;">部署失败
                        </el-tag>
                        <el-tag v-if="node.status == 'RECYCLE'" effect="dark" slot="bottomSlot1" size="mini" type="primary"
                            style="font-size: mini;">回收成功
                        </el-tag>
                        <el-tag v-if="node.status == 'PROCESSING'" effect="dark" slot="bottomSlot1" size="mini"
                            type="primary" style="font-size: mini;">正在部署
                        </el-tag>
                    </node>
                </el-col>
                <!-- prod -->
                <el-col :span="5">
                    <node v-for="node in item.prodNodes" :key="node.id">
                        <i slot="status" v-if="node.status == 'FAILURE'" class="el-icon-circle-close"
                            style="color:red;"></i>
                        <i slot="status" v-if="node.status == 'SUCCESS' || node.status == 'RECYCLE'"
                            class="el-icon-circle-check" style="color:green;"></i>
                        <div class="loader" v-if="node.status == 'PROCESSING'" slot="status"><i
                                class="el-icon-refresh-right" style="color:gray;"></i></div>
                        <div slot="time">{{ parseTime(node.gmtCreate) }}</div>
                        <router-link slot="middleSlot1" :to="'/project/detail/deploy_detail/' + node.id" class="link-type">
                            部署详情
                        </router-link>
                        <span v-if="node.status == 'SUCCESS'" slot="middleSlot2" @click="recycle(node, node.id)"
                            class="link-type">
                            回收
                        </span>
                        <router-link v-if="node.status == 'SUCCESS'" slot="middleSlot3"
                            :to="'/project/detail/deploy?' + 'projectId=' + projectId + '&buildId=' + node.buildId + '&id=' + node.id + '&env=PROD' + '&op=EXPANSION'"
                            class="link-type">
                            扩缩容
                        </router-link>
                        <span v-if="node.status == 'SUCCESS'" slot="middleSlot4" @click="openRollbackDialog(node, node.id)"
                            class="link-type">
                            回滚
                        </span>
                        <el-tag v-if="node.status == 'SUCCESS'" effect="dark" size="mini" slot="bottomSlot1" type="success"
                            style="font-size: mini;">发布成功
                        </el-tag>
                        <el-tag v-if="node.status == 'FAILURE'" effect="dark" slot="bottomSlot1" size="mini" type="danger"
                            style="font-size: mini;">发布失败
                        </el-tag>
                        <el-tag v-if="node.status == 'RECYCLE'" effect="dark" slot="bottomSlot1" size="mini" type="primary"
                            style="font-size: mini;">回收成功
                        </el-tag>
                        <el-tag v-if="node.status == 'PROCESSING'" effect="dark" slot="bottomSlot1" size="mini"
                            type="primary" style="font-size: mini;">正在作业
                        </el-tag>
                    </node>
                </el-col>
            </el-row>
        </div>
        <el-dialog title="发起构建" ref="buildDialog" :visible.sync="open" width="600px">
            <el-form ref="buildForm" :model="buildParam">
                <el-form-item label="构建分支" prop="branch">
                    <el-select v-model="buildParam.branch" placeholder="请选择构建分支">
                        <el-option v-for="item in branches" :key="item.name" :label="item.name" :value="item">
                            <span style="float: left">{{ item.name }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.branchUrl }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitBuild">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
        <el-dialog title="回滚" ref="rollbackDialog" :visible.sync="rollbackOpen" width="600px">
            <el-select v-model="rollbackValue" placeholder="请选择回滚的分支">
                <el-option v-for="item in rollbackVersions" :key="item" :label="item" :value="item">
                    <span style="float: left">{{ item }}</span>
                </el-option>
            </el-select>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitRollback">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<style lang="scss" scoped>
::v-deep .el-descriptions--medium:not(.is-bordered) .el-descriptions-item__cell {
    padding-bottom: 1;
    text-align: center;
}

::v-deep .el-descriptions-item__label.is-bordered-label {
    font-weight: bold;
    color: #909399;
    background: #ffffff;
}

::v-deep .el-descriptions .is-bordered .el-descriptions-item__cell {
    border: 0px solid #e6ebf5;
    padding: 12px 10px;
}
</style>
<style scoped>
.link-type {
    font-size: small;
    margin-right: 10px;
}

.line {
    border-radius: 2px;
    width: 3px;
    height: auto;
    background: #d3d3d3;
    margin: 10px;
    margin-left: 0;

}

.loader {
    animation: rotate 2s linear infinite;
}
</style>

<script>
import Node from './node.vue'
import { listBranch } from '@/api/system/project/project'
import { listNode, addNode, deployNode, recycleNode, rollbackNode, expansionNode } from '@/api/system/project/node'
import { listRollbackVersion } from '@/api/system/project/jenkins'
import { getFeature } from '@/api/system/project/feature'
import { runInThisContext } from 'vm'
export default {
    name: 'Detail',
    components: {
        Node
    },
    data() {
        return {
            loading: true,
            buildParam: {},
            rollbackNode: {},
            rollbackVersions: [],
            rollbackValue: '',
            rollbackOpen: false,
            queryParams: {
                // projectId: this.$route.query.id
                id: 20
            },
            branches: [],
            open: false,
            nodeDatas: [],
            featureInfo: {
                name: 'master',
                creator: 'admin',
                createTime: '2020-01-01 00:00:00'
            },
            projectId: undefined
        }
    },
    created() {
        this.projectId = this.$route.query.projectId
        this.listNodes();
        this.getFeatureInfo();

    },
    methods: {
        getFeatureInfo() {
            const featureId = this.$route.query.featureId
            getFeature({ featureId: featureId }).then(response => {
                this.featureInfo = response.data
            })
        },
        listNodes() {
            this.loading = true
            const featureId = this.$route.query.featureId
            const projectId = this.$route.query.projectId
            console.log('featureId', featureId);
            console.log('projectId', projectId);

            listNode({ projectId: projectId, featureId: featureId }).then(response => {
                this.nodeDatas = response.data
                // 等待1秒
                setTimeout(() => {
                    this.loading = false
                }, 500);
            })
        },
        cancel() {
            this.open = false
            this.rollbackOpen = false
            this.reset();
        },
        reset() {
            this.branches = []
            this.rollbackVersions = []
            this.resetForm('buildForm')
            // this.resetForm('rollbackDialog')
        },
        submitBuild() {
            this.open = false
            const params = {
                projectId: 20,
                branch: this.buildParam.branch.name,
                commit: this.buildParam.branch.commit,
                feature: this.featureInfo.name
            }
            addNode(params).then(response => {
                this.$message({
                    type: 'success',
                    message: '构建成功!'
                });
                this.listNodes();
            })
        },
        submitRollback() {
            console.log('rollback');
            rollbackNode({ nodeId: this.rollbackNode.id, projectId: this.projectId, version: this.rollbackValue }).then(response => {
                this.$message({
                    type: 'success',
                    message: '回滚成功!'
                });
                this.listNodes();
                this.rollbackOpen = false
            })
        },
        openBuildDialog() {
            this.open = true

            listBranch(this.queryParams).then(response => {
                this.branches = response.data
                this.branches.forEach(element => {
                    if (element.name === this.featureInfo.name) {
                        this.value = element.name
                    }
                });
            })
        },
        openRollbackDialog(node, id) {
            this.rollbackOpen = true
            // TODO
            listRollbackVersion({projectId:this.projectId,version:this.rollbackValue}).then(response => {
                this.rollbackVersions = response.data
            })
            this.rollbackNode = node
        },
        recycle(node, id) {
            this.$confirm('回收节点, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                recycleNode({ nodeId: id, projectId: this.projectId }).then(response => {
                    this.$message({
                        type: 'success',
                        message: '回收成功!'
                    });
                    this.listNodes();
                })
                this.$message({
                    type: 'warning',
                    message: '开始回收!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });


        }
    }
}
</script>