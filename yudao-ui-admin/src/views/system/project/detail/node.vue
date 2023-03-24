<template>
    <div class="node">
        <el-col :gutter="5">
            <el-row :span="4" style="font-size: small;" type="flex" justify="space-between">
                <slot name="time"></slot>
                <slot name="status"></slot>
            </el-row>
            <el-row :span="8" style="margin-top:10px;">
                <slot name="middleSlot1"></slot>
                <slot name="middleSlot2"> </slot>
                <slot name="middleSlot3"> </slot>
                <slot name="middleSlot4"> </slot>
            </el-row>
            <el-row :span="8" style="margin-top: 8px;" type="flex" justify="space-between">
                <slot name="bottomSlot1"></slot>
                <slot name="bottomSlot2"></slot>
            </el-row>
        </el-col>
    </div>
</template>
<style scoped>
.node {
    border-radius: 6px;
    width: 85%;
    height: 80px;
    background: #e9e9e9;
    margin: 10px;
    margin-left: 0px;
    padding: 5px;
}
</style>
<script>
export default {
    data() {
        return {
        }
    },
}
</script>