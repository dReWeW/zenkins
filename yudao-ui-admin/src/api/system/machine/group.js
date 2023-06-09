import request from '@/utils/request'
// 查询列表
export function page(data) {
    return request({
        url: '/system/machinegroup/page',
        method: 'get',
        params: data
    })
}
export function list(data) {
  return request({
    url: '/system/machinegroup/list',
    method: 'get',
    params: data
  })
}
// 查询详细
export function info(id) {
    return request({
        url: '/system/machinegroup/info',
        method: 'get',
        params: { 'id': id }
    })
}
// 新增
export function add(data) {
    return request({
        url: '/system/machinegroup/add',
        method: 'post',
        data: data
    })
}
// 修改
export function update(data) {
    return request({
        url: '/system/machinegroup/update',
        method: 'put',
        data: data
    })
}
// 删除
export function del(data) {
    return request({
        url: '/system/machinegroup/delete',
        method: 'delete',
        data: data
    })
}
