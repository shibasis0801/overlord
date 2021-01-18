const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    mode: "development",
    entry: path.resolve(__dirname, "../src/index.tsx"),
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".json"],
        alias: {

        }
    },
    module: {
        rules: [
            {
                test: /\.[jt]sx?/,
                exclude: /node_modules/,
                loader: "babel-loader"
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: "development",
            template: "./public/index.html"
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, '../build'),
        compress: true,
        port: 3000,
        hot: true
    },
    devtool: "inline-source-map"
}