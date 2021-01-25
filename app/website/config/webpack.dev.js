const baseWebpack = require("./webpack.base");
const path = require("path");

module.exports = {
    mode: "development",
    devServer: {
        contentBase: path.join(__dirname, '../public'),
        compress: true,
        port: 3000,
        hot: true,
        publicPath: "/",
        historyApiFallback: true,
        proxy: [
            {
                context: ["/signalling"],
                target: "http://localhost:8080"
            }
        ]
    },
    devtool: "inline-source-map",

    entry: baseWebpack.entry,
    resolve: baseWebpack.resolve,
    module: baseWebpack.module,
    plugins: baseWebpack.plugins
}
