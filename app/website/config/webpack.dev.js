const baseWebpack = require("./webpack.base");
const path = require("path");

const index = path.resolve(__dirname, "../public/index.html")

module.exports = {
    mode: "development",
    devServer: {
        contentBase: path.join(__dirname, '../public'),
        compress: true,
        port: 3000,
        hot: true,
        publicPath: "/",
        historyApiFallback: true
        // historyApiFallback: {
        //     index
        // }
    },
    // output: {
    //     publicPath: index
    // },
    devtool: "inline-source-map",

    entry: baseWebpack.entry,
    resolve: baseWebpack.resolve,
    module: baseWebpack.module,
    plugins: baseWebpack.plugins
}
