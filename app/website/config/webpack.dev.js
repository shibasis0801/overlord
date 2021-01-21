const baseWebpack = require("./webpack.base");
const path = require("path");

module.exports = {
    mode: "development",
    devServer: {
        contentBase: path.join(__dirname, '../build'),
        compress: true,
        port: 3000,
        hot: true
    },
    devtool: "inline-source-map",

    entry: baseWebpack.entry,
    resolve: baseWebpack.resolve,
    module: baseWebpack.module,
    plugins: baseWebpack.plugins
}
