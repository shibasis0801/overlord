const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
// Use ReactNativePaper if needed
// https://callstack.github.io/react-native-paper/getting-started.html
const commonReact = path.resolve(__dirname, "../../react")
const webReact = path.resolve(__dirname, "../src")

module.exports = {
    entry: path.resolve(__dirname, "../src/index.tsx"),
    resolve: {
        extensions: [ ".ts", ".tsx", ".js", ".json", "web.js", "web.jsx" ],
        alias: {
            "react-native$": "react-native-web"
        }
    },
    module: {
        rules: [
            {
                test: /\.[jt]sx?/,
                include: [
                    commonReact,
                    webReact
                ],
                use: {
                    loader: "babel-loader",
                    options: {
                        cacheDirectory: true,
                        presets: [
                            'module:metro-react-native-babel-preset'
                        ],
                        plugins: [
                            "react-native-web",
                            ["@babel/plugin-proposal-decorators", { legacy: true }]
                        ]
                    }
                }
            },
            {
                test: /\.(png|jpe?g|gif)$/i,
                use: [
                    {
                        loader: 'file-loader',
                    },
                ],
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, "../public/index.html")
        })
    ]
}
