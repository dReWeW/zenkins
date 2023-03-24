<template>
    <div class="app-container">
        <div class="round-container">
            <!-- <el-descriptions :column="3" border>
                <el-descriptions-item label="feature名称" :labelStyle="{ 'text-align': 'right' }">{{ buildInfo.name
                }}</el-descriptions-item>
                <el-descriptions-item label="构建人" :labelStyle="{ 'text-align': 'right' }">{{ buildInfo.creator
                }}</el-descriptions-item>
                <el-descriptions-item label="构建时间" :labelStyle="{ 'text-align': 'right' }">{{ buildInfo.createTime
                }}</el-descriptions-item>
            </el-descriptions> -->
        </div>
        <el-form ref="form" :model="form" label-width="80px" :rules="formRules">
            <el-form-item label="节点配置" prop="nodes">
                <el-radio-group v-model="form.allocation">
                    <el-radio-button label="SINGLE">单节点</el-radio-button>
                    <el-radio-button label="DOUBLE">双节点</el-radio-button>
                    <el-radio-button label="QUADRA">四节点</el-radio-button>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="stable部署" prop="stable">
                <el-switch v-model="form.stable">
                </el-switch>
            </el-form-item>
            <el-form-item v-show="env === 'PROD'" label="版本号" prop="version">
                <el-input v-model="form.version" placeholder="请输入版本号，格式为 x.x.x"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-caret-right" @click="handelDeploy">确认</el-button>
                <el-button icon="el-icon-close" @click="handelCancel">取消</el-button>
            </el-form-item>
        </el-form>
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
<script>
import { getNodeDetail } from "@/api/system/project/node";
import { listNode, addNode, deployNode, recycleNode, rollbackNode, expansionNode } from '@/api/system/project/node'

export default {
    name: 'Deploy',
    data() {
        return {
            buildInfo: {
                name: 'S50',
                creator: '郑浩龙',
                createTime: '2020-01-01 00:00:00'
            },
            form: {
                allocation: 'SINGLE',
                stable: true,
                createTime: '2020-01-01 00:00:00'
            },
            buildId: undefined,
            projectId: undefined,
            env: undefined,
            op: undefined,
            id: undefined,
            version: '',
            formRules: {
                version: [
                    { required: true, message: '请输入版本号', trigger: 'blur' },
                    { pattern: /^[0-9]+\.[0-9]+\.[0-9]+$/, message: '版本号格式不正确，格式应为 x.x.x，其中 x 为数字', trigger: 'blur' }
                ],
            },
        }
    },
    created() {
        this.buildId = this.$route.query.buildId
        this.projectId = this.$route.query.projectId
        this.op = this.$route.query.op
        this.env = this.$route.query.env
        this.id = this.$route.query.id
        this.form.buildId = this.buildId
        this.form.projectId = this.projectId
        this.form.env = this.env
        // this.getFeatureInfo()
    },
    methods: {
        handelDeploy() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    if (this.op == 'DEPLOY') {
                        deployNode(this.form).then(res => {
                            this.$message({
                                message: '部署成功',
                                type: 'success'
                            })
                            this.$router.go(-1)
                        })
                    }
                    if (this.op == 'EXPANSION') {
                        this.form.nodeId = this.id;
                        expansionNode(this.form).then(res => {
                            this.$message({
                                message: '扩缩容成功',
                                type: 'success'
                            })
                            this.$router.go(-1)
                        })
                    }
                } else {
                    return false;
                }
            });
        },
        handelCancel() {
            this.$router.go(-1)
        }
    }
}
</script>