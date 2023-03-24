import request from '@/utils/request'

export function listProject(query) {
  return request({
    url: '/system/project/page',
    method: 'get',
    params: query
  })
}