/**
 * Created by Liumin on 2017/6/26.
 */
module.exports = {
  devTest: { // 部署到测试服务器上
    remotePath: '/data/nginx/html/hifipi-admin/', // 部署到服务器的路径
    host: '192.168.10.70', // ip地址
    user: 'root', // 帐号
    pass: 'root123', // 密码
    port: 22 // 端口
  },
  devDist: { // 部署正式服务器上
    remotePath: '/data/nginx/html/hifipi-admin/', // 部署到服务器的路径
    host: '192.168.10.70', // ip地址
    user: 'root', // 帐号
    pass: 'zB6ztj', // 密码
    port: 22 // 端口
  },
  distPath: '/dist/', // 程序编译的路径地址
  target: 'http://admin.hifipi.com/admin/' // 连接的服务器地址
}
