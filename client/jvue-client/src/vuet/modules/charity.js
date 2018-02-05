/*
 * 慈善赠药相关modules
 *
 */

import ApiUtils from '@/api/'

export default {
  modules: {
    record: {
      modules: {
        list: {
          data() {
            return {
              searchForm: {
                goodsName: null,
                pharmacyName: null,
                doctorName: null,
                patientName: null,
                receiptorType: null,
                dateRange: {},
                page: 0,
                pageSize: 10,
                pageCount: 0,
                totalCount: 0
              },
              loading: false,
              list: []
            }
          },
          /**
           * 初始化
           * @returns {Promise.<void>}
           */
          async fetch() {
            this.loading = true
            const param = {
              goodsName: this.searchForm.goodsName,
              pharmacyName: this.searchForm.pharmacyName,
              doctorName: this.searchForm.doctorName,
              patientName: this.searchForm.patientName,
              receiptorType: this.searchForm.receiptorType,
              page: this.searchForm.page,
              pageSize: this.searchForm.pageSize
            }

            if (this.searchForm.dateRange.length > 0) {
              param.donateBeginTime = global.utils.formatDate(this.searchForm.dateRange[0])
              param.donateEndTime = global.utils.formatDate(this.searchForm.dateRange[1])
            }

            const response = await ApiUtils.get('618001', param)
            this.loading = false
            let {code, message, data} = response.data
            if (code === '0') {
              this.searchForm.page = data.page
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pageCount
              this.searchForm.totalCount = data.totalCount
              this.list = data.data || []
              return data
            } else {
              return Promise.reject(message)
            }
          }
        },
        detail: {
          data() {
            return {
              loading: true,
              record: {}
            }
          },
          async fetch() {
            this.loading = true
            const route = this.app.$route
            let response = await ApiUtils.get('618003', {recordId: `${route.params.id}`})
            this.loading = false
            let {code, message, data} = response.data
            if (code === '0') {
              this.record = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        },
        common: {
          data() {
            return {
              pharmacyList: [],
              projectList: [],
              patientList: [],
              doctorList: [],
              projectInfo: {}
            }
          },
          async fetch(provinceId) {
            // 援助点列表
            let response = await ApiUtils.get('632013', {provinceId: provinceId})
            let {code, data, message} = response.data
            if (code === '0') {
              this.pharmacyList = data
              return data
            } else {
              return Promise.reject(message)
            }
          },
          async fetchProjectList(pharmacyId) {
            let response = await ApiUtils.get('6Z0001', {pharmacyId: pharmacyId})
            let {code, data, message} = response.data
            if (code === '0') {
              this.projectList = data
              return data
            } else {
              return Promise.reject(message)
            }
          },
          async fetchProjectInfo(projectId) {
            let response = await ApiUtils.get('6Z0002', {projectId: projectId})
            let {code, data, message} = response.data
            if (code === '0') {
              this.projectInfo = data
              return data
            } else {
              return Promise.reject(message)
            }
          },
          async fetchPatientList(patientKey) {
            let response = await ApiUtils.get('6Z0011', {patientKey: patientKey})
            let {code, data, message} = response.data
            if (code === '0') {
              this.patientList = data
              return data
            } else {
              return Promise.reject(message)
            }
          },
          async fetchDoctorList(projectId, doctorKey) {
            let response = await ApiUtils.get('6Z0031', {
              pageSize: 50,
              projectId: projectId,
              doctorName: doctorKey
            })
            let {code, data, message} = response.data
            if (code === '0') {
              this.doctorList = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        },
        append: {
          data() {
            return {
              form: {
                provinceId: null,
                pharmacyId: null,
                projectId: null,
                patientId: null,
                doctorId: null,
                // 用于查询患者
                patientKey: '',
                doctorKey: '',
                patientQualification: {
                  id: null,
                  careerCertificateType: null,
                  financialSituationImage: null,
                  incomeCertificateImage: null,
                  careerCertificateImage: null,
                  caseImages: [],
                  removedCaseImages: []
                },
                buyCodeList: [],
                donateCodeList: [],
                charityPrescriptionInfo: {
                  medicalCertificateImage: null,
                  prescriptionBillImage: null
                },
                receiptorType: '1',
                receiptorInfo: {
                  expressCode: 'debang',
                  expressNumber: null
                }
              }
            }
          },
          async fetch() {
            // todo **
          },
          async save() {
            debugger
            let {
              pharmacyId, projectId, patientId, doctorId,
              patientQualification, buyCodeList, donateCodeList,
              charityPrescriptionInfo, receiptorType, receiptorInfo
            } = this.form
            let recordData = {
              pharmacyId,
              projectId,
              patientId,
              doctorId,
              patientQualification,
              buyCodeList,
              donateCodeList,
              charityPrescriptionInfo,
              receiptorType,
              receiptorInfo
            }
            console.info(this.form)
            console.info(recordData)
            let response = await ApiUtils.post('6Z0021', recordData)
            let {code, data, message} = response.data
            if (code === '0') {
              return data
            } else {
              return Promise.reject(message)
            }
          }
        }
      }
    },
    pbm: {
      modules: {
        list: {
          data() {
            return {
              searchForm: {
                goodsName: null,
                pharmacyName: null,
                patientName: null,
                dateRange: {},
                page: 0,
                pageSize: 10,
                pageCount: 0,
                totalCount: 0
              },
              loading: false,
              list: []
            }
          },
          /**
           * 初始化
           * @returns {Promise.<void>}
           */
          async fetch() {
            this.loading = true
            const param = {
              goodsName: this.searchForm.goodsName,
              pharmacyName: this.searchForm.pharmacyName,
              patientName: this.searchForm.patientName,
              page: this.searchForm.page,
              pageSize: this.searchForm.pageSize
            }

            if (this.searchForm.dateRange.length > 0) {
              param.startTime = global.utils.formatDate(this.searchForm.dateRange[0])
              param.endTime = global.utils.formatDate(this.searchForm.dateRange[1])
            }

            const response = await ApiUtils.get('618006', param)
            this.loading = false
            let {code, message, data} = response.data
            if (code === '0') {
              this.searchForm.page = data.page
              this.searchForm.pageSize = data.pageSize
              this.searchForm.pageCount = data.pageCount
              this.searchForm.totalCount = data.totalCount
              this.list = data.data || []
              return data
            } else {
              return Promise.reject(message)
            }
          }
        },
        detail: {
          data() {
            return {
              loading: true,
              record: {}
            }
          },
          async fetch() {
            this.loading = true
            const route = this.app.$route
            let response = await ApiUtils.get('618007', {id: `${route.params.id}`})
            this.loading = false
            let {code, message, data} = response.data
            if (code === '0') {
              this.record = data
              return data
            } else {
              return Promise.reject(message)
            }
          }
        }
      }
    }
  }
}
