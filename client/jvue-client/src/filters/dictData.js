/**
 * Created by Yuangui on 2017-01-05.
 */
// import store from '../store/'
import vuet from '@/vuet/'

export default {
  // v-model="model.code|regionName"
  regionData (value) {
    const dictList = vuet.getModule('common-region-list')
    if (!dictList.data) {
      return ''
    } else {
      return dictList.data[value]
    }
  },
  // v-model="model.code|dictName('{key}')"
  dictData (value, key) {
    const dictList = vuet.getModule('common-dict-codes')
    if (!dictList.data) {
      return ''
    } else {
      if (dictList.data[key]) {
        return dictList.data[key][String(value)]
      } else {
        return ''
      }
    }
  },
  // v-model="model.code|enumName('{key}')"
  enumData (value, key) {
    const dictList = vuet.getModule('common-dict-enums')
    if (!dictList.data) {
      return ''
    } else {
      if (dictList.data[key]) {
        return dictList.data[key][String(value)]
      } else {
        return ''
      }
    }
  },
  // v-model="model.code|enumsType('{key}')"
  enumsType (value, key) {
    let enumsType = JSON.parse(localStorage.getItem('enumsType'))
    return enumsType[key][value]
  },
  // v-model="model.code|enumsType('{index}')"
  codesType (value, index) {
    let codesType = JSON.parse(localStorage.getItem('codesType'))
    return codesType[index][value]
  },
  // v-model="model.code|enumsType('{index}')"
  regionsType (provinceWithCity) {
    let regionsType = JSON.parse(localStorage.getItem('regionsType'))
    let provinceName = ''
    let cityName = ''
    let index = provinceWithCity.indexOf(' ')
    for (let i = 0; i < regionsType.length; i++) {
      if (Number(regionsType[i].id) === Number(provinceWithCity.substr(0, index))) {
        provinceName = regionsType[i].name
        for (let a = 0; a < regionsType[i].child.length; a++) {
          if (Number(regionsType[i].child[a].id) === Number(provinceWithCity.substr(index + 1, provinceWithCity.length - 1))) {
            cityName = regionsType[i].child[a].name
          }
        }
      }
    }

    return provinceName + cityName
  }
}
