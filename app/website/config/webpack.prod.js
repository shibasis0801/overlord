const path = require("path");
const baseWebpack = require("./webpack.base");
module.exports = {
    mode: "production",
    output: {
        path: path.resolve(__dirname, "../build"),
        filename: "bundle.js"
    },

    entry: baseWebpack.entry,
    resolve: baseWebpack.resolve,
    module: baseWebpack.module,
    plugins: baseWebpack.plugins
}
