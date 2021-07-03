(function(config){
    config.optimization = {
        splitChunks: {
            chunks: 'all',
            cacheGroups: {
                vendorKotlinJS: {
                    test:  /[\\/]node_modules[\\/](kotlin)[\\/]/,
                    name: "kotlin",
                    priority: 10,
                    chunks: "all"
                },
                vendorKotlinCoroutines: {
                    test:  /[\\/]node_modules[\\/](kotlin-coroutines)[\\/]/,
                    name: "kotlin-coroutines",
                    priority: 11,
                    chunks: "all"
                }
            }
        }
    }

    config.externals = {
        kotlin: "kotlin"
    }
})(config);
