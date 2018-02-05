/**
 * Created by Yuangui on 2017-03-06.
 */
import dateUtil from 'element-ui/src/utils/date'

const consts = {
  ERROR_LEVEL: {
    INFO: '0',
    WARN: '1',
    ERROR: '2',
    FATAL: '3'
  },
  ERROR_CODE: {
    SUCCESS: '0', // 操作成功
    FAILD: '-1', // 操作失败
    DATE_FORMAT_ERROR: 'QJZ0003', // 日期格式化错误
    UPLOAD_FAILD: 'QJZ0004', // 文件上传失败
    DELETE_FAILD: 'QJZ0005', // 文件删除失败
    UNSUPPORTED_FORMAT: 'QJZ0006', // 不支持的图片格式
    DOWNLOAD_FAILD: 'QJZ0007', // 文件下载失败
    GET_POSITION_FAILD: 'QJZ0008' // 定位失败
  },
  YES_NO: {
    NO: 0,
    YES: 1
  },
  //  datePickerOptions
  DATE_PICKER_OPTIONS: {
    shortcuts: [{
      text: '最近一周',
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近一个月',
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近三个月',
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近半年',
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 180)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近半年',
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 180)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '当月' + dateUtil.format(new Date(), 'yyyy-M'),
      onClick (picker) {
        const end = new Date()
        const start = new Date()
        start.setDate(1)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '前月' + dateUtil.format(new Date().setDate(0), 'yyyy-M'),
      onClick (picker) {
        const end = new Date()
        end.setDate(0)
        const start = new Date()
        start.setDate(1)
        start.setMonth(start.getMonth() - 1, 1)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '当周',
      onClick (picker) {
        const end = new Date()
        let start = new Date()
        start.setDate(start.getDate() - start.getDay() + 1)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '前周',
      onClick (picker) {
        const end = new Date()
        end.setDate(end.getDate() - end.getDay())
        const start = new Date()
        start.setDate(start.getDate() - start.getDay() - 6)
        picker.$emit('pick', [start, end])
      }
    }]
  }
}
const enums = {
  // 产品线
  DOMAIN_TYPE: {
    '1': 'ERP WEB端',
    '5': 'ERP 管理端'
  },
  // 资源类型
  RESOURCE_TYPE: {
    '1': 'URL资源',
    '2': '页面片段资源'
  },
  // 操作类型
  OPERATION_TYPE: {
    '1': '读取权限',
    '2': '创建权限',
    '3': '写入权限',
    '4': '删除权限'
  },
  // ROLE优先级
  ROLE_LEVEL: {
    '1': '只读',
    '2': '操作',
    '3': '管理',
    '4': '属主'
  }
}
const messages = {
  notifyCheckError: () => {
    return {
      title: '警告',
      message: '信息填写错误',
      type: 'warning',
      duration: 2500
    }
  },
  notifyConfirmCancel: () => {
    return {
      title: '提示',
      message: '已取消',
      type: 'warning',
      duration: 2500
    }
  },
  notifyUpdateSuccess: () => {
    return {
      title: '提示',
      message: '修改成功',
      type: 'info',
      duration: 2500
    }
  }
}
// 使用时直接在Script中使用[consts.ERROR_LEVEL.INFO]取值即可
// global.consts = consts
// global.messages = messages
// global.enums = enums
export {consts, messages, enums}
