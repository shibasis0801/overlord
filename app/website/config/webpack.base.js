const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const commonReact = path.resolve(__dirname, "../../react")
const webReact = path.resolve(__dirname, "../src")

module.exports = {
    entry: path.resolve(__dirname, "../src/index.tsx"),
    resolve: {
        extensions: [ ".ts", ".tsx", ".js", ".json", "web.js", "web.jsx" ],
        alias: {
            "react-native$": "react-native-web",
            "commonReact": commonReact
        }
    },
    module: {
        rules: [
            {
                test: /\.[jt]sx?/,
                include: [
                    webReact,
                    commonReact,
                ],
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ['module:metro-react-native-babel-preset'],
                        plugins: [
                            "react-native-web",
                            ["@babel/plugin-proposal-decorators", { legacy: true }]
                        ]
                    }
                }
            },
            {
                test: /\.(gif|jpe?g|png|svg)$/,
                use: {
                    loader: 'url-loader',
                    options: {
                        name: '[name].[ext]'
                    }
                }
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./public/index.html"
        })
    ]
}
