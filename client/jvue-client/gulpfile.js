const gulp = require('gulp')
const path = require('path')
const config = require('./gulp/')
const buildConfig = require('./config/')
const isEnv = process.env.NODE_ENV == 'production'

/**
 * 清除生产目录文件
 */
const del = require('del')
gulp.task('clean', ['upload'], function (callback) {
  console.log('## 已经成功部署到服务器上')
  console.log('## 清除原来编译的代码')
  del(['.' + config.distPath], callback)
})

/**
 * 编译代码
 */
const webpack = require('webpack')
const webpackConfig = require('./build/webpack.prod.conf')
gulp.task('build', function (callback) {
  console.log('## 代码编译开始')
  require('shelljs/global')
  var assetsPath = path.join(buildConfig.build.assetsRoot, buildConfig.build.assetsSubDirectory)
  rm('-rf', assetsPath)
  mkdir('-p', assetsPath)
  console.log('## 复制文件：static/*')
  cp('-R', 'static/*', assetsPath)
  webpack(webpackConfig, function (err, state) {
    console.log('## 代码编译完成')
    callback(err)
  })
})

/**
 * 编译代码，自动部署到服务器
 */
const ftp = require('gulp-sftp')
gulp.task('upload', ['build'], function (callback) {
  console.log('## 正在部署到服务器上')
  var dev = isEnv ? config.devDist : config.devTest
  console.log(dev)
  gulp.src('.' + config.distPath + '**')
    .pipe(ftp(Object.assign(dev, {callback})))
})

/**
 * 上传到测试服务器上
 */
gulp.task('devTest', ['build', 'upload', 'clean'])

/**
 * 上传到生产服务器上
 */
gulp.task('devDist', ['build', 'upload', 'clean'])
